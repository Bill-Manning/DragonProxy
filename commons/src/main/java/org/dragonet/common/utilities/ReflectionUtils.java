package org.dragonet.common.utilities;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionUtils {

    public static Object getFieldValue(Object target, String field) throws Exception {
        Field f = target.getClass().getDeclaredField(field);
        boolean savedAccessibleState = f.isAccessible();
        f.setAccessible(true);
        Object obj = f.get(target);
        f.setAccessible(savedAccessibleState);
        return obj;
    }

    public static void setFieldValue(Object target, String field, Object value) throws Exception {
        Field f = target.getClass().getDeclaredField(field);

        // change final values
        boolean modifierFinal = false;
        Field modifiersField = null;
        if((f.getModifiers() & Modifier.FINAL) > 0) {
            modifiersField = Field.class.getDeclaredField("modifiers");
            modifierFinal = true;
            modifiersField.setAccessible(true);
            modifiersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);
        }

        boolean savedAccessibleState = f.isAccessible();
        f.setAccessible(true);

        f.set(target, value);

        f.setAccessible(savedAccessibleState);

        if(modifierFinal) {
            modifiersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);
            modifiersField.setAccessible(false);
        }
    }

    public static Object invoke(Object target, String method, Class[] args, Object[] values) throws Exception {
        Method m = target.getClass().getDeclaredMethod(method, args);
        boolean savedAccessibleState = m.isAccessible();
        m.setAccessible(true);
        Object obj = m.invoke(target, values);
        m.setAccessible(savedAccessibleState);
        return obj;
    }

}
