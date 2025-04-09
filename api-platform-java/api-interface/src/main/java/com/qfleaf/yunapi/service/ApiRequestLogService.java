package com.qfleaf.yunapi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qfleaf.yunapi.model.entity.ApiRequestLog;
import com.qfleaf.yunapi.model.Pageable;
import com.qfleaf.yunapi.model.vo.ApiRequestLogPageVO;
import com.qfleaf.yunapi.model.vo.ApiRequestLogVO;

/**
* @author qianfang
* @description 针对表【api_request_log(API 请求日志表，记录每次 API 调用的信息)】的数据库操作Service
* @createDate 2025-03-29 12:33:27
*/
public interface ApiRequestLogService extends IService<ApiRequestLog> {
    IPage<ApiRequestLogPageVO> findPageVo(Pageable pageable);
    ApiRequestLogVO findVoById(Long id);
    void saveApiRequestLog(ApiRequestLog apiRequestLog);
}
