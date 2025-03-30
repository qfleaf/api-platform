package com.qfleaf.yunapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfleaf.yunapi.model.entity.ApiRequestLog;
import com.qfleaf.yunapi.mapper.ApiRequestLogMapper;
import com.qfleaf.yunapi.model.Pageable;
import com.qfleaf.yunapi.model.vo.ApiRequestLogPageVO;
import com.qfleaf.yunapi.model.vo.ApiRequestLogVO;
import com.qfleaf.yunapi.service.ApiRequestLogService;
import org.springframework.stereotype.Service;

/**
 * @author qianfang
 * @description 针对表【api_request_log(API 请求日志表，记录每次 API 调用的信息)】的数据库操作Service实现
 * @createDate 2025-03-29 12:33:27
 */
@Service
public class ApiRequestLogServiceImpl extends ServiceImpl<ApiRequestLogMapper, ApiRequestLog>
        implements ApiRequestLogService {

    @Override
    public IPage<ApiRequestLogPageVO> findPageVo(Pageable pageable) {
        IPage<ApiRequestLogPageVO> page = new Page<>(pageable.getCurrent(), pageable.getSize());
        LambdaQueryWrapper<ApiRequestLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .orderByDesc(ApiRequestLog::getRequestTime)
                .isNull(ApiRequestLog::getDeletedAt);
        return baseMapper.selectPageVo(page, queryWrapper);
    }

    @Override
    public ApiRequestLogVO findVoById(Long id) {
        return baseMapper.selectVoById(id);
    }
}




