package com.qfleaf.yunapi.controller;

import com.qfleaf.web.common.CommonResponse;
import com.qfleaf.web.common.ResponseCode;
import com.qfleaf.web.utils.ResultUtil;
import com.qfleaf.yunapi.model.dto.user.UserLoginRequest;
import com.qfleaf.yunapi.model.dto.user.UserRegisterRequest;
import com.qfleaf.yunapi.model.vo.LoginUserVO;
import com.qfleaf.yunapi.model.vo.UserLoginResponse;
import com.qfleaf.yunapi.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("current")
    public CommonResponse<LoginUserVO> getCurrentUser() {
        LoginUserVO loginUserVO = usersService.getCurrentUser();
        return ResultUtil.success(ResponseCode.SUCCESS, loginUserVO);
    }

    @PostMapping("register")
    public CommonResponse<Void> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        usersService.register(userRegisterRequest);
        return ResultUtil.success(ResponseCode.SUCCESS_REGISTER, null);
    }

    @PostMapping("login")
    public CommonResponse<UserLoginResponse> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        UserLoginResponse result = usersService.login(userLoginRequest);
        return ResultUtil.success(ResponseCode.SUCCESS_LOGIN, result);
    }

    @PostMapping("logout")
    public CommonResponse<Void> logout() {
        usersService.logout();
        return ResultUtil.success(ResponseCode.SUCCESS, null);
    }
}
