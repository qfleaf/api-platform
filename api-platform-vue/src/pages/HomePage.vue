<script lang="ts" setup>
import { ref, onMounted } from 'vue';
import { getApiInfoPage } from '../services/apiInfo';
import type { ApiInfoPageVO } from '../types';
import GlobalHeader from '../components/GlobalHeader.vue';
import { useRouter } from 'vue-router';

const route = useRouter();
const apiList = ref([] as ApiInfoPageVO[]);
const loading = ref(false);
const pagination = ref({
  current: 1,
  pageSize: 10,
  total: 0
});

const fetchApiList = async () => {
  loading.value = true;
  try {
    getApiInfoPage({
      ...pagination.value,
    }).then((data) => {
      apiList.value = data.records;
      pagination.value.total = data.total;
      pagination.value.current = data.current;
      pagination.value.pageSize = data.size;
    }).catch((error: any) => {
      console.error('获取接口列表失败:', error);
      apiList.value = [];
      pagination.value.total = 0;
      pagination.value.current = 1;
      pagination.value.pageSize = 10;
    });
  } catch (error) {
    console.error('获取接口列表失败:', error);
  } finally {
    loading.value = false;
  }
};

const handlePageChange = (page: number, pageSize: number) => {
  pagination.value.current = page;
  pagination.value.pageSize = pageSize;
  fetchApiList();
};

const handleCheck = (id: number) => {
  console.log('Check record id:', id);
  route.push({path: '/api/info', query: { id }});
};

onMounted(fetchApiList);
</script>

<template>
  <a-layout>
    <GlobalHeader />
    <a-layout-content class="content">
      <a-table :dataSource="apiList" :loading="loading" :pagination="false" rowKey="id">
        <a-table-column title="接口名称" dataIndex="name" key="name" />
        <a-table-column title="路径" dataIndex="endpoint" key="endpoint" />
        <a-table-column title="请求方式" dataIndex="method" key="method" />
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
      <a-pagination :current="pagination.current" :total="pagination.total" :pageSize="pagination.pageSize"
        @change="handlePageChange" showSizeChanger :pageSizeOptions="['10', '20', '50']" class="pagination" />
    </a-layout-content>
  </a-layout>
</template>

<style scoped>
.content {
  padding: 20px;
}

.pagination {
  margin-top: 20px;
  text-align: center;
}
</style>