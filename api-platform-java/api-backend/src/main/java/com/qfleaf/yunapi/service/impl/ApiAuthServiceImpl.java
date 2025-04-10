package com.qfleaf.yunapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfleaf.yunapi.convert.ApiAuthConvert;
import com.qfleaf.yunapi.mapper.ApiAuthMapper;
import com.qfleaf.yunapi.model.dto.auth.ApiAuthCreateRequest;
import com.qfleaf.yunapi.model.dto.auth.ApiAuthQueryRequest;
import com.qfleaf.yunapi.model.entity.ApiAuth;
import com.qfleaf.yunapi.model.vo.ApiAuthPageVO;
import com.qfleaf.yunapi.model.vo.ApiAuthVO;
import com.qfleaf.yunapi.service.ApiAuthService;
import com.qfleaf.yunapi.service.UsersService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author qianfang
 * @description 针对表【api_auth(API 授权表，管理用户对 API 的访问权限)】的数据库操作Service实现
 * @createDate 2025-03-29 12:33:27
 */
@DubboService
@Service
public class ApiAuthServiceImpl extends ServiceImpl<ApiAuthMapper, ApiAuth>
        implements ApiAuthService {

    private final ApiAuthConvert apiAuthConvert;
    private final UsersService usersService;

    public ApiAuthServiceImpl(ApiAuthConvert apiAuthConvert, UsersService usersService) {
        this.apiAuthConvert = apiAuthConvert;
        this.usersService = usersService;
    }

    @Override
    public IPage<ApiAuthPageVO> findPageVo(ApiAuthQueryRequest request) {
        IPage<ApiAuthPageVO> page = new Page<>(request.getCurrent(), request.getSize());
        LambdaQueryWrapper<ApiAuth> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(ApiAuth::getAppId, request.getAppId())
                .isNull(ApiAuth::getDeletedAt);
        return baseMapper.selectPageVo(page, queryWrapper);
    }

    @Override
    public void generateApiAuth(ApiAuthCreateRequest request) {
        ApiAuth entity = apiAuthConvert.toEntity(request);
        baseMapper.insert(entity);
    }

    @Override
    public ApiAuthVO findById(Long id) {
        Long currentUserId = usersService.getCurrentUser().getId();
        return baseMapper.selectVoById(id, currentUserId);
    }

    @Override
    public boolean existsByAuthKey(String authKey) {
        LambdaQueryWrapper<ApiAuth> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(ApiAuth::getAuthKey, authKey)
                .isNull(ApiAuth::getDeletedAt);
        ApiAuth auth = baseMapper.selectOne(queryWrapper);
        return auth != null && (auth.getExpireAt() == null || auth.getExpireAt().getTime() > new Date().getTime());
    }
}




