<script lang="ts" setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const searchQuery = ref('');
const router = useRouter();

const handleSearch = () => {
  if (searchQuery.value.trim()) {
    router.push({ path: '/search', query: { q: searchQuery.value } });
  }
};
</script>

<template>
  <dev>
    <a-layout-header class="navbar">
      <div class="logo">晓芸API</div>
      <a-input-search v-model:value="searchQuery" placeholder="搜索接口..." class="search-box" @search="handleSearch" />
      <a-button type="primary" class="login-btn" @click="router.push('/login')">登录</a-button>
    </a-layout-header>
  </dev>
</template>

<style scoped>
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
  background: #001529;
  color: white;
}

.logo {
  font-size: 20px;
  font-weight: bold;
  color: white;
}

.search-box {
  width: 300px;
}

.login-btn {
  margin-left: auto;
}
</style>