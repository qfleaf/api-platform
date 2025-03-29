package com.qfleaf.yunapi.open.model.dto.api;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

@Data
public class ApiInfoDebugRequest {
    @NotNull(message = "接口ID不能为空")
    private Long id;
    private Map<String, Object> params;
}
