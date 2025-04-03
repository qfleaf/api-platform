// 定义响应数据的通用结构
export interface ApiResponse<T = any> {
    code: number;
    msg: string;
    data: T;
}
// 定义分页数据的结构
export interface Page<T> {
    total: number; // 总记录数
    size: number; // 每页记录数
    current: number; // 当前页码
    records: T[]; // 当前页数据
    pages: number; // 总页数
}
// =========================接口相关=========================
// 定义接口分页响应视图
export type ApiInfoPageVO = {
    id: string;
    name: string;
    endpoint: string;
    method: string;
    status: boolean;
};
// 定义接口响应视图
export type ApiInfoVO = {
    id: string;
    name: string;
    description: string;
    endpoint: string;
    method: string;
    version: string;
    status: boolean;
    createdAt: Date;
    updatedAt: Date;
}
// 定义接口调试参数结构
export type ApiDebugRequest = {
    id: string;
    params: Record<string, any>; // 请求参数
}
// =========================用户相关=========================
// 定义登录用户的结构
export type LoginUserVO = {
    id: string;
    username: string;
    email: string;
    role: string;
    status: boolean;
    createdAt: Date;
    updatedAt: Date;
}
export type UserLoginRequest = {
    username: string;
    password: string;
    loginType: string;
}
export type UserLoginResponse = {
    token: string;
    currentAuthority: string;
}
// 定义登录请求的参数结构