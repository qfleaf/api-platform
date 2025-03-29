package com.qfleaf.yunapi.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName api_request_log
 */
@TableName(value ="api_request_log")
@Data
public class ApiRequestLog implements Serializable {
    private Long id;

    private Long apiId;

    private Long userId;

    private Date requestTime;

    private Object requestParams;

    private Integer responseStatus;

    private Long responseTime;

    private String ipAddress;

    @TableLogic
    private Date deletedAt;

    @Serial
    private static final long serialVersionUID = 1L;
}