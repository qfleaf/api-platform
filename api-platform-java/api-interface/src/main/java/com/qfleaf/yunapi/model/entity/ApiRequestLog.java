package com.qfleaf.yunapi.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @TableName api_request_log
 */
@TableName(value ="api_request_log")
@Data
public class ApiRequestLog implements Serializable {
    private Long id;

    private Long apiId;

    private String authKey;

    private Date requestTime;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String, ?> requestParams;

    private Integer responseStatus;

    private Long responseTime;

    private String ipAddress;

    @TableLogic
    private Date deletedAt;

    @Serial
    private static final long serialVersionUID = 1L;
}