//package com.qfleaf.yunapi.gateway.filter;
//
//import com.qfleaf.yunapi.model.entity.ApiRequestLog;
//import com.qfleaf.yunapi.service.ApiInfoService;
//import com.qfleaf.yunapi.service.ApiRequestLogService;
//import org.apache.dubbo.config.annotation.DubboReference;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.core.Ordered;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.time.Duration;
//import java.time.Instant;
//import java.util.Date;
//import java.util.Map;
//
//public class ApiRequestLogGatewayFilter implements GatewayFilter, Ordered {
//
//    private static final String AUTH_KEY_HEADER = "Auth-Key";
//    @DubboReference
//    private ApiRequestLogService apiRequestLogService; // 远程调用
//    @DubboReference
//    private ApiInfoService apiInfoService;
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        Instant startTime = Instant.now();
//
//        return chain.filter(exchange).doOnSuccess((done) -> {
//            Instant endTime = Instant.now();
//            Duration duration = Duration.between(startTime, endTime);
//
//            ApiRequestLog logData = new ApiRequestLog();
//            String path = request.getPath().toString();
//            Long apiId = apiInfoService.findIdByPath(path);
//            logData.setApiId(apiId);
//            logData.setAuthKey(request.getHeaders().getFirst(AUTH_KEY_HEADER));
//            logData.setIpAddress(request.getRemoteAddress() != null ? request.getRemoteAddress().getHostString() : "UNKNOWN");
//            Map<String, String> paramsMap = request.getQueryParams().toSingleValueMap();
//            logData.setRequestParams(paramsMap);
//            logData.setResponseStatus(exchange.getResponse().getStatusCode() != null ? exchange.getResponse().getStatusCode().value() : 500);
//            logData.setRequestTime(Date.from(startTime));
//            logData.setResponseTime(duration.toMillis());
//
//            // 远程调用日志存储服务
//            apiRequestLogService.saveApiRequestLog(logData);
//        });
//    }
//
//    @Override
//    public int getOrder() {
//        return Ordered.LOWEST_PRECEDENCE;
//    }
//}