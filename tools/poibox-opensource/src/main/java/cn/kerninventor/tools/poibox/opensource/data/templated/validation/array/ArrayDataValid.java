package cn.kerninventor.tools.poibox.opensource.data.templated.validation.array;

import cn.kerninventor.tools.poibox.opensource.data.templated.validation.DataValid;
import cn.kerninventor.tools.poibox.opensource.data.templated.validation.array.dictionary.Dictionary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kern
 * @date 2019/12/13 15:28
 */
@Deprecated
@DataValid
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ArrayDataValid {

    Class<? extends Dictionary> dictionary();

    String prompMessage() default "";

    String errorMessage() default "";

    String[] defValuesWhenEmpty() default "NO DATA!";

}
