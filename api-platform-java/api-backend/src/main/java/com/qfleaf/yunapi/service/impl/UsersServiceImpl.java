package com.qfleaf.yunapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfleaf.web.common.constant.HttpConst;
import com.qfleaf.web.common.constant.RedisConst;
import com.qfleaf.web.token.JwtService;
import com.qfleaf.web.utils.RedisUtil;
import com.qfleaf.yunapi.convert.UsersConvert;
import com.qfleaf.yunapi.entity.Users;
import com.qfleaf.yunapi.mapper.UsersMapper;
import com.qfleaf.yunapi.open.model.dto.UserLoginRequest;
import com.qfleaf.yunapi.open.model.dto.UserRegisterRequest;
import com.qfleaf.yunapi.open.model.vo.LoginUserVO;
import com.qfleaf.yunapi.open.model.vo.UserLoginResponse;
import com.qfleaf.yunapi.service.UsersService;
import com.qfleaf.yunapi.strategy.login.LoginStrategy;
import com.qfleaf.yunapi.strategy.login.LoginStrategyManager;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
 * @author qianfang
 * @description 针对表【users(用户表，存储平台用户的基本信息)】的数据库操作Service实现
 * @createDate 2025-03-29 12:33:27
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
        implements UsersService {

    private final LoginStrategyManager loginStrategyManager;
    private final RedisUtil redisUtil;
    private final JwtService jwtService;
    private final HttpServletRequest httpServletRequest;
    private final UsersConvert usersConvert;

    public UsersServiceImpl(LoginStrategyManager loginStrategyManager, RedisUtil redisUtil, JwtService jwtService, HttpServletRequest httpServletRequest, UsersConvert usersConvert) {
        this.loginStrategyManager = loginStrategyManager;
        this.redisUtil = redisUtil;
        this.jwtService = jwtService;
        this.httpServletRequest = httpServletRequest;
        this.usersConvert = usersConvert;
    }

    @Override
    public LoginUserVO getCurrentUser() {
        String authorization = httpServletRequest.getHeader(HttpConst.USER_TOKEN_HEADER_KEY);
        return (LoginUserVO) redisUtil.get(RedisConst.LOGIN_USER + authorization);
    }

    @Override
    public void register(UserRegisterRequest userRegisterRequest) {
        Users user = usersConvert.toEntity(userRegisterRequest);
        baseMapper.insert(user);
    }

    @Override
    public UserLoginResponse login(UserLoginRequest loginRequest) {
        LoginStrategy loginStrategy = loginStrategyManager.getLoginStrategy(loginRequest.getLoginType());
        LoginUserVO loginUserVO = loginStrategy.doLogin(loginRequest);
        // 封装响应体
        String jwt = jwtService.createJWT(loginUserVO.getUsername());
        redisUtil.set(RedisConst.LOGIN_USER + jwt, loginUserVO);
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.setToken(jwt);
        userLoginResponse.setCurrentAuthority(loginUserVO.getRole());
        return userLoginResponse;
    }

    @Override
    public void logout() {
        String authorization = httpServletRequest.getHeader(HttpConst.USER_TOKEN_HEADER_KEY);
        redisUtil.delete(RedisConst.LOGIN_USER + authorization);
    }
}




