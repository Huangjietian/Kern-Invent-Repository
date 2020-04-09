package cn.kerninventor.tools.poibox.data;

import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.data.templatedtable.reader.Reader;
import cn.kerninventor.tools.poibox.data.templatedtable.writer.Writer;
import cn.kerninventor.tools.poibox.data.templatedtable.initializer.ExcelTabulationInitializer;
import cn.kerninventor.tools.poibox.data.templatedtable.reader.ExcelTabulationReader;
import cn.kerninventor.tools.poibox.data.templatedtable.templator.ExcelTabulationTemplator;
import cn.kerninventor.tools.poibox.data.templatedtable.templator.Templator;
import cn.kerninventor.tools.poibox.data.templatedtable.writer.ExcelTabulationWriter;

/**
 * @Title: POIDataBoxOpened
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data
 * @Author Kern
 * @Date 2019/12/11 17:05
 * @Description: TODO
 */
public final class DataTabulationHandler extends BoxBracket implements DataTabulator {

    public DataTabulationHandler(POIBox poiBox) {
        super(poiBox);
    }

    @Override
    public <T> Templator<T> templator(Class<T> sourceClass) {

        return new ExcelTabulationTemplator(new ExcelTabulationInitializer<>(sourceClass, poiBox));
    }

    @Override
    public Writer writer() {
        return new ExcelTabulationWriter();
    }

    @Override
    public Reader reader() {
        return new ExcelTabulationReader();
    }


}
