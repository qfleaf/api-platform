package com.qfleaf.yunapi.sdk;

import lombok.Data;
import org.springframework.http.HttpMethod;

import java.util.Map;

@Data
public class ApiRequest {
    private String path;                 // 请求路径
    private HttpMethod method;           // 请求方式
    private Map<String, Object> params;  // 参数

    public ApiRequest(String path, HttpMethod method, Map<String, Object> params) {
        this.path = path;
        this.method = method;
        this.params = params;
    }
}