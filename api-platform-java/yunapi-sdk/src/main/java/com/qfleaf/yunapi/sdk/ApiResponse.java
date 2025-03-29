package com.qfleaf.yunapi.sdk;

import lombok.Data;

@Data
public class ApiResponse {
    private int status;
    private String body;

    public ApiResponse(int status, String body) {
        this.status = status;
        this.body = body;
    }
}