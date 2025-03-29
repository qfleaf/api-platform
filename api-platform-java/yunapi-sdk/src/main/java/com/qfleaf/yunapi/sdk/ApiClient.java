package com.qfleaf.yunapi.sdk;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class ApiClient {
    private final String gatewayUrl;
    private final RestTemplate restTemplate;

    public ApiClient(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
        this.restTemplate = new RestTemplate();
    }

    public ApiResponse call(ApiRequest request) {
        String url = gatewayUrl + request.getPath();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request.getParams());
        ResponseEntity<String> response = restTemplate.exchange(url, request.getMethod(), entity, String.class);
        return new ApiResponse(response.getStatusCode().value(), response.getBody());
    }
}