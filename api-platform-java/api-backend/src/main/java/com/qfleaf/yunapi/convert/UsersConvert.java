package com.qfleaf.yunapi.convert;

import com.qfleaf.web.utils.PasswordUtil;
import com.qfleaf.yunapi.entity.Users;
import com.qfleaf.yunapi.open.model.dto.user.UserRegisterRequest;
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
        loginUserVO.setCreatedAt(user.getCreatedAt());
        loginUserVO.setUpdatedAt(user.getUpdatedAt());
        return loginUserVO;
    }

    public Users toEntity(UserRegisterRequest userRegisterRequest) {
        Users user = new Users();
        user.setUsername(userRegisterRequest.getUsername());
        user.setPassword(PasswordUtil.encode(userRegisterRequest.getPassword()));
        user.setEmail(userRegisterRequest.getEmail());
        return user;
    }
}
