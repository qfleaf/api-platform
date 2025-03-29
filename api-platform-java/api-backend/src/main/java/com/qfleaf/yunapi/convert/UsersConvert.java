package com.qfleaf.yunapi.convert;

import com.qfleaf.yunapi.entity.Users;
import com.qfleaf.yunapi.open.model.vo.LoginUserVO;
import org.springframework.stereotype.Component;

@Component
public class UsersConvert {
    public LoginUserVO toLoginUserVO(Users user) {
        LoginUserVO loginUserVO = new LoginUserVO();
        loginUserVO.setId(user.getId());
        loginUserVO.setUsername(user.getUsername());
        loginUserVO.setEmail(user.getEmail());
        loginUserVO.setRole(user.getRole());
        loginUserVO.setStatus(user.getStatus());
        loginUserVO.setDeletedAt(user.getDeletedAt());
        loginUserVO.setCreatedAt(user.getCreatedAt());
        loginUserVO.setUpdatedAt(user.getUpdatedAt());
        return loginUserVO;
    }
}
