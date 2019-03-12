package com.wanghuan.common;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.wanghuan.utils.MD5Util;

import java.security.NoSuchAlgorithmException;

public class pass {
    public static void  main(String []args) throws NoSuchAlgorithmException {
        MD5Util m = new MD5Util();
       System.out.println(MD5Util.encrypt("123"));

    }
}
