package cn.kerninventor.tools.poibox.data;

import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.data.datatable.reader.Reader;
import cn.kerninventor.tools.poibox.data.datatable.writer.Writer;
import cn.kerninventor.tools.poibox.data.datatable.initializer.ExcelTabulationInitializer;
import cn.kerninventor.tools.poibox.data.datatable.reader.ExcelTabulationReader;
import cn.kerninventor.tools.poibox.data.datatable.templator.ExcelTabulationTemplator;
import cn.kerninventor.tools.poibox.data.datatable.templator.Templator;
import cn.kerninventor.tools.poibox.data.datatable.writer.ExcelTabulationWriter;

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
        return new ExcelTabulationTemplator(new ExcelTabulationInitializer<>(sourceClass, getParent()));
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
