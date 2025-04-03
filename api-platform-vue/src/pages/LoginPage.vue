<script lang="ts" setup>
import { reactive, ref } from 'vue';
import { useRouter } from 'vue-router';
import type { UserLoginRequest } from '../types';
import { useUserStore } from '../stores/userStore';

const router = useRouter();
const userStore = useUserStore();
// 登录参数
const loginParams = reactive<UserLoginRequest>({
  username: '',
  password: '',
  loginType: 'account',
});
const errorMessage = ref<string>('');
const loading = ref<boolean>(false);

// 处理登录
const handleLogin = async () => {
  if (!loginParams.username || !loginParams.password) {
    errorMessage.value = '请输入用户名和密码';
    return;
  }
  loading.value = true;
  userStore.login(loginParams)
    .then(() => {
      goBack();
    }).catch((error: any) => {
      errorMessage.value = error.message || '登录失败';
    }).finally(() => {
      loading.value = false;
    });
};

// 返回上一级
const goBack = () => {
  if (window.history.length > 1) {
    router.back();
  } else {
    router.push('/');
  }
};
</script>

<template>
  <div class="login-page">
    <div class="background-overlay"></div>

    <!-- 返回按钮 -->
    <button class="back-button" @click="goBack">← 返回</button>

    <div class="login-container">
      <h1>欢迎回来 👋</h1>
      <p class="subtitle">请使用您的账户登录</p>

      <form @submit.prevent="handleLogin" class="login-form">
        <input v-model="loginParams.username" type="text" placeholder="用户名" class="input-field" />
        <input v-model="loginParams.password" type="password" placeholder="密码" class="input-field" />

        <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>

        <button type="submit" class="login-button" :disabled="loading">
          {{ loading ? '登录中...' : '登录' }}
        </button>
      </form>
    </div>
  </div>
</template>

<style scoped>
/* 整体背景 */
.login-page {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  position: relative;
  background: linear-gradient(135deg, #2b5876, #4e4376);
  overflow: hidden;
}

/* 背景模糊层 */
.background-overlay {
  position: absolute;
  width: 100%;
  height: 100%;
  backdrop-filter: blur(10px);
  background: rgba(255, 255, 255, 0.1);
  z-index: 0;
}

/* 返回按钮 */
.back-button {
  position: absolute;
  top: 20px;
  left: 20px;
  background: transparent;
  color: white;
  font-size: 16px;
  border: none;
  cursor: pointer;
  transition: all 0.3s ease-in-out;
  z-index: 10;
}

.back-button:hover {
  transform: scale(1.1);
  opacity: 0.8;
}

/* 登录框 */
.login-container {
  position: relative;
  background: rgba(255, 255, 255, 0.2);
  backdrop-filter: blur(15px);
  padding: 40px;
  border-radius: 15px;
  box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.3);
  width: 360px;
  text-align: center;
  transition: transform 0.3s ease-in-out, box-shadow 0.3s;
}

/* 悬浮效果 */
.login-container:hover {
  transform: translateY(-5px);
  box-shadow: 0px 15px 30px rgba(0, 0, 0, 0.35);
}

/* 标题 */
h1 {
  margin-bottom: 10px;
  color: #fff;
  font-size: 24px;
}

/* 副标题 */
.subtitle {
  color: rgba(255, 255, 255, 0.8);
  margin-bottom: 20px;
  font-size: 14px;
}

/* 输入框 */
.input-field {
  width: 100%;
  padding: 12px;
  margin-bottom: 15px;
  border: 1px solid rgba(255, 255, 255, 0.5);
  border-radius: 8px;
  background: transparent;
  color: white;
  font-size: 16px;
  transition: border 0.3s ease-in-out;
}

.input-field::placeholder {
  color: rgba(255, 255, 255, 0.7);
}

.input-field:focus {
  border-color: #fff;
  outline: none;
  box-shadow: 0 0 10px rgba(255, 255, 255, 0.4);
}

/* 错误信息 */
.error-message {
  color: #ff4d4f;
  font-size: 14px;
  margin-bottom: 10px;
}

/* 登录按钮 */
.login-button {
  width: 100%;
  padding: 12px;
  background: linear-gradient(135deg, #4facfe, #00f2fe);
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  font-weight: bold;
  cursor: pointer;
  transition: all 0.3s ease-in-out;
}

.login-button:hover {
  transform: scale(1.05);
  box-shadow: 0 5px 15px rgba(79, 172, 254, 0.5);
}

.login-button:disabled {
  background: gray;
  cursor: not-allowed;
}
</style>