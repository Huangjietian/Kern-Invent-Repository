package cn.kerninventor.tools.poibox.data.datatable.datavalidation.textLength;

import cn.kerninventor.tools.poibox.data.datatable.datavalidation.CompareType;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.ExcelValid;

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
@ExcelValid
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelValidTextlength {

    /**
     * Depending on the qualified unique value or minimum value of the comparison type
     * @return
     */
    int value();

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
     * compare type , default less than or equal to.
     */
    CompareType compareType() default CompareType.LTE;
}
