package com.wanghuan.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InsuranceLog {

    public static void logError(String method, String errorMsg, String params, Exception e){
        log.error("[方法名" + method + "]",errorMsg,params,e);
    }
}
