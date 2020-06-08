package cn.kerninventory.tools.common;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Objects;

/**
 * @author Kern
 * @date 2019/12/16 16:39
 */
public class ReflectUtil {

    /**
     * 获取标准的get方法名
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
     * 获取标准的set方法名
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
     * 获取标准的get method
     * @param clazz
     * @param fieldName
     * @return
     * @throws NoSuchMethodException
     */
    public static Method specifiedGetMethod(Class clazz, String fieldName) throws NoSuchMethodException {
        Objects.requireNonNull(fieldName, "Field name can't be null!");
        Objects.requireNonNull(clazz, "clazz can't be null!");
        return clazz.getDeclaredMethod(specifiedGetMethodName(fieldName), null);
    }

    /**
     * 获取标准的set method
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
     * 获取字段值
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
     * 设置字段值
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
     * 指定类的class类型，并获取一个实例
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
     * 判断Class 所表示的数据类型是否为基础数据类型
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
     * 获取字段上指定的注解实例
     * @param field
     * @param marker
     * @param <T>
     * @return
     */
    public static <T extends Annotation> Annotation getFirstMarkedAnnotation(Field field, Class<T> marker) {
        Annotation[] annotations = field.getDeclaredAnnotations();
        for (Annotation annotation : annotations){
            if (annotation.annotationType().isAnnotationPresent(marker)){
                return annotation;
            }
        }
        return null;
    }

    /**
     * 获取类的泛型类型数组
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
