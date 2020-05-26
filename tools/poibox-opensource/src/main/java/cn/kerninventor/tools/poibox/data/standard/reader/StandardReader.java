package cn.kerninventor.tools.poibox.data.standard.reader;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @author Kern
 * @date 2020/5/22 17:06
 * @description
 */
public interface StandardReader<Result, Range> {

    Result readFrom(Sheet sheet, Range range);
}
