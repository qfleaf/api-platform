package com.qfleaf.yunapi.gateway.filter;

import com.qfleaf.yunapi.service.ApiAuthService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthKeyFilter implements GlobalFilter, Ordered {

    private static final String AUTH_KEY_HEADER = "Auth-Key";
    private static final Logger log = LoggerFactory.getLogger(AuthKeyFilter.class);

    @DubboReference
    private ApiAuthService apiAuthService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String authKey = headers.getFirst(AUTH_KEY_HEADER);

        if (!isValidAuthKey(authKey)) {
            log.warn("请求拦截: Auth-Key 认证失败");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    private boolean isValidAuthKey(String authKey) {
        // 调用远程服务或查询 Redis 验证 authKey
//        return "valid-auth-key".equals(authKey);
        return apiAuthService.existsByAuthKey(authKey);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE; // 优先级高于日志过滤器，先执行
    }
}