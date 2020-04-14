package cn.kerninventor.tools.poibox.data.templated.initializer.configuration;

import org.apache.poi.ss.usermodel.CellStyle;

/**
 * @author Kern
 * @date 2020/4/13 12:02
 * @description
 */
public interface ExcelColumnDefinition {

    ExcelColumnDefinition setTitleName(String titleName);

    ExcelColumnDefinition setColumnStyle(CellStyle columnStyle);

    ExcelColumnDefinition setColumnWidth(int columnWidth);

    ExcelColumnDefinition setDataFormatExpreesion(String dataFormatExpreesion);

    ExcelColumnDefinition setMergeByContent(boolean isMergeByContent);
}
