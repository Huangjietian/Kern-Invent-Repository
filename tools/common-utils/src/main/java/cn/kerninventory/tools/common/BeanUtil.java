package cn.kerninventory.tools.common;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;

import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * <h1>中文注释</h1>
 * <p>
 *     Java Bean 工具类， 封装了针对java对象的判空，克隆等公共方法。
 * </p>
 * @author Kern
 * @version 1.0
 */
public class BeanUtil {

    /**
     * <p> 条件函数接口</p>
     *
     * @author Kern
     * @return {@link Boolean}
    */
    @FunctionalInterface
    public interface Condition {
        boolean judge();
    }

    private static void judgment(Condition condittion, String manifesto) {
        if (condittion.judge()) {
            throw new RuntimeException(manifesto);
        }
    }

    private static void judgment(Condition condittion, String manifesto, Throwable throwable) {
        if (condittion.judge()) {
            throw new RuntimeException(manifesto, throwable);
        }
    }

    private static void judgment(Condition condittion, RuntimeException e) {
        if (condittion.judge()) {
            throw e;
        }
    }

    /**
     * <p>
     *     对 {@link Object} 进行判空<br/>
     *     Object obj == null  {@retrun true}<br/>
     *     Object obj != null  {@retrun false}
     * </p>
     * @param obj
     * @return boolean
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * <p>
     *     对 {@link Object} 进行判空<br/>
     *     Object obj == null  {@retrun false}<br/>
     *     Object obj != null  {@retrun true}
     * </p>
     * @param obj
     * @return boolean
     */
    public static boolean nonNull(Object obj) {
        return obj != null;
    }

    /**
     * <p>
     *     对 {@link Object} 进行判空, 如果 T t == null 将抛出空指针异常
     * </p>
     * @param t
     * @return Object
     * @throws NullPointerException
     */
    public static <T> T notNull(T t) {
        return notNull(t, t.getClass() + " cannot be null!");
    }

    /**
     * <p>
     *     对 {@link Object} 进行判空, 如果 T t == null 将抛出空指针异常
     * </p>
     * @param t
     * @return Object
     * @throws NullPointerException
     */
    public static <T> T notNull(T t, String errorMsg) {
        judgment(() -> t == null , new NullPointerException(errorMsg));
        return t;
    }

    /**
     * <p>
     *     对 {@link Object} 进行判空<br/>
     *     数组集合空集，字符串空串，Map空键等情况也符合空判断
     * </p>
     * @param t
     * @return boolean
     */
    public static <T> boolean isEmpty(T t) {
        if (t instanceof String) {
            return isEmpty((String)t);
        } else if (t instanceof Collection) {
            return isEmpty((Collection)t);
        } else if (t.getClass().isArray()) {
            return isEmpty((Object[]) t);
        } else if (t instanceof Map) {
            return isEmpty((Map) t);
        } else {
            return isNull(t);
        }
    }

    /**
     * <p>
     *       {@link BeanUtil#isEmpty(Object)} 的反判断
     * </p>
     * @param t
     * @return boolean
     */
    public static <T> boolean nonEmpty(T t) {
        return !isEmpty(t);
    }

    /**
     * <p>
     *     对 {@link Object} 进行判空, 如果符合{@link BeanUtil#isEmpty(Object)}将抛出{@link IllegalArgumentException}异常
     * </p>
     * @param t
     * @return Object
     * @throws IllegalArgumentException
     */
    public static <T extends Object> T notEmpty(T t) {
        return notEmpty(t, t.getClass() + " cannot be empty!");
    }

    /**
     * <p>
     *     对 {@link Object} 进行判空, 如果符合{@link BeanUtil#isEmpty(Object)}将抛出{@link IllegalArgumentException}异常
     * </p>
     * @param t
     * @return Object
     * @throws IllegalArgumentException
     */
    public static <T extends Object> T notEmpty(T t, String errorMsg) {
        judgment(() -> isEmpty(t), new IllegalArgumentException(errorMsg));
        return t;
    }

    /**
     * <p>
     *     匹配对象t是否符合{@link Predicate}函数的描述
     * </p>
     * @param t
     * @param predicate
     * @param <T>
     * @return boolean
     */
    public static <T> boolean match(T t, Predicate<T> predicate) {
        return predicate.test(t);
    }

    /**
     * <p>
     *     字符串判空或空串
     * </p>
     * @param str
     * @return boolean
     */
    public static boolean nonEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * <p>
     *     字符串判空或空串
     * </p>
     * @param str
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    /**
     * <p>
     *     字符串判空或空串或者空格
     * </p>
     * @param str
     * @return boolean
     */
    public static boolean nonBlank(String str) {
        return !isBlank(str);
    }

    /**
     * <p>
     *     字符串判空或空串或者空格
     * </p>
     * @param str
     * @return boolean
     */
    public static boolean isBlank(String str) {
        String temporary = new String(str);
        return temporary == null || "".equals(temporary.trim());
    }

    /**
     * <p>
     *     字符串判空或空串， 抛出异常{@link IllegalArgumentException}
     * </p>
     * @param str
     * @return
     * @throws IllegalArgumentException
     */
    public static String notEmpty(String str) {
        return notEmpty(str, "The string cannot be empty!");
    }

    /**
     * <p>
     *     字符串判空或空串， 抛出异常{@link IllegalArgumentException}
     * </p>
     * @param str
     * @return
     * @throws IllegalArgumentException
     */
    public static String notEmpty(String str, String errorMsg) {
        judgment(() -> isEmpty(str), new IllegalArgumentException(errorMsg));
        return str;
    }

    /**
     * <p>
     *     字符串判空或空串或者空格， 抛出异常{@link IllegalArgumentException}
     * </p>
     * @param str
     * @return
     * @throws IllegalArgumentException
     */
    public static String notBlank(String str) {
        return notBlank(str, "The string cannot be blank!");
    }

    /**
     * <p>
     *     字符串判空或空串或者空格， 抛出异常{@link IllegalArgumentException}
     * </p>
     * @param str
     * @return
     * @throws IllegalArgumentException
     */
    public static String notBlank(String str, String errorMsg) {
        judgment(() -> isBlank(str), new IllegalArgumentException(errorMsg));
        return str;
    }

    /**
     * <p>
     *     集合判空 null || isEmpty()
     * </p>
     * @param collection
     * @return boolean
     */
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * <p>
     *     集合判空 null || isEmpty()
     * </p>
     * @param collection
     * @return boolean
     */
    public static boolean nonEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    /**
     * <p>
     *     集合判空 null || isEmpty()，抛出异常{@link IllegalArgumentException}
     * </p>
     * @param t
     * @return
     * @throws IllegalArgumentException
     */
    public static <T extends Collection> T notEmpty(T t) {
        return notEmpty(t, t.getClass() + " Cannot be empty!");
    }

    /**
     * <p>
     *     集合判空 null || isEmpty()，抛出异常{@link IllegalArgumentException}
     * </p>
     * @param t
     * @return
     * @throws IllegalArgumentException
     */
    public static <T extends Collection> T notEmpty(T t, String errorMsg) {
        judgment(() -> isEmpty(t), new IllegalArgumentException(errorMsg));
        return t;
    }

    /**
     * <p>
     *     数组判空 null || length == 0
     * </p>
     * @param arrays
     * @return boolean
     */
    public static boolean isEmpty(Object[] arrays) {
        return arrays == null || arrays.length == 0;
    }

    /**
     * <p>
     *     数组判空 null || length == 0
     * </p>
     * @param arrays
     * @return boolean
     */
    public static boolean nonEmpty(Object[] arrays) {
        return !isEmpty(arrays);
    }

    /**
     * <p>
     *     数组判空 null || length == 0, 抛出异常{@link IllegalArgumentException}
     * </p>
     * @param arrays
     * @return
     * @throws IllegalArgumentException
     */
    public static <T> T[] notEmpty(T[] arrays) {
        return notEmpty(arrays, "The arrays cannot be empty!");
    }

    /**
     * <p>
     *     数组判空 null || length == 0, 抛出异常{@link IllegalArgumentException}
     * </p>
     * @param arrays
     * @return
     * @throws IllegalArgumentException
     */
    public static <T> T[] notEmpty(T[] arrays, String errorMsg) {
        judgment(() -> isEmpty(arrays), new IllegalArgumentException(errorMsg));
        return arrays;
    }

    /**
     * <p>
     *     Map判空 null || isEmpty()
     * </p>
     * @param map
     * @return boolean
     */
    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    /**
     * <p>
     *     Map判空 null || isEmpty()
     * </p>
     * @param map
     * @return boolean
     */
    public static boolean nonEmpty(Map map) {
        return !isEmpty(map);
    }

    /**
     * <p>
     *     Map判空 null || isEmpty()，抛出异常{@link IllegalArgumentException}
     * </p>
     * @param map
     * @return
     * @throws IllegalArgumentException
     */
    public static <K, V> Map<K, V> notEmpty(Map<K, V> map) {
        return notEmpty(map, map.getClass() + " cannot be empty!");
    }

    /**
     * <p>
     *     Map判空 null || isEmpty()，抛出异常{@link IllegalArgumentException}
     * </p>
     * @param map
     * @return
     * @throws IllegalArgumentException
     */
    public static <K, V> Map<K, V> notEmpty(Map<K, V> map, String errorMsg) {
        judgment(() -> isEmpty(map), new IllegalArgumentException(errorMsg));
        return map;
    }

    /**
     * <p>
     *     后续动作函数接口，配合接口<br/>
     *     {@link Condition}条件函数接口以及<br/>
     *     {@link BeanUtil#nonNull2Do(Object, AfterAction, AfterAction)}方法，<br/>
     *     {@link BeanUtil#branchAction(Condition, AfterAction, AfterAction)}方法<br/>
     *     提供一组三元判断的执行接口
     * </p>
     *
     * @author Kern
    */
    @FunctionalInterface
    public interface AfterAction<T> {
        T action();
    }

    /**
     * <P>
     *     业务中常见的非空判断，当空判断成立时，执行successdAction, 反之执行failureAction
     * </P>
     * @param arg
     * @param successdAction
     * @param failureAction
     * @param <T>
     * @return
     */
    public static <T> T nonNull2Do(Object arg, AfterAction<T> successdAction, AfterAction<T> failureAction) {
        if (nonNull(arg)) {
            return successdAction.action();
        }
        return failureAction.action();
    }

    /**
     * <P>
     *     通过condition提供一个是否判断的结果，当判断成立时，执行successdAction, 反之执行failureAction
     * </P>
     * @param condition
     * @param succeedAction
     * @param failureAction
     * @param <T>
     * @return
     */
    public static <T> T branchAction(Condition condition, AfterAction<T> succeedAction, AfterAction<T> failureAction) {
        if (condition.judge()) {
            return succeedAction.action();
        }
        return failureAction.action();
    }

    /**
     * <p>
     *     使用阿里巴巴的json包克隆集合对象：<br/>
     *     {@link JSON#toJSONString(Object)}<br/>
     *     {@link JSON#parseArray(String,Class)}<br/>
     *     <br/>
     *     注：<br/>
     *     集合的泛型类可以通过{@link com.alibaba.fastjson.annotation}注解指定name()属性来配置字段名，<br/>
     *     以实现不同类型的集合之间传递对象的值，但必须保证字段之间的数据类型是一致的
     * </p>
     * @param source
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> copyProperties(Collection source, Class<T> tClass) {
        return JSON.parseArray(JSON.toJSONString(source), tClass);
    }

    /**
     * <p>
     *     使用阿里巴巴的json包克隆对象：<br/>
     *     {@link JSON#toJSONString(Object)}<br/>
     *     {@link JSON#parseObject(String,Class)}<br/>
     *     <br/>
     *     注：<br/>
     *     类可以通过{@link com.alibaba.fastjson.annotation}注解指定name()属性来配置字段名，<br/>
     *     以实现不同类型的对象之间传递字段的值，但必须保证字段之间的数据类型是一致的
     * </p>
     * @param source
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T copyProperties(Object source, Class<T> tClass) {
        return JSON.parseObject(JSON.toJSONString(source), tClass);
    }

    /**
     * <p>
     *      Spring提供的BeanUtils的对象克隆，仅整理到工具包中
     *      {@link BeanUtils#copyProperties(Object, Object, String...)}
     * </p>
     * @param source
     * @param target
     * @param ignoreProperties
     */
    public static void copyProperties(Object source, Object target, String... ignoreProperties) {
        BeanUtils.copyProperties(source, target, ignoreProperties);
    }

    /**
     * <p>
     *     使用读写流对实现了序列化接口的对象进行深克隆。
     * </p>
     * @param t
     * @return T
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T extends Serializable> T copy(T t) throws IOException, ClassNotFoundException {
        notNull(t, "The clone object cannot be null");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(t);
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (T) ois.readObject();
    }
}
