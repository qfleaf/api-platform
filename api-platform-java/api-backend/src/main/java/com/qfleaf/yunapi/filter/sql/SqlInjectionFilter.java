package com.qfleaf.yunapi.filter.sql;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SqlInjectionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String query = httpRequest.getQueryString();
        if (query != null && containsSqlInjection(query)) {
            throw new ServletException("SQL 注入攻击尝试被拦截！");
        }
        chain.doFilter(request, response);
    }

    private boolean containsSqlInjection(String value) {
        return value.matches("(?i).*(' OR |' AND |--|;|DROP |DELETE |INSERT |UPDATE ).*");
    }
}