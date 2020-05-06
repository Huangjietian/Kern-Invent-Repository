package cn.kerninventor.tools.poibox.opensource.data.templated.validation.date;

import cn.kerninventor.tools.poibox.opensource.data.templated.validation.CompareType;
import cn.kerninventor.tools.poibox.opensource.data.templated.validation.DataValid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title: ExcelValid_DATE
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.validation
 * @Author Kern
 * @Date 2019/12/13 11:10
 * @Description: TODO
 */
@DataValid
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateDataValid {

    String NOW = "now()";

    String parseFormat() default "yyyy-MM-dd";

    String date() default "1900-01-01";

    String optionalDate() default "";

    String prompMessage() default "";

    String errorMessage() default "";

    CompareType compareType() default CompareType.GT;

}
