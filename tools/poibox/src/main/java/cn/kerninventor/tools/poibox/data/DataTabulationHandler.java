package cn.kerninventor.tools.poibox.data;

import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.data.templated.initializer.ExcelTabulationInitializer;
import cn.kerninventor.tools.poibox.data.templated.reader.ExcelTabulationReader;
import cn.kerninventor.tools.poibox.data.templated.reader.Reader;
import cn.kerninventor.tools.poibox.data.templated.writer.ExcelTabulationWriter;
import cn.kerninventor.tools.poibox.data.templated.writer.Writer;

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
    public <T> Writer<T> writer(Class<T> sourceClass) {
        ExcelTabulationInitializer tabulationInitializer = new ExcelTabulationInitializer(sourceClass, getParent());
        return new ExcelTabulationWriter(tabulationInitializer);
    }

    @Override
    public <T> Reader<T> reader(Class<T> sourceClass) {
        ExcelTabulationInitializer tabulationInitializer = new ExcelTabulationInitializer(sourceClass, getParent());
        return new ExcelTabulationReader(tabulationInitializer);
    }

}
