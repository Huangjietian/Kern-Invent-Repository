package cn.kerninventor.tools.poibox.opensource.data.templated.writer.chain;

import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Kern
 * @date 2020/5/21 12:01
 * @description
 */
@FunctionalInterface
public interface WriteChain {

    void writeTo(Sheet sheet);
}
