package com.hundsun.licensesdk.common.util;

import com.hundsun.licensesdk.lang.annotation.LicenseApi;
import com.hundsun.licensesdk.validation.LicenseResult;

/**
 * Created by jiayq24996 on 2019-06-14
 */
public class ValidateUtil {

    private static String PRODUCT_LICENSE_NO_IS_NULL_ERROR = "许可证编号不存在";

    private static String API_FUNCTION_ID_IS_NULL_ERROR = "接口功能号不存在";

    public static LicenseResult apiCheck(LicenseApi apiInfo) {
        LicenseResult result = new LicenseResult();
        boolean flag = CacheUtil.getInstance().isExist(CacheUtil.API_CACHE_NAME, apiInfo.functionId());
        if (!flag) {
            result.add(apiInfo.functionId() + API_FUNCTION_ID_IS_NULL_ERROR);
        }
        return result;
    }
}
