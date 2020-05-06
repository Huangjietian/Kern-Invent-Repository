package cn.kerninventor.tools.poibox.opensource.data.templated.validation.costom;

import cn.kerninventor.tools.poibox.opensource.data.templated.validation.DataValid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title ExcelValid_REGULAR
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.datavalidation.regular
 * @Author Kern
 * @Date 2020/3/11 11:07
 * @Description TODO
 */
@DataValid
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormulaCustomDataValid {

    String formula();

    String prompMessage() default "";

    String errorMessage() default "";

}