package com.qfleaf.yunapi.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName api_info
 */
@TableName(value ="api_info")
@Data
public class ApiInfo implements Serializable {
    private Long id;

    private String name;

    private String description;

    private String endpoint;

    private String method;

    private String version;

    private Boolean status;

    private Date deletedAt;

    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;

    @Serial
    private static final long serialVersionUID = 1L;
}