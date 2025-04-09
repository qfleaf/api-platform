import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    open: false,
    host: true,
    // port: 8081,
    https: false,
    cors: true,
    proxy: {    // 配置跨域                
      '/yunapi': {
        target: 'http://localhost:8081/',
        changeOrigin: true,
        // rewrite: (path) => path.replace(/^\/yunapi/, ''),
      },
    },
  },
})
