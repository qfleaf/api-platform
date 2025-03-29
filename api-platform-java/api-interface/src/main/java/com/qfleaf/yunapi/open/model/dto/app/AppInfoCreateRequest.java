package com.qfleaf.yunapi.open.model.dto.app;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AppInfoCreateRequest {
    @NotNull(message = "所有者ID不能为空")
    private Long userId;
    @NotBlank(message = "应用名称不能为空")
    @Size(min = 1, max = 32, message = "应用名称长度只能在1~32个字符")
    private String name;
    @Size(max = 200, message = "应用描述长度最多为200字符")
    private String description;
}
