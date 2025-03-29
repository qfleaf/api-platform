package com.qfleaf.yunapi.open.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AppInfoVO {
    private Long id;
    private String developer;
    private String name;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createdAt;
}
