package com.qfleaf.yunapi.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

@Data
public class ApiRequestLogVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String apiName;
    private String requestUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date requestTime;
    private Object requestParams;
    private Integer responseStatus;
    private Long responseTime;
    private String ipAddress;
}
