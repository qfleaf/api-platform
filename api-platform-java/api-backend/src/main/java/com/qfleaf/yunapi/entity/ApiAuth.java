package com.qfleaf.yunapi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName api_auth
 */
@TableName(value ="api_auth")
@Data
public class ApiAuth implements Serializable {
    private Long id;

    private Long userId;

    private Long apiId;

    private String authKey;

    private Date expireAt;

    private Date createdAt;

    private Date deletedAt;

    private static final long serialVersionUID = 1L;
}