package com.qfleaf.yunapi.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class ApiInfoPageVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
    private String endpoint;
    private String method;
    private Boolean status;
}
