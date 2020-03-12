package cn.kerninventor.tools.poibox.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @Title: ReflectUtil
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.utils
 * @Author Kern
 * @Date 2019/12/16 16:39
 * @Description: TODO
 */
public class ReflectUtil {

    public static String specifiedGetMethodName(String fieldName) {
        if (fieldName == null || "".equals(fieldName.trim())){
            throw new NullPointerException("Field name can't be empty!");
        }
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    public static String specifiedSetMethodName(String fieldName) {
        if (fieldName == null || "".equals(fieldName.trim())){
            throw new NullPointerException("Field name can't be empty!");
        }
        return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    public static Method specifiedGetMethod(Class clazz, String fieldName) throws NoSuchMethodException {
        Objects.requireNonNull(fieldName, "Field name can't be null!");
        Objects.requireNonNull(clazz, "clazz can't be null!");
        return clazz.getDeclaredMethod(specifiedGetMethodName(fieldName), null);
    }

    public static Method specifiedSetMethod(Class clazz, String fieldName, Class argClass) throws NoSuchMethodException {
        Objects.requireNonNull(fieldName, "Field name can't be null!");
        Objects.requireNonNull(clazz, "clazz can't be null!");
        return clazz.getDeclaredMethod(specifiedSetMethodName(fieldName), argClass);
    }

    public static Object getFieldValue(Field field, Object target) throws IllegalAccessException {
        field.setAccessible(true);
        return field.get(target);
    }

    public static void setFieldValue(Field field, Object target, Object value) throws IllegalAccessException {
        field.setAccessible(true);
        field.set(target, value);
    }

    public static Object newInstance(Class clazz, Object... paramters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class[] paramTypes = new Class[paramters.length];
        for (int i = 0 ; i < paramters.length ; i ++) {
            paramTypes[i] = paramters[i].getClass();
        }
        return clazz.getDeclaredConstructor(paramTypes).newInstance(paramters);
    }

    public static boolean isBasicWrapperType(Class clazz) {
        try {
            return ((Class) clazz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

}
