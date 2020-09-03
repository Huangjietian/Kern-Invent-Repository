package cn.kerninventory.tools.excel.fluexcel.elements;

import java.lang.annotation.*;

/**
 * <p>
 *     Column definition.
 * </p>
 *
 * @author Kern
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {

    /**
     * 定义列表头内容
     * @return
     */
    String[] value();

    /**
     * 定义列宽
     * @return
     */
    int width() default -1;

    /**
     * 定义列的单元格格式，详情参考Excel的单元格格式自定义部分
     * @return
     */
    String dataFormat() default "";

    /**
     * 定义列的单元格函数
     * @return
     */
    String formula() default "";

    /**
     * 风格订阅
     * @return
     */
    int styleSubs() default 0;


//    /**
//     * 定义列的单元格写入器 默认提供的几种单元格写入器请参考 {@link CellsWriter}
//     * @return
//     */
//    Class<? extends CellsWriter> cellsWriter() default CellsGeneralWriter.class;

//    /**
//     * 定义列的字段值翻译器。 如果你指定了某个 {@link ColumnDataTranslate#translator()}, 并置 {@link ColumnDataTranslate#open()} = true <br/>
//     * 那么你需要在对应的读写器中添加对应的 {@link ColumnDataTranslator},<br/>
//     * 例如
//     * {@link TabulationWriter#withColumnDataTranslator(String, ColumnDataTranslator)} 或者
//     * {@link TabulationReader#withColumnDataTranslator(String, ColumnDataTranslator)}
//     * @return
//     */
//    ColumnDataTranslate dataTranslate() default @ColumnDataTranslate(translator = "", open = false);
}
