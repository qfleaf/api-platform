package com.qfleaf.yunapi.annotation.support;

import com.qfleaf.web.common.ResponseCode;
import com.qfleaf.web.common.exception.BusinessException;
import com.qfleaf.yunapi.annotation.Authorized;
import com.qfleaf.yunapi.model.vo.LoginUserVO;
import com.qfleaf.yunapi.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

import static com.qfleaf.yunapi.annotation.support.AuthConst.ROLE_ANY;

@Slf4j
@Aspect
@Component
public class AuthorizedAnnotationSupport {

    private final UsersService usersService;

    @Autowired
    public AuthorizedAnnotationSupport(UsersService usersService) {
        this.usersService = usersService;
    }

    @Pointcut("@annotation(com.qfleaf.yunapi.annotation.Authorized)")
    void point() {
    }

    @Before("point()")
    void doBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Authorized annotation = method.getAnnotation(Authorized.class);
        if (annotation != null) {
            String[] authority = annotation.authority();
            LoginUserVO currentUser = usersService.getCurrentUser();
            String role = currentUser.getRole();
            boolean matches = Arrays.asList(authority).contains(role) || Arrays.asList(authority).contains(ROLE_ANY);
            if (!matches) {
                throw new BusinessException(ResponseCode.BAD_PERMIT, "权限不足");
            }
        }
    }
}
