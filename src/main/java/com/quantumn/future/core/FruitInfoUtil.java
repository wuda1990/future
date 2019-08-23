package com.quantumn.future.core;

import java.lang.reflect.Field;

public class FruitInfoUtil {
    public static void getFruitInfo(Class clazz) {
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(FruitProvider.class)) {
                FruitProvider provider = field.getAnnotation(FruitProvider.class);
                System.out.println(provider.id()+":"+provider.name()+":"+provider.address());
            }
        }
    }
}
