package com.qfleaf.yunapi.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

@Data
public class ApiInfoVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
    private String description;
    private String endpoint;
    private String method;
    private String version;
    private Boolean status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date updatedAt;
}
