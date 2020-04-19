package cn.kerninventor.tools.poibox.data;

import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.data.templated.initializer.ETabulationInitiator;
import cn.kerninventor.tools.poibox.data.templated.reader.ETabulationReader;
import cn.kerninventor.tools.poibox.data.templated.reader.Reader;
import cn.kerninventor.tools.poibox.data.templated.writer.ETabulationWriter;
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
        ETabulationInitiator tabulationInitializer = new ETabulationInitiator(sourceClass, getParent());
        return new ETabulationWriter(tabulationInitializer);
    }

    @Override
    public <T> Reader<T> reader(Class<T> sourceClass) {
        ETabulationInitiator tabulationInitializer = new ETabulationInitiator(sourceClass, getParent());
        return new ETabulationReader(tabulationInitializer);
    }

}
