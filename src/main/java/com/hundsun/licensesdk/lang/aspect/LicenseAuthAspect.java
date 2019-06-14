package com.hundsun.licensesdk.lang.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


/**
 * Created by jiayq24996 on 2019-06-05
 */
@Aspect
public class LicenseAuthAspect {
    protected final Log logger = LogFactory.getLog(this.getClass());

    @Pointcut(value = "@annotation(com.hundsun.licensesdk.lang.annotation.LicenseApi)")
    public void anyMethod() {
    }

    @Around("anyMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取请求报文头部元数据
        /*ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取请求对象
        HttpServletRequest request = requestAttributes.getRequest();
        //记录控制器执行前的时间毫秒数
        System.out.println("前置通知执行：");
        System.out.println("url:" + request.getRequestURL());
        System.out.println("method:" + request.getMethod());
        System.out.println("ip:" + request.getRemoteAddr());
        System.out.println("class_method:" + joinPoint.getSignature().getDeclaringTypeName() +
                "." + joinPoint.getSignature().getName());
        System.out.println("args:" + Arrays.toString(joinPoint.getArgs()));*/
        long begin = System.currentTimeMillis();
        String name = joinPoint.getSignature().getName();
        System.out.println("================路过Aspect===================");


        if (this.logger.isErrorEnabled()) {
            this.logger.error("Failed to set bean properties on servlet '" + name + "'", new Exception());
        }

        logger.info(joinPoint.getSignature().getDeclaringTypeName());
        logger.info(joinPoint.getSignature().getDeclaringType().getSimpleName());

        logger.error("License校验失败");
        Object[] sourceArgs = joinPoint.getArgs();
        sourceArgs[sourceArgs.length - 1] = new String("哈哈");
        Object obj = joinPoint.proceed(sourceArgs);

        System.out.println("方法：" + name + "执行结果：" + obj + "；耗时：" + (System.currentTimeMillis() - begin));

     /*   String name = joinPoint.getSignature().getName();
        System.out.println(name + "方法的前置通知");

        //和controller层返回值类型不同时，会报类型不匹配异常
        *//*if(true){
            List<Integer> list = new ArrayList<>();
            return list;
        }*//*
         *//*if(true){
            throw new AccessDeniedException("您无权操作！");
        }*//*


        //读取注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ParamsCheck paramsCheck = method.getAnnotation(ParamsCheck.class);
        if (paramsCheck != null && paramsCheck.ignore()) {
            joinPoint.proceed();
        }*/
        return obj;
    }

}
