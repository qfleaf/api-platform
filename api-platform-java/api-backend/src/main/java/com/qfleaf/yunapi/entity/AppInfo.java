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
 * @TableName app_info
 */
@TableName(value ="app_info")
@Data
public class AppInfo implements Serializable {
    private Long id;

    private Long userId;

    private String name;

    private String description;

    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;

    @TableLogic
    private Date deletedAt;

    @Serial
    private static final long serialVersionUID = 1L;
}