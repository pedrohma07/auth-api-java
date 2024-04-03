package com.example.authapijava.utils;

import java.lang.reflect.Field;

public abstract class ValidRequestDTO {
    public static void isValid(Object obj) {
        Class<?> theClass = obj.getClass();
        Field fields[] = theClass.getDeclaredFields();
        int totalFields = fields.length;
        int nullFieldsCount = 0;

        try {
            for (Field field : fields) {
                field.setAccessible(true); // Permitir acesso a campos privados
                Object value = field.get(obj);
                if (value == null) {
                    nullFieldsCount++;
                }
            }

            if (nullFieldsCount == totalFields) {
                throw new IllegalArgumentException("All fields are null or you are sending non-existent fields.");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
