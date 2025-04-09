package com.qfleaf.yunapi.strategy.login.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qfleaf.web.common.ResponseCode;
import com.qfleaf.web.common.exception.BusinessException;
import com.qfleaf.web.utils.PasswordUtil;
import com.qfleaf.yunapi.convert.UsersConvert;
import com.qfleaf.yunapi.model.entity.Users;
import com.qfleaf.yunapi.mapper.UsersMapper;
import com.qfleaf.yunapi.model.dto.user.UserLoginRequest;
import com.qfleaf.yunapi.model.vo.LoginUserVO;
import com.qfleaf.yunapi.strategy.login.Constants;
import com.qfleaf.yunapi.strategy.login.LoginStrategy;
import com.qfleaf.yunapi.strategy.login.annotation.LoginType;
import org.springframework.beans.factory.annotation.Autowired;

@LoginType(Constants.LoginType.ACCOUNT)
public class AccountLoginStrategy implements LoginStrategy {
    private final UsersMapper usersMapper;
    private final UsersConvert usersConvert;

    @Autowired
    public AccountLoginStrategy(UsersMapper usersMapper, UsersConvert usersConvert) {
        this.usersMapper = usersMapper;
        this.usersConvert = usersConvert;
    }

    @Override
    public LoginUserVO doLogin(UserLoginRequest userLoginRequest) {
        Users user = usersMapper.selectOne(new LambdaQueryWrapper<Users>()
                .eq(Users::getUsername, userLoginRequest.getUsername()));
        if (user == null) {
            throw new BusinessException(ResponseCode.BAD_PARAMS, "用户不存在或已被禁用");
        }
        boolean matches = PasswordUtil.matches(userLoginRequest.getPassword(), user.getPassword());
        if (!matches) {
            throw new BusinessException(ResponseCode.BAD_AUTH, "用户名或密码错误");
        }
        return usersConvert.toLoginUserVO(user);
    }
}
