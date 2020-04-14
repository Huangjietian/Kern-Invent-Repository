package cn.kerninventor.tools.poibox.data.templated.validation.array;

import cn.kerninventor.tools.poibox.data.templated.validation.DataValid;
import cn.kerninventor.tools.poibox.data.templated.validation.array.dictionary.Dictionary;
import cn.kerninventor.tools.poibox.developer.ReadyToDevelop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kern
 * @date 2019/12/13 15:28
 */
@DataValid
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ArrayDataValid {

    Class<? extends Dictionary> dictionary();

    @ReadyToDevelop
    DictionaryConf conf() default @DictionaryConf(dictionary = Dictionary.class, flags = "");

    String prompMessage() default "";

    String errorMessage() default "";

    @ReadyToDevelop
    String cascadeFlow() default "";

    String[] defValuesWhenEmpty() default "NO DATA!";
}
