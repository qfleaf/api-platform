package com.qfleaf.yunapi.open.model.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public class LoginUserVO implements Serializable {
    private Long id;
    private String username;
    private String email;
    private String role;
    private Boolean status;
    private Date deletedAt;
    private Date createdAt;
    private Date updatedAt;
    @Serial
    private static final long serialVersionUID = 1L;
}
