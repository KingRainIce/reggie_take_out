package com.ice.utils;

/**
 * @Title: BaseContext
 * @Auth: Ice
 * @Date: 2023/3/26 10:03
 * @Version: 1.0
 * @Desc:
 */

public class BaseContext {

    private static final ThreadLocal<Long> THREAD_LOCAL = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        THREAD_LOCAL.set(id);
    }

    public static Long getCurrentId() {
        return THREAD_LOCAL.get();
    }

}
