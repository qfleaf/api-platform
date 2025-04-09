import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
// ant-design-vue
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';
// vue-router
import router from './routers';
// pinia
import { createPinia } from 'pinia';
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

const pinia = createPinia();
pinia.use(piniaPluginPersistedstate);

createApp(App)
    .use(Antd)
    .use(router)
    .use(pinia)
    .mount('#app')
