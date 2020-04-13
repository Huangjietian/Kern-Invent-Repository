package cn.kerninventor.tools.poibox.data.templatedtable.initializer.configuration;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @author Kern
 * @date 2020/4/13 11:59
 * @description
 */
public interface ExcelBannerDefinition {

    ExcelBannerDefinition newInstance(String value, CellStyle cellStyle, CellRangeAddress rangeAddress);

}
