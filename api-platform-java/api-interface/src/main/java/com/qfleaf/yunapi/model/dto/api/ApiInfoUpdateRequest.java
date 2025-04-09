package com.qfleaf.yunapi.model.dto.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ApiInfoUpdateRequest {
    @NotNull(message = "接口ID不能为空")
    private Long id;
    @NotBlank(message = "接口名称不能为空")
    private String name;
    @Size(max = 1000, message = "接口描述不能超过500字")
    private String description;
    @NotBlank(message = "接口访问地址不能为空")
    private String endpoint;
    @NotBlank(message = "接口访问方法不能为空")
    private String method;
    private String version;
    private Boolean status = false;
}
