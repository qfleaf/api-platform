package com.qfleaf.yunapi.open.model.vo;

import lombok.Data;

@Data
public class UserLoginResponse {
    private String token;
    private String currentAuthority;
}
