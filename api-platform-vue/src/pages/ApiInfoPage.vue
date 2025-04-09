<script lang="ts" setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { debugApi, getApiInfoById } from '../services/apiInfo';
import type { ApiDebugRequest, ApiInfoVO } from '../types';
import { message } from 'ant-design-vue';
import GlobalHeader from '../components/GlobalHeader.vue';

// 路由 & 数据
const router = useRouter();
const apiInfo = ref<ApiInfoVO>({} as ApiInfoVO);
const loading = ref<boolean>(false);
const parameters = ref<string>('');
const response = ref('');
const fetchApiInfo = async () => {
  loading.value = true;
  try {
    const id = router.currentRoute.value.query.id;
    if (id) {
      getApiInfoById(id as string).then((data) => {
        apiInfo.value = data;
      }).catch((error: any) => {
        console.error('获取接口信息失败:', error);
        apiInfo.value = {} as ApiInfoVO;
      });
    }
  } catch (error) {
    console.error('获取接口信息失败:', error);
  } finally {
    loading.value = false;
  }
};

// 调试接口请求
const handleDebug = async () => {
  console.log('调试接口请求:', parameters.value);

  if (!parameters.value) {
    message.error('请输入参数');
    return;
  }

  // 模拟 API 调试
  try {
    const res = await fetchApiInfoForDebug({
      id: apiInfo.value.id,
      params: JSON.parse(parameters.value),
    } as ApiDebugRequest);
    response.value = JSON.stringify(res, null, 2);
  } catch (error) {
    response.value = '接口调试失败';
  }
};

// 模拟接口调试函数
const fetchApiInfoForDebug = async (params: ApiDebugRequest) => {
  return debugApi(params);
};

onMounted(fetchApiInfo);
</script>

<template>
  <GlobalHeader />
  <a-layout class="page-container">
    <a-layout-content class="content">
      <!-- 返回 + 面包屑 -->
      <div class="breadcrumb-container">
        <a-button type="text" @click="router.back" class="back-btn">
          <template #icon><i class="ri-arrow-left-line"></i></template>
          返回
        </a-button>
        <a-breadcrumb>
          <a-breadcrumb-item>首页</a-breadcrumb-item>
          <a-breadcrumb-item>接口详情</a-breadcrumb-item>
        </a-breadcrumb>
      </div>

      <!-- 接口信息 -->
      <a-card title="接口信息" class="api-card">
        <a-descriptions bordered size="middle" :column="2">
          <a-descriptions-item label="接口名称">{{ apiInfo.name }}</a-descriptions-item>
          <a-descriptions-item label="请求方式">
            <a-tag :color="apiInfo.method === 'GET' ? 'green' : 'blue'">{{ apiInfo.method }}</a-tag>
          </a-descriptions-item>
          <a-descriptions-item label="接口路径">{{ apiInfo.endpoint }}</a-descriptions-item>
          <a-descriptions-item label="状态">
            <a-badge :status="apiInfo.status ? 'processing' : 'error'" :text="apiInfo.status ? '启用' : '禁用'" />
          </a-descriptions-item>
          <a-descriptions-item label="接口描述" :span="2">{{ apiInfo.description }}</a-descriptions-item>
        </a-descriptions>
      </a-card>

      <!-- 在线调试 -->
      <a-card title="在线调试" class="debug-card">
        <a-form layout="vertical">
          <a-form-item label="输入参数">
            <a-textarea v-model:value="parameters" placeholder="请输入 JSON 格式的参数" auto-size />
          </a-form-item>
          <a-button type="primary" block @click="handleDebug">调试</a-button>
        </a-form>

        <!-- 响应结果 -->
        <div v-if="response" class="response-container">
          <h3>响应结果</h3>
          <a-card class="response-card">
            <pre>{{ response }}</pre>
          </a-card>
        </div>
      </a-card>
    </a-layout-content>
  </a-layout>
</template>

<style scoped>
/* 页面整体样式 */
.page-container {
  min-height: 100vh;
  background: #f0f2f5;
  padding: 24px;
}

/* 返回按钮 + 面包屑 */
.breadcrumb-container {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-bottom: 16px;
}

/* API 信息卡片 */
.api-card {
  background: white;
  margin-bottom: 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
}

/* 在线调试卡片 */
.debug-card {
  background: white;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
}

/* 响应结果 */
.response-container {
  margin-top: 16px;
}

.response-card {
  background: #282c34;
  color: #61dafb;
  font-family: "Courier New", Courier, monospace;
  padding: 12px;
  border-radius: 6px;
  white-space: pre-wrap;
}
</style>