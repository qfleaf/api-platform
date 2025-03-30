package com.qfleaf.yunapi.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ApiAuthVO {
    private Long id;

    private String authKey;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expireAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createdAt;
}
