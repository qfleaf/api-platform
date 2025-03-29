package com.qfleaf.yunapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfleaf.yunapi.entity.SdkVersion;
import com.qfleaf.yunapi.service.SdkVersionService;
import com.qfleaf.yunapi.mapper.SdkVersionMapper;
import org.springframework.stereotype.Service;

/**
* @author qianfang
* @description 针对表【sdk_version(SDK 版本管理表，记录 SDK 的发布信息)】的数据库操作Service实现
* @createDate 2025-03-29 12:33:27
*/
@Service
public class SdkVersionServiceImpl extends ServiceImpl<SdkVersionMapper, SdkVersion>
    implements SdkVersionService{

}




