package cn.kerninventor.tools.poibox.data;

import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.Poibox;
import cn.kerninventor.tools.poibox.data.tabulation.definition.TabulationClassParser;
import cn.kerninventor.tools.poibox.data.tabulation.definition.TabulationDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.reader.ExcelTabulationReader;
import cn.kerninventor.tools.poibox.data.tabulation.reader.TabulationReader;
import cn.kerninventor.tools.poibox.data.tabulation.writer.ExcelTabulationWriter;
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
        TabulationDefinition<T> tabulationDefinition = new TabulationClassParser().parse(sourceClass, getParent());
        return new ExcelTabulationWriter<T>(tabulationDefinition);
    }

    @Override
    public <T> TabulationReader<T> reader(Class<T> sourceClass) {
        TabulationDefinition<T> tabulationDefinition = new TabulationClassParser().parse(sourceClass, getParent());
        return new ExcelTabulationReader<T>(tabulationDefinition);
    }

}
