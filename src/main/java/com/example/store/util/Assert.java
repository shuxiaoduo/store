package com.example.store.util;


import com.example.store.exception.BusinessExecutionException;
import com.example.store.exception.NotFoundException;

import java.util.Collection;
import java.util.Objects;

@SuppressWarnings("unused")
public class Assert {

    private static final String DEFAULT_MESSAGE = "业务执行出现异常";

    public static void isTrue(boolean expression) {
        isTrue(expression, DEFAULT_MESSAGE);
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new BusinessExecutionException(message);
        }
    }

    public static void isFalse(boolean expression) {
        isFalse(expression, DEFAULT_MESSAGE);
    }

    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new BusinessExecutionException(message);
        }
    }

    public static void isNull(Object object) {
        isNull(object, DEFAULT_MESSAGE);
    }

    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new BusinessExecutionException(message);
        }
    }

    public static void notNull(Object object) {
        notNull(object, DEFAULT_MESSAGE);
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new NotFoundException(message);
        }
    }

    public static void notEqual(Object o1, Object o2) {
        notEqual(o1, o2, DEFAULT_MESSAGE);
    }

    public static void notEqual(Object o1, Object o2, String message) {
        if (Objects.equals(o1, o2)) {
            throw new BusinessExecutionException(message);
        }
    }

    public static void isEqual(Object o1, Object o2, String message) {
        if (!Objects.equals(o1, o2)) {
            throw new BusinessExecutionException(message);
        }
    }

    public static void isValueIn(Object value, Object[] alternative, String s) {
        for (Object o : alternative) {
            if (Objects.equals(o, value)) {
                return;
            }
        }

        throw new BusinessExecutionException(s);
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if (collection.size() == 0) {
            throw new BusinessExecutionException(message);
        }
    }

    public static void isEmpty(Collection<?> collection, String message) {
        if (collection.size() != 0) {
            throw new BusinessExecutionException(message);
        }
    }
}