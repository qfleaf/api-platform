package com.qfleaf.yunapi.model.vo;

import lombok.Data;

@Data
public class UserLoginResponse {
    private String token;
    private String currentAuthority;
}
