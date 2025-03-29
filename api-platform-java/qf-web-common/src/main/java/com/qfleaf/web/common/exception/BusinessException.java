package com.qfleaf.web.common.exception;

import com.qfleaf.web.common.ResponseCode;
import lombok.Getter;
import org.springframework.util.StringUtils;

public class BusinessException extends RuntimeException {
    @Getter
    private final ResponseCode responseCode;
    private final String description;

    public BusinessException(ResponseCode responseCode, String description) {
        super(responseCode.getMsg());
        this.responseCode = responseCode;
        this.description = description;
    }

    public String getDescription() {
        if (StringUtils.hasLength(description)) {
            return description;
        }
        return ResponseCode.ERROR.getMsg();
    }
}
