package cn.kerninventor.tools.poibox.data.tabulation.writer;

import cn.kerninventor.tools.poibox.data.tabulation.context.ColumnDefinition;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Kern
 * @date 2020/4/10 14:10
 * @description
 */
public interface TbodyWriter<T> {

    void templateTbody(ColumnDefinition<T> columnDefinition, Sheet sheet);

}
