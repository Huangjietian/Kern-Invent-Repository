package cn.kerninventory.tools.common;

import java.lang.reflect.*;
import java.util.Objects;

/**
 * <h1>中文</h1>
 * <p>
 *     反射工具类, 待完善
 * </p>
 * @author Kern
 * @version 1.0
 */
public class ReflectUtil {

    /**
     * <p>
     *     获取标准的get方法名
     * </p>
     * @param fieldName
     * @return
     */
    public static String specifiedGetMethodName(String fieldName) {
        if (fieldName == null || "".equals(fieldName.trim())){
            throw new NullPointerException("Field name can't be empty!");
        }
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    /**
     * <p>
     *     获取标准的set方法名
     * </p>
     * @param fieldName
     * @return
     */
    public static String specifiedSetMethodName(String fieldName) {
        if (fieldName == null || "".equals(fieldName.trim())){
            throw new NullPointerException("Field name can't be empty!");
        }
        return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    /**
     * <p>
     *     获取标准的get Method 实例
     * </p>
     * @param clazz
     * @param fieldName
     * @return
     * @throws NoSuchMethodException
     */
    public static Method specifiedGetMethod(Class clazz, String fieldName) throws NoSuchMethodException {
        Objects.requireNonNull(fieldName, "Field name can't be null!");
        Objects.requireNonNull(clazz, "clazz can't be null!");
        return clazz.getDeclaredMethod(specifiedGetMethodName(fieldName));
    }

    /**
     * <p>
     *     获取标准的set Method 实例
     * </p>
     * @param clazz
     * @param fieldName
     * @param argClass
     * @return
     * @throws NoSuchMethodException
     */
    public static Method specifiedSetMethod(Class clazz, String fieldName, Class argClass) throws NoSuchMethodException {
        Objects.requireNonNull(fieldName, "Field name can't be null!");
        Objects.requireNonNull(clazz, "clazz can't be null!");
        return clazz.getDeclaredMethod(specifiedSetMethodName(fieldName), argClass);
    }

    /**
     * <p>
     *     获取字段值
     * </p>
     * @param field
     * @param target
     * @return
     * @throws IllegalAccessException
     */
    public static Object getFieldValue(Field field, Object target) throws IllegalAccessException {
        field.setAccessible(true);
        return field.get(target);
    }

    /**
     * <p>
     *     设置字段值
     * </p>
     * @param field
     * @param target
     * @param value
     * @throws IllegalAccessException
     */
    public static void setFieldValue(Field field, Object target, Object value) throws IllegalAccessException {
        field.setAccessible(true);
        field.set(target, value);
    }

    /**
     * <p>
     *     指定类的class类型，并获取一个实例
     * </p>
     * @param clazz
     * @param paramters
     * @param <T>
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static <T extends Object> T newInstance(Class<T> clazz, Object... paramters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class[] paramTypes = new Class[paramters.length];
        for (int i = 0 ; i < paramters.length ; i ++) {
            paramTypes[i] = paramters[i].getClass();
        }
        return clazz.getDeclaredConstructor(paramTypes).newInstance(paramters);
    }

    /**
     * <p>
     *     判断Class 所表示的数据类型是否为基础数据类型
     * </p>
     * @param clazz
     * @return
     */
    public static boolean isBasicWrapperType(Class clazz) {
        try {
            return ((Class) clazz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * <p>
     *     获取类的泛型类型数组
     * </p>
     * @param clazz
     * @return
     */
    public static Type[] getGenericTypes(Class clazz) {
        Type[] types = clazz.getGenericInterfaces();
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                return ((ParameterizedType) type).getActualTypeArguments();
            }
        }
        return null;
    }
}
