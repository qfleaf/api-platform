package com.qfleaf.yunapi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}