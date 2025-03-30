package com.qfleaf.yunapi.open.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ApiRequestLogPageVO {
    private Long id;
    private String apiName;
    private String requestUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date requestTime;
    private Integer responseStatus;
    private Long responseTime;
}
