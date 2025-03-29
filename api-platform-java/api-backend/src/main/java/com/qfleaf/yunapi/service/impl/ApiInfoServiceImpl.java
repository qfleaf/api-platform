package com.qfleaf.yunapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfleaf.yunapi.entity.ApiInfo;
import com.qfleaf.yunapi.service.ApiInfoService;
import com.qfleaf.yunapi.mapper.ApiInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author qianfang
* @description 针对表【api_info(API 信息表，存储平台所有对外提供的接口信息)】的数据库操作Service实现
* @createDate 2025-03-29 12:33:27
*/
@Service
public class ApiInfoServiceImpl extends ServiceImpl<ApiInfoMapper, ApiInfo>
    implements ApiInfoService{

}




