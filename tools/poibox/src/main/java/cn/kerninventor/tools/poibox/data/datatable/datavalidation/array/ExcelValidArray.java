package cn.kerninventor.tools.poibox.data.datatable.datavalidation.array;

import cn.kerninventor.tools.poibox.data.datatable.datavalidation.ExcelValid;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.dictionary.view.ViewBody;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.dictionary.view.ViewDictionary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title: ExcelValid_ARRAY
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.validation.array
 * @Author Kern
 * @Date 2019/12/13 15:28
 * @Description: TODO
 */
@ExcelValid
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelValidArray {

    boolean coverness = true;

    /**
     * The field value can be translated based on metadata and viewdata by configuring the property
     * @return
     */
    Class<? extends ViewDictionary> dictionary();

    /**
     *
     * @return
     */
    Class<? extends ViewBody> body();

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
    String casecadeFrom() default "";



}
