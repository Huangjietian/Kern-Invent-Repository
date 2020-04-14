package cn.kerninventor.tools.poibox.data.templated.validation;

import java.lang.annotation.*;

/**
 * @Title: ExcelValid
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.validation
 * @Author Kern
 * @Date 2019/12/13 11:32
 * @Description: TODO
 */
@Inherited
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataValid {

    boolean coverness = false;


}
