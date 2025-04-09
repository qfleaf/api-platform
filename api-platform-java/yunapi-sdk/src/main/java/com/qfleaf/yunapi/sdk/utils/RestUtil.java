package com.qfleaf.yunapi.sdk.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public final class RestUtil {
    private static final RestTemplate restTemplate = new RestTemplate();

    public static ResponseEntity<String> call(String url, HttpMethod httpMethod, HttpEntity<?> mapHttpEntity, Map<String, ?> params) {
        if (httpMethod.equals(HttpMethod.GET)) {
            return restTemplate.exchange(
                    url,
                    httpMethod,
                    HttpEntity.EMPTY,
                    String.class,
                    params
            );
        } else {
            return restTemplate.exchange(
                    url,
                    httpMethod,
                    mapHttpEntity,
                    String.class
            );
        }
    }
}
