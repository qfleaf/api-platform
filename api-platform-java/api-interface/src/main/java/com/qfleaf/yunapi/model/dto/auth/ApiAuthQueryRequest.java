package com.qfleaf.yunapi.model.dto.auth;

import com.qfleaf.yunapi.model.Pageable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApiAuthQueryRequest extends Pageable {
    @NotNull(message = "应用ID不能为空")
    private Long appId;
}
