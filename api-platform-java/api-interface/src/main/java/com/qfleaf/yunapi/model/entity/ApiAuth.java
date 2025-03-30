package com.qfleaf.yunapi.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
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

    private Long appId;

    private String authKey;

    private Date expireAt;

    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;

    @TableLogic
    private Date deletedAt;

    @Serial
    private static final long serialVersionUID = 1L;
}