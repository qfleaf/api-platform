<script lang="ts" setup>
import LoginForm from '../components/LoginForm.vue';
</script>

<template>
  <div class="login-page">
    <div class="login-container">
      <h1>欢迎登录</h1>
      <LoginForm />
    </div>
  </div>
</template>

<style scoped>
.login-page {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(135deg, #667eea, #764ba2);
}

.login-container {
  background: white;
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
  width: 350px;
  text-align: center;
}

h1 {
  margin-bottom: 20px;
  color: #333;
}
</style>