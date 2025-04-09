package com.qfleaf.yunapi.convert;

import com.qfleaf.yunapi.model.entity.AppInfo;
import com.qfleaf.yunapi.model.dto.app.AppInfoCreateRequest;
import com.qfleaf.yunapi.model.dto.app.AppInfoEditRequest;
import org.springframework.stereotype.Component;

@Component
public class AppInfoConvert {
    public AppInfo toEntity(AppInfoCreateRequest request) {
        AppInfo appInfo = new AppInfo();
        appInfo.setUserId(request.getUserId());
        appInfo.setName(request.getName());
        appInfo.setDescription(request.getDescription());
        return appInfo;
    }

    public AppInfo toEntity(AppInfoEditRequest request) {
        AppInfo appInfo = new AppInfo();
        appInfo.setId(request.getId());
        appInfo.setName(request.getName());
        appInfo.setDescription(request.getDescription());
        return appInfo;
    }
}
