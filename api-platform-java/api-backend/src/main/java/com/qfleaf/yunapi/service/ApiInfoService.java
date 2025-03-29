package com.qfleaf.yunapi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qfleaf.yunapi.entity.ApiInfo;
import com.qfleaf.yunapi.open.model.dto.api.ApiInfoCreateRequest;
import com.qfleaf.yunapi.open.model.dto.api.ApiInfoDebugRequest;
import com.qfleaf.yunapi.open.model.dto.api.ApiInfoQueryRequest;
import com.qfleaf.yunapi.open.model.dto.api.ApiInfoUpdateRequest;
import com.qfleaf.yunapi.open.model.vo.ApiInfoDebugResponse;
import com.qfleaf.yunapi.open.model.vo.ApiInfoPageVO;
import com.qfleaf.yunapi.open.model.vo.ApiInfoVO;

/**
* @author qianfang
* @description 针对表【api_info(API 信息表，存储平台所有对外提供的接口信息)】的数据库操作Service
* @createDate 2025-03-29 12:33:27
*/
public interface ApiInfoService extends IService<ApiInfo> {
    IPage<ApiInfoPageVO> findPageVo(ApiInfoQueryRequest request);
    ApiInfoVO findById(Long id);
    void createApi(ApiInfoCreateRequest request);
    void updateById(ApiInfoUpdateRequest request);
    void enable(Long id);
    void disable(Long id);
    ApiInfoDebugResponse debug(ApiInfoDebugRequest request);
}
