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
// 定义接口分页响应视图
export type ApiInfoPageVO = {
    id: number;
    name: string;
    endpoint: string;
    method: string;
    status: boolean;
};
// 定义登录用户的结构
// 该结构用于存储登录用户的信息
export type LoginUserVO = {
    id: number;
    username: string;
    email: string;
    role: string;
    status: boolean;
    createdAt: Date;
    updatedAt: Date;
}

// 定义登录请求的参数结构