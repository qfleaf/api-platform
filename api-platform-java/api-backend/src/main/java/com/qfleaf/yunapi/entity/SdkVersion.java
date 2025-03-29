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
 * @TableName sdk_version
 */
@TableName(value ="sdk_version")
@Data
public class SdkVersion implements Serializable {
    private Long id;

    private String name;

    private String version;

    private String downloadUrl;

    private String releaseNotes;

    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;

    @TableLogic
    private Date deletedAt;

    @Serial
    private static final long serialVersionUID = 1L;
}