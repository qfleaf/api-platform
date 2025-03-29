package com.qfleaf.yunapi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName app_info
 */
@TableName(value ="app_info")
@Data
public class AppInfo implements Serializable {
    private Long id;

    private Long userId;

    private String name;

    private String description;

    private Date createdAt;

    private Date deletedAt;

    private static final long serialVersionUID = 1L;
}