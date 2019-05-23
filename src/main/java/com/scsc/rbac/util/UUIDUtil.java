package com.scsc.rbac.util;

import java.util.UUID;

/**
 * @author qing
 * @date 2019/4/11 13:58
 */
public class UUIDUtil {
    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
