package com.qfleaf.yunapi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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

    private Date createdAt;

    private Date deletedAt;

    private static final long serialVersionUID = 1L;
}