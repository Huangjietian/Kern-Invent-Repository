package cn.kerninventor.tools.poibox.data;

import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.Poibox;
import cn.kerninventor.tools.poibox.data.tabulation.context.TableContext;
import cn.kerninventor.tools.poibox.data.tabulation.reader.ETabulationReader;
import cn.kerninventor.tools.poibox.data.tabulation.reader.TabulationReader;
import cn.kerninventor.tools.poibox.data.tabulation.writer.ETabulationWriter;
import cn.kerninventor.tools.poibox.data.tabulation.writer.TabulationWriter;

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
        TableContext<T> context = new TableContext<T>(sourceClass, getParent());
        return new ETabulationWriter<T>(context);
    }

    @Override
    public <T> TabulationReader<T> reader(Class<T> sourceClass) {
        TableContext<T> context = new TableContext<T>(sourceClass, getParent());
        return new ETabulationReader<T>(context);
    }

}
