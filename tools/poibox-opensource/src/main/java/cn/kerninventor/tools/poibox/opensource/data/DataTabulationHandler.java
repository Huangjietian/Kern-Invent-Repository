package cn.kerninventor.tools.poibox.opensource.data;

import cn.kerninventor.tools.poibox.opensource.BoxBracket;
import cn.kerninventor.tools.poibox.opensource.Poibox;
import cn.kerninventor.tools.poibox.opensource.data.templated.initializer.ETabulationInitiator;
import cn.kerninventor.tools.poibox.opensource.data.templated.reader.ETabulationReader;
import cn.kerninventor.tools.poibox.opensource.data.templated.reader.Reader;
import cn.kerninventor.tools.poibox.opensource.data.templated.writer.ETabulationWriter;
import cn.kerninventor.tools.poibox.opensource.data.templated.writer.Writer;

/**
 * @author Kern
 * @date 2019/12/11 17:05
 */
public final class DataTabulationHandler extends BoxBracket implements DataTabulator {

    public DataTabulationHandler(Poibox poiBox) {
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
