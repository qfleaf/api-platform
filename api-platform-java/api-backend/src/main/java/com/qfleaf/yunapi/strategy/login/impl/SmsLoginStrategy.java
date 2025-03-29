package com.qfleaf.yunapi.strategy.login.impl;

import com.qfleaf.web.common.ResponseCode;
import com.qfleaf.web.common.exception.BusinessException;
import com.qfleaf.yunapi.open.model.dto.user.UserLoginRequest;
import com.qfleaf.yunapi.open.model.vo.LoginUserVO;
import com.qfleaf.yunapi.strategy.login.Constants;
import com.qfleaf.yunapi.strategy.login.LoginStrategy;
import com.qfleaf.yunapi.strategy.login.annotation.LoginType;

@LoginType(Constants.LoginType.SMS)
public class SmsLoginStrategy implements LoginStrategy {
    @Override
    public LoginUserVO doLogin(UserLoginRequest userLoginRequest) {
        // todo 实现手机验证码登陆策略
        throw new BusinessException(ResponseCode.BAD_FUNCTION, "功能未启用");
    }
}
