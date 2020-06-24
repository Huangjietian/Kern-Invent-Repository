package cn.kerninventor.tools.poibox.data.tabulation.writer.basic;

import cn.kerninventor.tools.poibox.data.tabulation.definition.ColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.definition.TabulationDefinition;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Kern
 * @date 2020/4/10 14:10
 * @description
 */
public interface BodyWriter<T> {

    void doWirte(TabulationDefinition<T> tabulationDefinition, ColumnDefinition columnDefinition, Sheet sheet);

}
