package cn.kerninventor.tools.web.gadget;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Kern
 * @Title: BaseDTO
 * @ProjectName swms
 * @Description: 查询方法的参数传递对象超类，继承于此类的所有的子类的String类型的字段将在初始化时被StringUtils.tirmToNull()处理。
 * @date 2019/8/2815:50
 */
public class QueryDTO {

    //后续可以考虑使用注解的形式
    private List<String> emptyAbleFileds = Lists.newArrayList();
    public void addEmptyAbleFiled(String... filedName){
        emptyAbleFileds.addAll(Lists.newArrayList(filedName));
    }

    public QueryDTO() {
        //QueryDTO 还可以提供以注解的形式进行LIKESQL拼接
    }

    public void trimToNull() throws IllegalArgumentException {
        Class tClass =  this.getClass();
        Field [] fields = tClass.getDeclaredFields();
        for (Field f : fields){
            String fieldName = f.getName();
            if (f.getType() == String.class && !emptyAbleFileds.contains(fieldName)){
                new StringFieldInstance(f, this,tClass).invokeTrimToNull();
            }
        }
    }


    /**
     * 代表字符字段对象，提供了trimToNull方法
     * @param <DTO>
     */
    private class StringFieldInstance <DTO extends QueryDTO>{
        private Class sourceClass;
        private DTO sourceObject;
        private Field sourceField;
        private Method getMethod;
        private Method setMethod;
        public StringFieldInstance(Field sourceField, DTO sourceObject, Class sourceClass) {
            this.sourceClass = sourceClass;
            this.sourceObject = sourceObject;
            this.sourceField = sourceField;
            String sourceName = sourceField.getName();
            String namePart = sourceName.substring(0, 1).toUpperCase() + sourceName.substring(1);
            try {
                getMethod = sourceClass.getMethod("get" + namePart);
                setMethod = sourceClass.getMethod("set" + namePart);
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException("DTO中的字段："+ sourceName + "缺少get/set方法或get/set方法不符合规范");
            }

        }

        public void invokeTrimToNull() {
            try {
                setMethod.invoke(sourceObject, StringUtils.trimToNull((String) getMethod.invoke(sourceObject)));
            } catch (Exception e) {
                throw new IllegalArgumentException("调用get/set方法异常");
            }
        }

    }
}
