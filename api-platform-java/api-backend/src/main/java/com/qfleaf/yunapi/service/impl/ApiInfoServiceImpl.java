package com.qfleaf.yunapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfleaf.web.common.ResponseCode;
import com.qfleaf.web.common.exception.BusinessException;
import com.qfleaf.yunapi.convert.ApiInfoConvert;
import com.qfleaf.yunapi.model.entity.ApiInfo;
import com.qfleaf.yunapi.mapper.ApiInfoMapper;
import com.qfleaf.yunapi.model.dto.api.ApiInfoCreateRequest;
import com.qfleaf.yunapi.model.dto.api.ApiInfoDebugRequest;
import com.qfleaf.yunapi.model.dto.api.ApiInfoQueryRequest;
import com.qfleaf.yunapi.model.dto.api.ApiInfoUpdateRequest;
import com.qfleaf.yunapi.model.vo.ApiInfoDebugResponse;
import com.qfleaf.yunapi.model.vo.ApiInfoPageVO;
import com.qfleaf.yunapi.model.vo.ApiInfoVO;
import com.qfleaf.yunapi.sdk.ApiClient;
import com.qfleaf.yunapi.sdk.ApiRequest;
import com.qfleaf.yunapi.sdk.ApiResponse;
import com.qfleaf.yunapi.service.ApiInfoService;
import com.qfleaf.yunapi.service.UsersService;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author qianfang
 * @description 针对表【api_info(API 信息表，存储平台所有对外提供的接口信息)】的数据库操作Service实现
 * @createDate 2025-03-29 12:33:27
 */
@Service
public class ApiInfoServiceImpl extends ServiceImpl<ApiInfoMapper, ApiInfo>
        implements ApiInfoService {

    private final ApiInfoConvert apiInfoConvert;
    private final UsersService usersService;

    public ApiInfoServiceImpl(ApiInfoConvert apiInfoConvert, UsersService usersService) {
        this.apiInfoConvert = apiInfoConvert;
        this.usersService = usersService;
    }

    @Override
    public IPage<ApiInfoPageVO> findPageVo(ApiInfoQueryRequest request) {
        IPage<ApiInfoPageVO> page = new Page<>(request.getCurrent(), request.getSize());
        LambdaQueryWrapper<ApiInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(StringUtils.hasLength(request.getName()), ApiInfo::getName, request.getName())
                .eq(StringUtils.hasLength(request.getDescription()), ApiInfo::getDescription, request.getDescription())
                .eq(StringUtils.hasLength(request.getEndpoint()), ApiInfo::getEndpoint, request.getEndpoint())
                .eq(StringUtils.hasLength(request.getMethod()), ApiInfo::getMethod, request.getMethod())
                .eq(request.getStatus() != null, ApiInfo::getStatus, request.getStatus())
                .isNull(ApiInfo::getDeletedAt);
        return baseMapper.selectPageVo(page, queryWrapper);
    }

    @Override
    public ApiInfoVO findById(Long id) {
        return baseMapper.selectVoById(id);
    }

    @Override
    public void createApi(ApiInfoCreateRequest request) {
        LambdaQueryWrapper<ApiInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ApiInfo::getName, request.getName());
        boolean exists = baseMapper.exists(queryWrapper);
        if (exists) {
            throw new BusinessException(ResponseCode.CONFLICT, "接口名称已存在");
        }
        ApiInfo entity = apiInfoConvert.toEntity(request);
        baseMapper.insert(entity);
    }

    @Override
    public void updateById(ApiInfoUpdateRequest request) {
        LambdaQueryWrapper<ApiInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(ApiInfo::getName, request.getName())
                .ne(ApiInfo::getId, request.getId());
        boolean exists = baseMapper.exists(queryWrapper);
        if (exists) {
            throw new BusinessException(ResponseCode.CONFLICT, "接口名称已存在");
        }
        ApiInfo entity = apiInfoConvert.toEntity(request);
        baseMapper.updateById(entity);
    }

    @Override
    public void enable(Long id) {
        LambdaUpdateWrapper<ApiInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(ApiInfo::getStatus, true)
                .set(ApiInfo::getUpdatedAt, new Date())
                .eq(ApiInfo::getId, id);
        baseMapper.update(updateWrapper);
    }

    @Override
    public void disable(Long id) {
        LambdaUpdateWrapper<ApiInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(ApiInfo::getStatus, false)
                .set(ApiInfo::getUpdatedAt, new Date())
                .eq(ApiInfo::getId, id);
        baseMapper.update(updateWrapper);
    }

    @Override
    public ApiInfoDebugResponse debug(ApiInfoDebugRequest request) {
        ApiInfo apiInfo = getById(request.getId());
        if (usersService.getCurrentUser() == null) {
            throw new BusinessException(ResponseCode.BAD_PERMIT, "请登陆后进行调试");
        }
        if (apiInfo == null || !apiInfo.getStatus()) {
            throw new BusinessException(ResponseCode.BAD_REQUEST, "接口已停用");
        }
        ApiClient apiClient = new ApiClient("http://localhost:8081");
        ApiRequest apiRequest = new ApiRequest(apiInfo.getEndpoint(), HttpMethod.valueOf(apiInfo.getMethod()), request.getParams());
        ApiResponse call = apiClient.call(apiRequest);
        ApiInfoDebugResponse apiInfoDebugResponse = new ApiInfoDebugResponse();
        apiInfoDebugResponse.setStatus(call.getStatus());
        apiInfoDebugResponse.setBody(call.getBody());
        return apiInfoDebugResponse;
    }
}




