package com.qfleaf.yunapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfleaf.yunapi.entity.ApiRequestLog;
import com.qfleaf.yunapi.service.ApiRequestLogService;
import com.qfleaf.yunapi.mapper.ApiRequestLogMapper;
import org.springframework.stereotype.Service;

/**
* @author qianfang
* @description 针对表【api_request_log(API 请求日志表，记录每次 API 调用的信息)】的数据库操作Service实现
* @createDate 2025-03-29 12:33:27
*/
@Service
public class ApiRequestLogServiceImpl extends ServiceImpl<ApiRequestLogMapper, ApiRequestLog>
    implements ApiRequestLogService{

}




