package com.qfleaf.yunapi.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

@Data
public class ApiAuthVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String authKey;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expireAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createdAt;
}
