package com.bobtime.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityUtils {

    private static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        Class<?> currentClass = clazz;
        while (currentClass != null) {
            fields.addAll(Arrays.asList(currentClass.getDeclaredFields()));
            currentClass = currentClass.getSuperclass();
        }
        return fields;
    }


    private static Map<Field, Object> getFieldMap(Object instance) {
        Map<Field, Object> fieldMap = new HashMap<>();
        if (instance != null) {
            List<Field> fields = getAllFields(instance.getClass());
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    fieldMap.put(field, field.get(instance));
                } catch (IllegalAccessException e) {
                    // 예외 처리 로직 추가 필요함
                }
            }
        }
        return fieldMap;
    }


    @SneakyThrows
    public static <T> T copyObject(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }

        T target = createInstance(targetClass);
        Map<Field, Object> sourceFieldMap = getFieldMap(source);

        for (Map.Entry<Field, Object> entry : sourceFieldMap.entrySet()) {
            Field sourceField = entry.getKey();
            Object sourceFieldValue = entry.getValue();

            try {
                Field targetField = targetClass.getDeclaredField(sourceField.getName());
                targetField.setAccessible(true);
                if (targetField.getType().isAssignableFrom(sourceField.getType())) {
                    targetField.set(target, sourceFieldValue);
                }
            } catch (NoSuchFieldException ignored) {
                // 타겟 클래스에 해당 필드가 없는 경우 무시
            }
        }

        return target;
    }


    private static <T> T createInstance(Class<T> targetClass) throws Exception {
        return targetClass.getDeclaredConstructor().newInstance();
    }
}
