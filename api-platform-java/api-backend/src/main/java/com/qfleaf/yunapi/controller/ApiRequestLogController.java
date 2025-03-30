package com.qfleaf.yunapi.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qfleaf.web.common.CommonResponse;
import com.qfleaf.web.common.ResponseCode;
import com.qfleaf.web.utils.ResultUtil;
import com.qfleaf.yunapi.annotation.Authorized;
import com.qfleaf.yunapi.open.model.Pageable;
import com.qfleaf.yunapi.open.model.vo.ApiRequestLogPageVO;
import com.qfleaf.yunapi.open.model.vo.ApiRequestLogVO;
import com.qfleaf.yunapi.service.ApiRequestLogService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("log")
public class ApiRequestLogController {
    private final ApiRequestLogService apiRequestLogService;

    public ApiRequestLogController(ApiRequestLogService apiRequestLogService) {
        this.apiRequestLogService = apiRequestLogService;
    }

    @Authorized
    @GetMapping("list")
    public CommonResponse<IPage<ApiRequestLogPageVO>> listLogPageVo(@Valid @ParameterObject Pageable pageable) {
        IPage<ApiRequestLogPageVO> page = apiRequestLogService.findPageVo(pageable);
        return ResultUtil.success(ResponseCode.SUCCESS, page);
    }

    @Authorized
    @GetMapping("{id}")
    public CommonResponse<ApiRequestLogVO> getLog(@PathVariable("id") Long id) {
        ApiRequestLogVO log = apiRequestLogService.findVoById(id);
        return ResultUtil.success(ResponseCode.SUCCESS, log);
    }

    // todo日志可视化数据
}
