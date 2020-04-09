package cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.integer;

import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.CompareType;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.DataValid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title: ExcelValid_INTEGER
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.validation.integer
 * @Author Kern
 * @Date 2019/12/13 14:25
 * @Description: TODO
 */
@DataValid
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IntDataValid {

    /**
     * Depending on the qualified unique value or minimum value of the comparison type
     * @return
     */
    int value() default 0;

    /**
     * The maximum value, whether or not it makes sense depends on the comparison type
     * @return
     */
    int optionalVal() default -1;

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
     * compare type , default greater than.
     */
    CompareType compareType() default CompareType.GT;
}
