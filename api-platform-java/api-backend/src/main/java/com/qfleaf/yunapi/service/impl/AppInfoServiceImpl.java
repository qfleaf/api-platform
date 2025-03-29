package com.qfleaf.yunapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfleaf.web.common.ResponseCode;
import com.qfleaf.web.common.exception.BusinessException;
import com.qfleaf.yunapi.convert.AppInfoConvert;
import com.qfleaf.yunapi.entity.AppInfo;
import com.qfleaf.yunapi.open.model.dto.app.AppInfoCreateRequest;
import com.qfleaf.yunapi.open.model.dto.app.AppInfoEditRequest;
import com.qfleaf.yunapi.open.model.dto.app.AppInfoQueryRequest;
import com.qfleaf.yunapi.open.model.vo.AppInfoPageVO;
import com.qfleaf.yunapi.open.model.vo.AppInfoVO;
import com.qfleaf.yunapi.open.model.vo.LoginUserVO;
import com.qfleaf.yunapi.service.AppInfoService;
import com.qfleaf.yunapi.mapper.AppInfoMapper;
import com.qfleaf.yunapi.service.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author qianfang
 * @description 针对表【app_info(应用信息表，记录开发者的应用信息)】的数据库操作Service实现
 * @createDate 2025-03-29 12:33:27
 */
@Service
public class AppInfoServiceImpl extends ServiceImpl<AppInfoMapper, AppInfo>
        implements AppInfoService {
    private final AppInfoConvert appInfoConvert;
    private final UsersService usersService;

    public AppInfoServiceImpl(AppInfoConvert appInfoConvert, UsersService usersService) {
        this.appInfoConvert = appInfoConvert;
        this.usersService = usersService;
    }

    @Override
    public IPage<AppInfoPageVO> findPageVo(AppInfoQueryRequest request) {
        IPage<AppInfoPageVO> page = new Page<>(request.getCurrent(), request.getSize());
        LambdaQueryWrapper<AppInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(request.getUserId() != null, AppInfo::getUserId, request.getUserId())
                .like(StringUtils.hasLength(request.getName()), AppInfo::getName, request.getName())
                .like(StringUtils.hasLength(request.getDescription()), AppInfo::getDescription, request.getDescription())
                .ge(request.getCreatedAt() != null, AppInfo::getCreatedAt, request.getCreatedAt())
                .isNull(AppInfo::getDeletedAt);
        return baseMapper.selectPageVo(page, queryWrapper);
    }

    @Override
    public AppInfoVO findById(long id) {
        return baseMapper.selectVoById(id);
    }

    @Override
    public void saveApp(AppInfoCreateRequest request) {
        LambdaQueryWrapper<AppInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AppInfo::getName, request.getName());
        boolean exists = baseMapper.exists(queryWrapper);
        if (exists) {
            throw new BusinessException(ResponseCode.CONFLICT, "应用名称已存在");
        }
        LoginUserVO currentUser = usersService.getCurrentUser();
        request.setUserId(currentUser.getId());
        AppInfo entity = appInfoConvert.toEntity(request);
        baseMapper.insert(entity);
    }

    @Override
    public void updateById(AppInfoEditRequest request) {
        LoginUserVO currentUser = usersService.getCurrentUser();
        LambdaQueryWrapper<AppInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(AppInfo::getUserId, currentUser.getId())
                .eq(AppInfo::getName, request.getName())
                .ne(AppInfo::getId, request.getId());
        boolean exists = baseMapper.exists(queryWrapper);
        if (exists) {
            throw new BusinessException(ResponseCode.CONFLICT, "应用名称已存在");
        }
        AppInfo entity = appInfoConvert.toEntity(request);
        baseMapper.updateById(entity);
    }
}




