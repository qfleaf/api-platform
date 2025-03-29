package com.qfleaf.yunapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfleaf.yunapi.entity.ApiAuth;
import com.qfleaf.yunapi.service.ApiAuthService;
import com.qfleaf.yunapi.mapper.ApiAuthMapper;
import org.springframework.stereotype.Service;

/**
* @author qianfang
* @description 针对表【api_auth(API 授权表，管理用户对 API 的访问权限)】的数据库操作Service实现
* @createDate 2025-03-29 12:33:27
*/
@Service
public class ApiAuthServiceImpl extends ServiceImpl<ApiAuthMapper, ApiAuth>
    implements ApiAuthService{

}




