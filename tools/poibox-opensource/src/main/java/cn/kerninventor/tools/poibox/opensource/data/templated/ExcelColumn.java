package cn.kerninventor.tools.poibox.opensource.data.templated;

import cn.kerninventor.tools.poibox.opensource.data.templated.writer.col.ColWriter;
import cn.kerninventor.tools.poibox.opensource.data.templated.writer.col.GeneralColWriter;
import cn.kerninventor.tools.poibox.opensource.developer.ReadyToDevelop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kern
 * @date 2019/12/6 10:41
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {

    int DefaultColumnWidth = -1;

    String value();

    int columnSort() default -1;

    int columnWidth() default DefaultColumnWidth;

    String dataFormatEx() default "";

    String formula() default "";

    int theadStyleIndex() default 0;

    int tbodyStyleIndex() default 0;

    @ReadyToDevelop("合并列和普通列的数据写入器配置，支持自定义的写入器，用于写入例如数组或者其他本工具暂不支持的数据类型")
    Class<? extends ColWriter> colWriter() default GeneralColWriter.class;
}
