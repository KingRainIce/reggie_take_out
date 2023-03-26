package com.ice.common;

/**
 * @Title: CustomException
 * @Auth: Ice
 * @Date: 2023/3/26 11:37
 * @Version: 1.0
 * @Desc:
 */

public class CustomException extends RuntimeException{
    public CustomException(String message) {
        super(message);
    }
}
