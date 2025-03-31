import axios from 'axios';
import type { AxiosInstance, AxiosRequestConfig, AxiosResponse, InternalAxiosRequestConfig } from 'axios';
import { message } from 'ant-design-vue';
import type { ApiResponse } from '../types';

// 创建 Axios 实例
const service: AxiosInstance = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL, // 从环境变量获取基础地址
    timeout: 10000, // 请求超时时间
    withCredentials: true // 跨域时携带凭证
});

// 请求拦截器
service.interceptors.request.use(
    (config: InternalAxiosRequestConfig) => {
        const token = localStorage.getItem('token');
        if (token) {
            config.headers = config.headers || {};
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

// 响应拦截器
service.interceptors.response.use(
    (response: AxiosResponse<ApiResponse>) => {
        const { code, msg, data } = response.data;
        if (code !== 20000) {
            message.error(msg || '请求失败');
            return Promise.reject(new Error(msg || 'Error'));
        }
        return data;
    },
    (error) => {
        if (error.response) {
            const { status, data } = error.response;
            switch (status) {
                case 401:
                    message.error('未授权或登录已过期，请重新登录');
                    localStorage.removeItem('token');
                    window.location.href = '/login';
                    break;
                case 403:
                    message.error('没有权限访问');
                    break;
                case 500:
                    message.error('服务器错误');
                    break;
                default:
                    message.error(data?.message || '请求出错');
            }
        } else {
            message.error('网络连接失败');
        }
        return Promise.reject(error);
    }
);

// 封装常用方法
export const request = {
    get<T = any>(url: string, params?: Record<string, any>, config?: AxiosRequestConfig) {
        return service.get<T>(url, { ...params, ...config });
    },
    post<T = any>(url: string, data?: Record<string, any>, config?: AxiosRequestConfig) {
        return service.post<T>(url, data, config);
    },
    put<T = any>(url: string, data?: Record<string, any>, config?: AxiosRequestConfig) {
        return service.put<T>(url, data, config);
    },
    delete<T = any>(url: string, config?: AxiosRequestConfig) {
        return service.delete<T>(url, config);
    }
};

// export const request = service

export default request;