package com.qfleaf.yunapi.model.dto.app;

import com.qfleaf.yunapi.model.Pageable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class AppInfoQueryRequest extends Pageable {
    private Long userId;
    private String name;
    private String description;
    private Date createdAt;
}
