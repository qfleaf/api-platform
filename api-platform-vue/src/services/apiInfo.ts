import request from "../utils/request";
import type { ApiInfoPageVO, Page } from "../types/";

export const getApiInfoPage = async (params: Record<string, any> | undefined): Promise<Page<ApiInfoPageVO>> => {
    return request.get('/yunapi/api/list', params) as unknown as Promise<Page<ApiInfoPageVO>>;
}