import { createWebHistory, createRouter } from 'vue-router'

const routes = [
    { path: '/', component: () => import('../pages/HomePages.vue') },
    { path: '/login', component: () => import('../pages/LoginPage.vue') },
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router