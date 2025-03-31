<script lang="ts" setup>
import { ref } from 'vue';

const username = ref('');
const password = ref('');
const errorMessage = ref('');

// 登录表单的提交函数
const handleSubmit = () => {
  if (!username.value || !password.value) {
    errorMessage.value = '用户名和密码不能为空';
    return;
  }

  // 模拟表单提交
  console.log('Submitting:', { username: username.value, password: password.value });

  // 假设登录失败
  errorMessage.value = '登录失败，请检查用户名和密码';
};
</script>

<template>
  <div class="login-form">
    <h2>登录</h2>

    <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>

    <form @submit.prevent="handleSubmit">
      <div class="input-group">
        <label for="username">用户名</label>
        <input type="text" id="username" v-model="username" placeholder="请输入用户名" required />
      </div>

      <div class="input-group">
        <label for="password">密码</label>
        <input type="password" id="password" v-model="password" placeholder="请输入密码" required />
      </div>

      <button type="submit">登录</button>
    </form>
  </div>
</template>

<style scoped>
.login-form {
  max-width: 400px;
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: #f9f9f9;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

.input-group {
  margin-bottom: 15px;
}

.input-group label {
  display: block;
  margin-bottom: 5px;
}

.input-group input {
  width: 100%;
  padding: 8px;
  margin-top: 5px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #4caf50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}

button:hover {
  background-color: #45a049;
}

.error-message {
  color: red;
  margin-bottom: 10px;
  font-size: 14px;
}
</style>