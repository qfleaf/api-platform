package com.qfleaf.yunapi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qfleaf.yunapi.model.entity.ApiAuth;
import com.qfleaf.yunapi.model.dto.auth.ApiAuthCreateRequest;
import com.qfleaf.yunapi.model.dto.auth.ApiAuthQueryRequest;
import com.qfleaf.yunapi.model.vo.ApiAuthPageVO;
import com.qfleaf.yunapi.model.vo.ApiAuthVO;

/**
* @author qianfang
* @description 针对表【api_auth(API 授权表，管理用户对 API 的访问权限)】的数据库操作Service
* @createDate 2025-03-29 12:33:27
*/
public interface ApiAuthService extends IService<ApiAuth> {
    IPage<ApiAuthPageVO> findPageVo(ApiAuthQueryRequest request);
    void generateApiAuth(ApiAuthCreateRequest request);
    ApiAuthVO findById(Long id);
    boolean existsByAuthKey(String authKey);
}
