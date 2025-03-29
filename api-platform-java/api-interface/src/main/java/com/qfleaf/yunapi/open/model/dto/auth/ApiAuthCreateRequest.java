package com.qfleaf.yunapi.open.model.dto.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ApiAuthCreateRequest {
    private Long userId;
    @NotNull(message = "应用ID不能为空")
    private Long appId;
}
