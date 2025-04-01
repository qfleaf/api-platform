package com.qfleaf.yunapi.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public class LoginUserVO implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String username;
    private String email;
    private String role;
    private Boolean status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date updatedAt;
    @Serial
    private static final long serialVersionUID = 1L;
}
