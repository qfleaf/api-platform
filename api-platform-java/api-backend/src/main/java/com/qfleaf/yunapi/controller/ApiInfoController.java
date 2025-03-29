package com.qfleaf.yunapi.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qfleaf.web.common.CommonResponse;
import com.qfleaf.web.common.ResponseCode;
import com.qfleaf.web.utils.ResultUtil;
import com.qfleaf.yunapi.annotation.Authorized;
import com.qfleaf.yunapi.open.model.dto.api.ApiInfoCreateRequest;
import com.qfleaf.yunapi.open.model.dto.api.ApiInfoDebugRequest;
import com.qfleaf.yunapi.open.model.dto.api.ApiInfoQueryRequest;
import com.qfleaf.yunapi.open.model.dto.api.ApiInfoUpdateRequest;
import com.qfleaf.yunapi.open.model.vo.ApiInfoDebugResponse;
import com.qfleaf.yunapi.open.model.vo.ApiInfoPageVO;
import com.qfleaf.yunapi.open.model.vo.ApiInfoVO;
import com.qfleaf.yunapi.service.ApiInfoService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class ApiInfoController {
    private final ApiInfoService apiInfoService;

    public ApiInfoController(ApiInfoService apiInfoService) {
        this.apiInfoService = apiInfoService;
    }

    @GetMapping("list")
    public CommonResponse<IPage<ApiInfoPageVO>> listApiInfo(@Valid @ParameterObject ApiInfoQueryRequest request) {
        IPage<ApiInfoPageVO> page = apiInfoService.findPageVo(request);
        return ResultUtil.success(ResponseCode.SUCCESS, page);
    }

    @GetMapping("{id}")
    public CommonResponse<ApiInfoVO> getApiInfoById(@PathVariable("id") Long id) {
        ApiInfoVO api = apiInfoService.findById(id);
        return ResultUtil.success(ResponseCode.SUCCESS, api);
    }

    // 调试接口
    @PostMapping("debug")
    public CommonResponse<ApiInfoDebugResponse> debugApiInfo(@Valid @RequestBody ApiInfoDebugRequest request) {
        ApiInfoDebugResponse result = apiInfoService.debug(request);
        return ResultUtil.success(ResponseCode.SUCCESS, result);
    }

    // region 管理员功能
    // 添加接口
    @Authorized
    @PostMapping
    public CommonResponse<Void> addApiInfo(@Valid @RequestBody ApiInfoCreateRequest request) {
        apiInfoService.createApi(request);
        return ResultUtil.success(ResponseCode.SUCCESS, null);
    }

    // 修改接口
    @Authorized
    @PutMapping
    public CommonResponse<Void> modifyApiInfo(@Valid @RequestBody ApiInfoUpdateRequest request) {
        apiInfoService.updateById(request);
        return ResultUtil.success(ResponseCode.SUCCESS, null);
    }

    // 删除接口
    @Authorized
    @DeleteMapping("{id}")
    public CommonResponse<Void> deleteApiInfo(@PathVariable("id") Long id) {
        apiInfoService.removeById(id);
        return ResultUtil.success(ResponseCode.SUCCESS, null);
    }

    // 发布接口
    @Authorized
    @PutMapping("enable/{id}")
    public CommonResponse<Void> enableApiInfo(@PathVariable("id") Long id) {
        apiInfoService.enable(id);
        return ResultUtil.success(ResponseCode.SUCCESS, null);
    }

    // 停用接口
    @Authorized
    @PutMapping("disable/{id}")
    public CommonResponse<Void> disableApi(@PathVariable("id") Long id) {
        apiInfoService.disable(id);
        return ResultUtil.success(ResponseCode.SUCCESS, null);
    }
    // endregion
}
