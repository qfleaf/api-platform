import type { LoginUserVO, UserLoginRequest, UserLoginResponse } from "../types";
import request from "../utils/request";

export const doUserLogin = async (params: UserLoginRequest): Promise<UserLoginResponse> => {
    return request.post('/yunapi/user/login', params) as unknown as UserLoginResponse;
}

export const doUserLogout = async (): Promise<void> => {
    return request.post('/yunapi/user/logout') as unknown as void;
}

export const getUserInfo = async (): Promise<LoginUserVO> => {
    return request.get('/yunapi/user/info') as unknown as LoginUserVO;
}