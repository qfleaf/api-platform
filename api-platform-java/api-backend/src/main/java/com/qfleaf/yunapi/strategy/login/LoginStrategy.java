package com.qfleaf.yunapi.strategy.login;


import com.qfleaf.yunapi.open.model.dto.user.UserLoginRequest;
import com.qfleaf.yunapi.open.model.vo.LoginUserVO;

public interface LoginStrategy {
    /**
     * 执行登陆策略
     *
     * @param userLoginRequest 登陆请求参数
     * @return 登陆用户视图实体
     */
    LoginUserVO doLogin(UserLoginRequest userLoginRequest);
}
