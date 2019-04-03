package com.wanghuan.utils;

import java.util.UUID;

public class IdGeneratorUtil {
    public static String generatId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
