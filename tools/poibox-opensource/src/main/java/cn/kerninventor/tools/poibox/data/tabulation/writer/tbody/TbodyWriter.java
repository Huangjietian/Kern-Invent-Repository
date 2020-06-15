package cn.kerninventor.tools.poibox.data.tabulation.writer.tbody;

import cn.kerninventor.tools.poibox.data.tabulation.context.ClassFileColumnDefinition;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Kern
 * @date 2020/4/10 14:10
 * @description
 */
public interface TbodyWriter<T> {

    void templateTbody(ClassFileColumnDefinition<T> classFileColumnDefinition, Sheet sheet);

}
