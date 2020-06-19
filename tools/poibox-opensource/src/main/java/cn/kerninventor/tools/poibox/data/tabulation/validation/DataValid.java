package cn.kerninventor.tools.poibox.data.tabulation.validation;

import java.lang.annotation.*;

/**
 * @author Kern
 * @date 2019/12/13 11:32
 */
@Inherited
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataValid {

    Class<? extends AbstractDvBuilder> dvBuilder();

}
