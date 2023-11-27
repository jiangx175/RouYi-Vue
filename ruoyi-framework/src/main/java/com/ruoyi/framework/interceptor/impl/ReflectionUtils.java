package com.ruoyi.framework.interceptor.impl;

import java.lang.reflect.Field;

public class ReflectionUtils {
    public static Field findField(Class<?> clazz, String fieldName) {
        Class<?> currentClass = clazz;
        while (currentClass != null) {
            try {
                Field field = currentClass.getDeclaredField(fieldName);
                field.setAccessible(true); // 设置为可访问
                return field;
            } catch (NoSuchFieldException e) {
                // 字段不存在于当前类，尝试查找父类
                currentClass = currentClass.getSuperclass();
            }
        }
        return null; // 如果未找到字段，返回 null
    }
}

