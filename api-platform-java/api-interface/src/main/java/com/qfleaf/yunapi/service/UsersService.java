package com.qfleaf.yunapi.service;

import com.qfleaf.yunapi.model.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qfleaf.yunapi.model.dto.user.UserLoginRequest;
import com.qfleaf.yunapi.model.dto.user.UserRegisterRequest;
import com.qfleaf.yunapi.model.vo.LoginUserVO;
import com.qfleaf.yunapi.model.vo.UserLoginResponse;

/**
* @author qianfang
* @description 针对表【users(用户表，存储平台用户的基本信息)】的数据库操作Service
* @createDate 2025-03-29 12:33:27
*/
public interface UsersService extends IService<Users> {
    LoginUserVO getCurrentUser();
    void register(UserRegisterRequest userRegisterRequest);
    UserLoginResponse login(UserLoginRequest loginRequest);
    void logout();
}
