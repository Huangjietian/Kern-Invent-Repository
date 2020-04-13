package cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array;

import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.DataValid;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary.api.Dictionary;
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

    /**
     * The field value can be translated based on metadata and viewdata by configuring the property
     * @return
     */
    Class<? extends Dictionary> dictionary();


    /**
     * Prompt message when a cell is selected
     * @return
     */
    String prompMessage() default "";

    /**
     * Prompt message when a constraint is violated
     * @return
     */
    String errorMessage() default "";

    /**
     * Determines which data this data is associated with.
     * @return
     */
    @ReadyToDevelop
    String cascadeFlow() default "";

    /**
     * Default data when no dictionary data was provided!
     * @return
     */
    String[] defValuesWhenEmpty() default "NO DATA!";
}
