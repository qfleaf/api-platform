package com.qfleaf.yunapi.convert;

import com.qfleaf.yunapi.model.entity.ApiInfo;
import com.qfleaf.yunapi.model.dto.api.ApiInfoCreateRequest;
import com.qfleaf.yunapi.model.dto.api.ApiInfoUpdateRequest;
import org.springframework.stereotype.Component;

@Component
public class ApiInfoConvert {
    public ApiInfo toEntity(ApiInfoCreateRequest request) {
        ApiInfo apiInfo = new ApiInfo();
        apiInfo.setName(request.getName());
        apiInfo.setDescription(request.getDescription());
        apiInfo.setEndpoint(request.getEndpoint());
        apiInfo.setMethod(request.getMethod());
        apiInfo.setVersion(request.getVersion());
        apiInfo.setStatus(request.getStatus());
        return apiInfo;
    }

    public ApiInfo toEntity(ApiInfoUpdateRequest request) {
        ApiInfo apiInfo = new ApiInfo();
        apiInfo.setId(request.getId());
        apiInfo.setName(request.getName());
        apiInfo.setDescription(request.getDescription());
        apiInfo.setEndpoint(request.getEndpoint());
        apiInfo.setMethod(request.getMethod());
        apiInfo.setVersion(request.getVersion());
        apiInfo.setStatus(request.getStatus());
        return apiInfo;
    }
}
