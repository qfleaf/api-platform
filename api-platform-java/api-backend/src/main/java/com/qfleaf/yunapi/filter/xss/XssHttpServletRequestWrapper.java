package com.qfleaf.yunapi.filter.xss;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        return cleanXSS(super.getParameter(name));
    }

    private String cleanXSS(String value) {
        if (value == null) return null;
        return value.replaceAll("<", "&lt;").replaceAll(">", "&gt;")
                    .replaceAll("'", "&#39;").replaceAll("\"", "&quot;");
    }
}