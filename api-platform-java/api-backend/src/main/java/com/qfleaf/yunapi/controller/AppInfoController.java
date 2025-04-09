package com.qfleaf.yunapi.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qfleaf.web.common.CommonResponse;
import com.qfleaf.web.common.ResponseCode;
import com.qfleaf.web.common.exception.BusinessException;
import com.qfleaf.web.utils.ResultUtil;
import com.qfleaf.yunapi.annotation.Authorized;
import com.qfleaf.yunapi.model.entity.AppInfo;
import com.qfleaf.yunapi.model.dto.app.AppInfoCreateRequest;
import com.qfleaf.yunapi.model.dto.app.AppInfoEditRequest;
import com.qfleaf.yunapi.model.dto.app.AppInfoQueryRequest;
import com.qfleaf.yunapi.model.vo.AppInfoPageVO;
import com.qfleaf.yunapi.model.vo.AppInfoVO;
import com.qfleaf.yunapi.service.AppInfoService;
import com.qfleaf.yunapi.service.UsersService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("app")
public class AppInfoController {
    private final AppInfoService appInfoService;
    private final UsersService usersService;

    public AppInfoController(AppInfoService appInfoService, UsersService usersService) {
        this.appInfoService = appInfoService;
        this.usersService = usersService;
    }

    // 管理员获取所有app列表
    @Authorized
    @GetMapping("list")
    public CommonResponse<IPage<AppInfoPageVO>> listAppInfo(@Valid @ParameterObject AppInfoQueryRequest request) {
        IPage<AppInfoPageVO> page = appInfoService.findPageVo(request);
        return ResultUtil.success(ResponseCode.SUCCESS, page);
    }

    // 获取用户自己的app列表
    @GetMapping("list/self")
    public CommonResponse<IPage<AppInfoPageVO>> listAppInfoSelf(@Valid @ParameterObject AppInfoQueryRequest request) {
        request.setUserId(usersService.getCurrentUser().getId());
        IPage<AppInfoPageVO> page = appInfoService.findPageVo(request);
        return ResultUtil.success(ResponseCode.SUCCESS, page);
    }

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
        AppInfo appInfo = appInfoService.getById(id);
        if (appInfo!=null && !Objects.equals(appInfo.getUserId(), usersService.getCurrentUser().getId())) {
            throw new BusinessException(ResponseCode.BAD_AUTH, "没有此应用的处理权限");
        }
        appInfoService.removeById(id);
        return ResultUtil.success(ResponseCode.SUCCESS, null);
    }
}
