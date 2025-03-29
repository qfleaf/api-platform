package com.qfleaf.yunapi.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qfleaf.web.common.CommonResponse;
import com.qfleaf.web.common.ResponseCode;
import com.qfleaf.web.utils.ResultUtil;
import com.qfleaf.yunapi.annotation.Authorized;
import com.qfleaf.yunapi.open.model.dto.app.AppInfoCreateRequest;
import com.qfleaf.yunapi.open.model.dto.app.AppInfoEditRequest;
import com.qfleaf.yunapi.open.model.dto.app.AppInfoQueryRequest;
import com.qfleaf.yunapi.open.model.vo.AppInfoPageVO;
import com.qfleaf.yunapi.open.model.vo.AppInfoVO;
import com.qfleaf.yunapi.service.AppInfoService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("app")
public class AppInfoController {
    private final AppInfoService appInfoService;

    public AppInfoController(AppInfoService appInfoService) {
        this.appInfoService = appInfoService;
    }

    @Authorized
    @GetMapping("list")
    public CommonResponse<IPage<AppInfoPageVO>> listAppInfo(@Valid @ParameterObject AppInfoQueryRequest request) {
        IPage<AppInfoPageVO> page = appInfoService.findPageVo(request);
        return ResultUtil.success(ResponseCode.SUCCESS, page);
    }
    // todo 增加一个获取当前用户app信息列表的接口

    @GetMapping("{id}")
    public CommonResponse<AppInfoVO> getAppInfoById(@PathVariable("id") Long id) {
        AppInfoVO app = appInfoService.findById(id);
        return ResultUtil.success(ResponseCode.SUCCESS, app);
    }

    @PostMapping
    public CommonResponse<Void> createApp(@Valid @RequestBody AppInfoCreateRequest request) {
        appInfoService.saveApp(request);
        return ResultUtil.success(ResponseCode.SUCCESS, null);
    }

    @PutMapping
    public CommonResponse<Void> modifyApp(@Valid @RequestBody AppInfoEditRequest request) {
        appInfoService.updateById(request);
        return ResultUtil.success(ResponseCode.SUCCESS, null);
    }

    @DeleteMapping("{id}")
    public CommonResponse<Void> deleteApp(@PathVariable("id") Long id) {
        appInfoService.removeById(id);
        return ResultUtil.success(ResponseCode.SUCCESS, null);
    }
}
