package com.qfleaf.yunapi.model.dto.api;

import com.qfleaf.yunapi.model.Pageable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApiInfoQueryRequest extends Pageable {
    private String name;
    private String description;
    private String endpoint;
    private String method;
    private Boolean status;
}
