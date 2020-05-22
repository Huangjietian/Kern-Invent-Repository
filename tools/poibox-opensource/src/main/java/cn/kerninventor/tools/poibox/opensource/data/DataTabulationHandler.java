package cn.kerninventor.tools.poibox.opensource.data;

import cn.kerninventor.tools.poibox.opensource.BoxBracket;
import cn.kerninventor.tools.poibox.opensource.Poibox;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.TableContext;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.reader.ETabulationReader;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.reader.TabulationReader;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.writer.ETabulationWriter;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.writer.TabulationWriter;

/**
 * @author Kern
 * @date 2019/12/11 17:05
 */
public final class DataTabulationHandler extends BoxBracket implements DataTabulator {

    public DataTabulationHandler(Poibox poiBox) {
        super(poiBox);
    }

    @Override
    public <T> TabulationWriter<T> writer(Class<T> sourceClass) {
        TableContext context = new TableContext(sourceClass, getParent());
        return new ETabulationWriter(context);
    }

    @Override
    public <T> TabulationReader<T> reader(Class<T> sourceClass) {
        TableContext context = new TableContext(sourceClass, getParent());
        return new ETabulationReader(context);
    }

}
