import request from "../utils/request";
import type { ApiDebugRequest, ApiInfoPageVO, ApiInfoVO, Page } from "../types/";

export const getApiInfoPage = async (params: Record<string, any>): Promise<Page<ApiInfoPageVO>> => {
    return request.get('/yunapi/api/list', { params }) as unknown as Promise<Page<ApiInfoPageVO>>;
}

export const getApiInfoById = async (id: string): Promise<ApiInfoVO> => {
    return request.get(`/yunapi/api/${id}`) as unknown as Promise<ApiInfoVO>;
}

export const debugApi = async (params: ApiDebugRequest): Promise<any> => {
    return request.post('/yunapi/api/debug', params) as unknown as Promise<ApiInfoVO>;
}