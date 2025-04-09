package com.qfleaf.yunapi.sdk.utils;

import org.springframework.web.util.UriComponentsBuilder;
import java.util.Map;

public final class QueryParamUtil {
    public static String mapToQueryParams(String baseUrl, Map<String, Object> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl);
        params.forEach(builder::queryParam);
        return builder.toUriString();
    }
}