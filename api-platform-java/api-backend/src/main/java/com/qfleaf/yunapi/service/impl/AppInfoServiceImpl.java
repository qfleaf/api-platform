package com.qfleaf.yunapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfleaf.yunapi.entity.AppInfo;
import com.qfleaf.yunapi.service.AppInfoService;
import com.qfleaf.yunapi.mapper.AppInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author qianfang
* @description 针对表【app_info(应用信息表，记录开发者的应用信息)】的数据库操作Service实现
* @createDate 2025-03-29 12:33:27
*/
@Service
public class AppInfoServiceImpl extends ServiceImpl<AppInfoMapper, AppInfo>
    implements AppInfoService{

}




