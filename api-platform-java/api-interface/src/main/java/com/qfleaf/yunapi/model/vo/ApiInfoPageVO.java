package com.qfleaf.yunapi.model.vo;

import lombok.Data;

@Data
public class ApiInfoPageVO {
    private Long id;
    private String name;
    private String endpoint;
    private String method;
    private Boolean status;
}
