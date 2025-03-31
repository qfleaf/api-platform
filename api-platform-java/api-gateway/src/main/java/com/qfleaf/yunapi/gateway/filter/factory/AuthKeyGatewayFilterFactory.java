package com.qfleaf.yunapi.gateway.filter.factory;

import com.qfleaf.yunapi.service.ApiAuthService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.nio.charset.StandardCharsets;

@Component
public class AuthKeyGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthKeyGatewayFilterFactory.Config> {
    private static final String AUTH_KEY_HEADER = "Auth-Key";
    private static final Logger log = LoggerFactory.getLogger(AuthKeyGatewayFilterFactory.class);

    @DubboReference
    private ApiAuthService apiAuthService;

    public AuthKeyGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return config.isEnable() ?
                new OrderedGatewayFilter((exchange, chain) -> {
                    HttpHeaders headers = exchange.getRequest().getHeaders();
                    String authKey = headers.getFirst(AUTH_KEY_HEADER);

                    if (!isValidAuthKey(authKey)) {
                        log.warn("请求拦截: Auth-Key 认证失败");
                        // 创建一个失败的响应对象
                        String errorMessage = "{\"message\": \"Auth-Key 认证失败\"}";
                        // 将错误信息写入响应体
                        DataBuffer dataBuffer = exchange
                                .getResponse()
                                .bufferFactory()
                                .wrap(errorMessage.getBytes(StandardCharsets.UTF_8));
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().writeWith(Flux.just(dataBuffer)).then();
                    }

                    return chain.filter(exchange);
                }, Ordered.HIGHEST_PRECEDENCE)
                :
                (exchange, chain) -> chain.filter(exchange);
    }

    private boolean isValidAuthKey(String authKey) {
        // 调用远程服务或查询 Redis 验证 authKey
//        return "valid-auth-key".equals(authKey);
        return apiAuthService.existsByAuthKey(authKey);
    }

    public static class Config {
        private boolean enable;

        public Config() {
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }
    }
}
