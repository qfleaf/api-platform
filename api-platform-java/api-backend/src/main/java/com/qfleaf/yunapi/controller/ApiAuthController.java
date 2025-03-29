package com.qfleaf.yunapi.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qfleaf.web.common.CommonResponse;
import com.qfleaf.web.common.ResponseCode;
import com.qfleaf.web.utils.ResultUtil;
import com.qfleaf.yunapi.annotation.Authorized;
import com.qfleaf.yunapi.open.model.dto.auth.ApiAuthCreateRequest;
import com.qfleaf.yunapi.open.model.dto.auth.ApiAuthQueryRequest;
import com.qfleaf.yunapi.open.model.vo.ApiAuthPageVO;
import com.qfleaf.yunapi.open.model.vo.ApiAuthVO;
import com.qfleaf.yunapi.service.ApiAuthService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class ApiAuthController {
    private final ApiAuthService apiAuthService;

    public ApiAuthController(ApiAuthService apiAuthService) {
        this.apiAuthService = apiAuthService;
    }

    // 管理员获取某app下的key
    @Authorized
    @GetMapping("list")
    public CommonResponse<IPage<ApiAuthPageVO>> listApiAuth(@Valid @ParameterObject ApiAuthQueryRequest request) {
        IPage<ApiAuthPageVO> page = apiAuthService.findPageVo(request);
        return ResultUtil.success(ResponseCode.SUCCESS, page);
    }

    @Authorized
    @PostMapping
    public CommonResponse<Void> generateApiAuth(@Valid @RequestBody ApiAuthCreateRequest request) {
        apiAuthService.generateApiAuth(request);
        return ResultUtil.success(ResponseCode.SUCCESS, null);
    }

    @GetMapping("{id}")
    public CommonResponse<ApiAuthVO> getApiAuth(@PathVariable("id") Long id) {
        ApiAuthVO apiAuth = apiAuthService.findById(id);
        return ResultUtil.success(ResponseCode.SUCCESS, apiAuth);
    }
    // todo 用户通过购买获取auth_key
}
