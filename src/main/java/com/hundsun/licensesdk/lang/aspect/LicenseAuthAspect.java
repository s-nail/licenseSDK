package com.hundsun.licensesdk.lang.aspect;

import com.hundsun.licensesdk.common.util.CacheUtil;
import com.hundsun.licensesdk.common.dto.Api;
import com.hundsun.licensesdk.common.dto.Product;
import com.hundsun.licensesdk.common.util.ValidateUtil;
import com.hundsun.licensesdk.lang.annotation.LicenseApi;
import com.hundsun.licensesdk.validation.LicenseResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;


/**
 * Created by jiayq24996 on 2019-06-05
 */
@Aspect
public class LicenseAuthAspect {
    protected final Log logger = LogFactory.getLog(this.getClass());

    @PostConstruct
    private void init() {
        //1.HTTP请求许可中心获取对应系统的许可文件
        //2.解析许可文件，存放系统缓存中
        Product product = new Product();
        product.setLicenceNo("1111");
        product.setBeginDate("20190610");
        product.setExpireDate("20210610");
        product.setProductInfo("操作员中心");
        product.setUserInfo("admin from 操作员中心");
        CacheUtil.getInstance().addCache(CacheUtil.PRODUCT_CACHE_NAME, product.getLicenceNo(), product);

        Api api = new Api();
        api.setFunctionId("111500");
        api.setApiName("TEST");
        CacheUtil.getInstance().addCache(CacheUtil.API_CACHE_NAME, api.getFunctionId(), api);
    }

    @Pointcut(value = "@annotation(com.hundsun.licensesdk.lang.annotation.LicenseApi)")
    public void anyMethod() {
    }

    @Around("anyMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("================路过Aspect===================");
        long begin = System.currentTimeMillis();
        String name = joinPoint.getSignature().getName();
        //读取注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LicenseApi apiInfo = method.getAnnotation(LicenseApi.class);

        LicenseResult result = ValidateUtil.apiCheck(apiInfo);

        Object[] sourceArgs = joinPoint.getArgs();
        sourceArgs[sourceArgs.length - 1] = result;
        Object obj = joinPoint.proceed(sourceArgs);

        System.out.println("方法：" + name + "校验结果：" + result.getAllErrors().toString() + "；耗时：" + (System.currentTimeMillis() - begin));
        return obj;
    }

}
