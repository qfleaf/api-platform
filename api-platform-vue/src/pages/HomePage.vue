<script lang="ts" setup>
import { ref, onMounted } from 'vue';
import { getApiInfoPage } from '../services/apiInfo';
import type { ApiInfoPageVO } from '../types';
import GlobalHeader from '../components/GlobalHeader.vue';
import { useRouter } from 'vue-router';
import { message } from 'ant-design-vue';

// 路由
const router = useRouter();

// 数据
const apiList = ref<ApiInfoPageVO[]>([]);
const loading = ref(false);
const searchQuery = ref('');
const pagination = ref({
  current: 1,
  pageSize: 10,
  total: 0
});

// 获取接口列表
const fetchApiList = async () => {
  loading.value = true;
  try {
    const data = await getApiInfoPage({
      ...pagination.value,
      search: searchQuery.value
    });
    apiList.value = data.records;
    pagination.value.total = data.total;
    pagination.value.current = data.current;
    pagination.value.pageSize = data.size;
  } catch (error) {
    console.error('获取接口列表失败:', error);
    message.error('获取接口列表失败');
    apiList.value = [];
    pagination.value.total = 0;
  } finally {
    loading.value = false;
  }
};

// 分页变化
const handlePageChange = (page: number, pageSize: number) => {
  pagination.value.current = page;
  pagination.value.pageSize = pageSize;
  fetchApiList();
};

// 跳转接口详情
const handleCheck = (id: number) => {
  router.push({ path: '/api/info', query: { id } });
};

// 搜索
const handleSearch = () => {
  fetchApiList();
};

onMounted(fetchApiList);
</script>

<template>
  <GlobalHeader />
  <a-layout class="page-container">
    <a-layout-content class="content">
      <!-- 标题 -->
      <div class="header">
        <h2>API 接口列表</h2>
      </div>

      <!-- 搜索框 -->
      <a-card class="search-card">
        <div class="search-bar">
          <a-input-search v-model:value="searchQuery" placeholder="搜索接口..." @search="handleSearch" enter-button
            size="large" />
        </div>
      </a-card>

      <!-- 数据表格 -->
      <a-card class="table-card">
        <a-table :dataSource="apiList" :loading="loading" :pagination="false" rowKey="id">
          <a-table-column title="接口名称" dataIndex="name" key="name" />
          <a-table-column title="路径" dataIndex="endpoint" key="endpoint" />
          <a-table-column title="请求方式" dataIndex="method" key="method">
            <template #default="{ record }">
              <a-tag v-if="record.method === 'GET'" color="green">GET</a-tag>
              <a-tag v-else-if="record.method === 'POST'" color="blue">POST</a-tag>
              <a-tag v-else-if="record.method === 'PUT'" color="yellow">PUT</a-tag>
              <a-tag v-else-if="record.method === 'DELETE'" color="red">DELETE</a-tag>
              <a-tag v-else>{{ record.method }}</a-tag>
            </template>
          </a-table-column>
          <a-table-column title="状态" key="status">
            <template #default="{ record }">
              <a-badge :status="record.status ? 'success' : 'error'" :text="record.status ? '启用' : '禁用'" />
            </template>
          </a-table-column>
          <a-table-column title="操作" key="action">
            <template #default="{ record }">
              <a-button type="link" @click="() => handleCheck(record.id)">查看</a-button>
            </template>
          </a-table-column>
        </a-table>

        <!-- 分页 -->
        <div class="pagination-container">
          <a-pagination :current="pagination.current" :total="pagination.total" :pageSize="pagination.pageSize"
            @change="handlePageChange" showSizeChanger :pageSizeOptions="['10', '20', '50']" />
        </div>
      </a-card>
    </a-layout-content>
  </a-layout>
</template>

<style scoped>
/* 页面整体背景 */
.page-container {
  min-height: 100vh;
  background: #f0f2f5;
  padding: 24px;
}

/* 标题样式 */
.header {
  margin-bottom: 16px;
}

/* 搜索框卡片 */
.search-card {
  margin-bottom: 16px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  padding: 16px;
}

/* 搜索框 */
.search-bar {
  display: flex;
  justify-content: center;
}

/* 表格卡片 */
.table-card {
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  padding: 16px;
}

/* 分页 */
.pagination-container {
  margin-top: 16px;
  text-align: center;
}
</style>