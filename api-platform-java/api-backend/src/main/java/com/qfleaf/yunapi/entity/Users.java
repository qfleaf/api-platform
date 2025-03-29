package com.qfleaf.yunapi.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName users
 */
@TableName(value ="users")
@Data
public class Users implements Serializable {
    private Long id;

    private String username;

    private String password;

    private String email;

    private String role;

    private Boolean status;

    @TableLogic
    private Date deletedAt;

    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;

    @Serial
    private static final long serialVersionUID = 1L;
}