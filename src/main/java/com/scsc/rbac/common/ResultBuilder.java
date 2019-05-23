package com.scsc.rbac.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ResultBuilder {

    private static final Map<String, Object> successCodeBean;

    static {
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("success", true);
        successCodeBean = Collections.unmodifiableMap(map);
    }

    /**
     * 构建成功标识
     *
     * @return 成功标识
     */
    public static Map buildSuccessCode() {
        return successCodeBean;
    }

    /**
     * 构建成功Map
     *
     * @return 成功Map
     * @see "The Java Programming Language"
     */
    public static Map buildSuccess(Object data) {
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("success", true);
        map.put("data", data);
        return map;
    }

    public static Map buildSuccess(Object data, String version) {
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("success", true);
        map.put("data", data);
        map.put("version", version);
        return map;
    }

    /**
     * 构建失败Map
     *
     * @return 失败Map
     */
    public static Map buildError(String errMessage) {
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("success", false);
        map.put("errorMessage", errMessage);
        return map;
    }

}
