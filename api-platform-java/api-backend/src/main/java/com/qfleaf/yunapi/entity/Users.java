package com.qfleaf.yunapi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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

    private Date deletedAt;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}