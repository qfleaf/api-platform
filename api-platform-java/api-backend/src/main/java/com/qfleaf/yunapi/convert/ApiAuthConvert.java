package com.qfleaf.yunapi.convert;

import com.qfleaf.web.random.RandomAuthKeyGenerator;
import com.qfleaf.yunapi.entity.ApiAuth;
import com.qfleaf.yunapi.open.model.dto.auth.ApiAuthCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class ApiAuthConvert {
    private final RandomAuthKeyGenerator randomAuthKeyGenerator;

    public ApiAuthConvert(RandomAuthKeyGenerator randomAuthKeyGenerator) {
        this.randomAuthKeyGenerator = randomAuthKeyGenerator;
    }

    public ApiAuth toEntity(ApiAuthCreateRequest request) {
        ApiAuth apiAuth = new ApiAuth();
        apiAuth.setUserId(request.getUserId());
        apiAuth.setAppId(request.getAppId());
        apiAuth.setAuthKey(randomAuthKeyGenerator.generateAuthKey());
        return apiAuth;
    }
}
