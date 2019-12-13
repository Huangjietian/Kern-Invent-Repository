package cn.kerninventor.tools.poibox.data.datatable;
import cn.kerninventor.tools.poibox.style.RootTabulationStyle;
import cn.kerninventor.tools.poibox.style.TabulationStyle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title: POIExcel
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.annotation
 * @Author Kern
 * @Date 2019/12/6 10:39
 * @Description: TODO
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelTabulation {

    /**
     * config file name your need by this attribution.
     * @return
     */
    String headline() default "";

    /**
     * config start row index.
     * @return
     */
    int startRowIndex() default 0;

    /**
     * The number of lines in the body
     * @return
     */
    int textRowNum() default 20;

    /**
     * if you need to config your table's column index, you can config this attribution to false,
     * else poibox will use default plan that the first column will be 0 index.
     * @return
     */
    boolean autoColumnIndex() default true;

    /**
     * Sets the style style of the data tabulation
     * @return
     */
    Class<? extends TabulationStyle> style() default RootTabulationStyle.class;


}
