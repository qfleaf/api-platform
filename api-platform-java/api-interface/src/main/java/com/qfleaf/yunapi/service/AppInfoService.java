package com.qfleaf.yunapi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qfleaf.yunapi.model.entity.AppInfo;
import com.qfleaf.yunapi.model.dto.app.AppInfoCreateRequest;
import com.qfleaf.yunapi.model.dto.app.AppInfoEditRequest;
import com.qfleaf.yunapi.model.dto.app.AppInfoQueryRequest;
import com.qfleaf.yunapi.model.vo.AppInfoPageVO;
import com.qfleaf.yunapi.model.vo.AppInfoVO;

/**
* @author qianfang
* @description 针对表【app_info(应用信息表，记录开发者的应用信息)】的数据库操作Service
* @createDate 2025-03-29 12:33:27
*/
public interface AppInfoService extends IService<AppInfo> {
    IPage<AppInfoPageVO> findPageVo(AppInfoQueryRequest request);
    AppInfoVO findById(long id);
    void saveApp(AppInfoCreateRequest request);
    void updateById(AppInfoEditRequest request);
}
