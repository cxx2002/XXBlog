package com.cxx.aspect;

import com.alibaba.fastjson.JSON;
import com.cxx.annotation.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 陈喜喜
 * @date 2022-10-07 10:41
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    @Pointcut("@annotation(com.cxx.annotation.SystemLog)")
    public void pointCut() {

    }
    @Around("pointCut()")
    public Object printLog(ProceedingJoinPoint pjp) throws Throwable {
        Object result;
        try {
            this.handleBefore(pjp);
            result = pjp.proceed();
            this.handleAfter(result);
        } finally {
            //无论是否有异常结束后都会执行，换行
            log.info("=======End=======" + System.lineSeparator());
        }

        return null;
    }

    private void handleBefore(ProceedingJoinPoint pjp) {
        //获取到当前这个线程当中的这个请求对象  且具有线程隔离  在子类实现类有getRequest()方法获取到
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取被增强方法上的注解对象
        SystemLog systemLog = this.getSystemLog(pjp);
        log.info("=======Start=======");
        // 打印请求 URL
        log.info("URL            : {}",request.getRequestURL());
        // 打印描述信息
        log.info("BusinessName   : {}",systemLog.businessName());
        // 打印 Http method
        log.info("HTTP Method    : {}",request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}",pjp.getSignature().getDeclaringTypeName(),pjp.getSignature().getName());
        // 打印请求的 IP
        log.info("IP             : {}",request.getRemoteHost());
        // 打印请求入参
        log.info("Request Args   : {}", JSON.toJSONString(pjp.getArgs()) );
    }

    private void handleAfter(Object result) {
        // 打印出参
        log.info("Response       : {}", JSON.toJSONString(result));
    }

    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        SystemLog systemLog = methodSignature.getMethod().getAnnotation(SystemLog.class);
        return systemLog;

    }
}
