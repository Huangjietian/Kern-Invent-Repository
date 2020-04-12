package cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.decimal;

import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.CompareType;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.DataValid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title: ExcelValid_DECIMAL
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.validation.decimal
 * @Author Kern
 * @Date 2019/12/13 15:18
 * @Description: TODO
 */
@DataValid
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DecimalDataValid {

    /**
     * Depending on the qualified unique value or minimum value of the comparison type
     * @return
     */
    double value() default 0.00;

    /**
     * The maximum value, whether or not it makes sense depends on the comparison type
     * @return
     */
    double optionalVal() default -1.00;

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