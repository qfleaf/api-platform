import { createWebHistory, createRouter } from 'vue-router'

const routes = [
    { path: '/', component: () => import('../pages/HomePage.vue') },
    { path: '/login', component: () => import('../pages/LoginPage.vue') },
    
    { path: '/api/info', component: () => import('../pages/ApiInfoPage.vue') },
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router