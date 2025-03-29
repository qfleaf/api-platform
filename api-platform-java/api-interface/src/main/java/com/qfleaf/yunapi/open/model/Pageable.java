package com.qfleaf.yunapi.open.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public abstract class Pageable {
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码不能小于1")
    protected Integer current = 1;
    @NotNull(message = "页大小不能为空")
    @Min(value = 5, message = "页大小不能小于5")
    protected Integer size = 5;
}
