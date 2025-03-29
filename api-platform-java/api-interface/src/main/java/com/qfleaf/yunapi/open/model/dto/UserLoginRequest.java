package com.qfleaf.yunapi.open.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginRequest {
    @NotBlank(message = "请输入用户名")
    private String username;
    @NotBlank(message = "请输入密码")
    private String password;
    @NotBlank(message = "登陆方式不能为空")
    private String loginType;
}
