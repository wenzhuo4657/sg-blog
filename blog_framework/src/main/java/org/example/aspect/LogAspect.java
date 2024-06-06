package org.example.aspect;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.annotation.SystemLog;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Slf4j
public class LogAspect {
    @Pointcut("@annotation(org.example.annotation.SystemLog)")
    public  void pc(){
    }

    @Around("pc()")
    public  Object pt(ProceedingJoinPoint point){
        Object ret;


        try {
            handlerBefore(point);
            ret=point.proceed();
            handlerAfter(ret);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }finally {
            log.info("==========end");

        }
        return  ret;
    }

    private void handlerAfter(Object ret) {
        log.info("方法结果："+JSON.toJSONString(ret));
    }

    private void handlerBefore(ProceedingJoinPoint point) {
//        获取request，其中有url等打印信息
        ServletRequestAttributes requestAttributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request= requestAttributes.getRequest();
//        获取注解对象，其中包含一些属性值也需要打印
        SystemLog systemLog=getSystemLog(point);


//        打印信息
        log.info("======start");

        log.info("请求url: "+request.getRequestURI());
        log.info("请求方式： "+request.getMethod());
        // 打印调用 controller 的全路径(全类名)、方法名
        log.info("请求类名   : {}.{}",point.getSignature().getDeclaringTypeName(),((MethodSignature) point.getSignature()).getName());
        // 打印请求的 IP
        log.info("访问IP    : {}",request.getRemoteHost());
        // 打印请求入参。JSON.toJSONString十FastJson提供的工具方法，能把数组转成JSON
        log.info("传入参数   : {}", JSON.toJSONString(point.getArgs()));
    }

    private SystemLog getSystemLog(ProceedingJoinPoint point) {
        MethodSignature methodSignature= (MethodSignature) point.getSignature();
        SystemLog systemLog=methodSignature.getMethod().getAnnotation(SystemLog.class);
        return systemLog;
    }

}
