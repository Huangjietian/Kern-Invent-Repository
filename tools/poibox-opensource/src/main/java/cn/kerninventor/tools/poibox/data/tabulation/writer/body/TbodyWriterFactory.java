package cn.kerninventor.tools.poibox.data.tabulation.writer.body;

import cn.kerninventor.tools.poibox.data.tabulation.translator.AbstractColumnDataTranslator;
import cn.kerninventor.tools.poibox.data.tabulation.writer.TbodyWriter;

import java.util.List;

/**
 * @author Kern
 * @date 2020/6/5 11:54
 * @description
 */
public class TbodyWriterFactory {

    public static <T> TbodyWriter<T> getTbodyWriter(AbstractColumnDataTranslator abstractColumnDataTranslator, List<T> tList) {
        if (tList != null) {
            return new TableBodyDataWriter<T>(tList, abstractColumnDataTranslator);
        } else {
            return new TableBodyTemplateWriter<T>();
        }
    }
}
