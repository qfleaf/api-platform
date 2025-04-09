<script lang="ts" setup>
import { h, ref } from 'vue';
import { useRouter } from 'vue-router';
import { UserOutlined } from '@ant-design/icons-vue';
import { useUserStore } from '../stores/userStore';

const router = useRouter();
const userStore = useUserStore();
const isLoggedIn = ref<boolean>(userStore.isLogin);

// 退出登录
const handleLogout = () => {
  userStore.logout();
  isLoggedIn.value = false;
};

// 登录跳转
const handleLogin = () => {
  router.push('/login');
};
</script>

<template>
  <a-layout-header class="navbar">
    <!-- 左侧 LOGO -->
    <div class="logo" @click="router.push('/')">
      <!-- TODO: 设计logo <img src="@/assets/logo.svg" alt="Logo" class="logo-img" /> -->
      <span class="logo-text">晓芸API</span>
    </div>

    <!-- 右侧 用户操作 -->
    <div class="user-actions">
      <a-button v-if="!isLoggedIn" type="primary" class="login-btn" @click="handleLogin">登录</a-button>

      <a-dropdown v-else>
        <a class="user-avatar">
          <a-avatar size="large" :icon="h(UserOutlined)" />
        </a>
        <template #overlay>
          <a-menu>
            <a-menu-item key="profile">个人中心</a-menu-item>
            <a-menu-item key="logout" @click="handleLogout">退出登录</a-menu-item>
          </a-menu>
        </template>
      </a-dropdown>
    </div>
  </a-layout-header>
</template>

<style scoped>
/* 顶部导航 */
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
  background: rgba(0, 21, 41, 0.9);
  /* 玻璃效果 */
  backdrop-filter: blur(10px);
  color: white;
  height: 64px;
}

/* LOGO 样式 */
.logo {
  display: flex;
  align-items: center;
  cursor: pointer;
}

.logo-img {
  width: 40px;
  height: 40px;
  margin-right: 10px;
}

.logo-text {
  font-size: 22px;
  font-weight: bold;
  color: white;
}

/* 用户操作 */
.user-actions {
  display: flex;
  align-items: center;
}

/* 登录按钮 */
.login-btn {
  background-color: #1890ff;
  border-color: #1890ff;
  color: white;
}

/* 用户头像 */
.user-avatar {
  cursor: pointer;
}
</style>