package com.example.test.common;

import com.example.test.common.exception.ServiceException;

public class Conditions {

    public static void isTrueOrElseTrow(Boolean condition, String message) {
        if (!condition) {
            throw new ServiceException(message);
        }
    }

    public static void isTrueOrElseThrow(Boolean condition, String message, Object... args) {
        if (!condition) {
            throw new ServiceException(message, args);
        }
    }
}
