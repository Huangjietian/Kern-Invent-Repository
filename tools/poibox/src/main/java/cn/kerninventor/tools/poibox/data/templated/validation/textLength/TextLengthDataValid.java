package cn.kerninventor.tools.poibox.data.templated.validation.textLength;

import cn.kerninventor.tools.poibox.data.templated.validation.CompareType;
import cn.kerninventor.tools.poibox.data.templated.validation.DataValid;

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
public @interface TextLengthDataValid {

    int value();

    int optionalVal() default -1;

    String prompMessage() default "";

    String errorMessage() default "";

    CompareType compareType() default CompareType.LTE;
}
