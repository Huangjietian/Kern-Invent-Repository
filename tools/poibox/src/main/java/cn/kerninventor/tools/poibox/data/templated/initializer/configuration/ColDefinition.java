package cn.kerninventor.tools.poibox.data.templated.initializer.configuration;

import org.apache.poi.ss.usermodel.CellStyle;

/**
 * @author Kern
 * @date 2020/4/13 12:02
 * @description
 */
public interface ColDefinition {

    ColDefinition setTitleName(String titleName);

    ColDefinition setColumnStyle(CellStyle columnStyle);

    ColDefinition setColumnWidth(int columnWidth);

    ColDefinition setDataFormatExpreesion(String dataFormatExpreesion);

    ColDefinition setMergeByContent(boolean isMergeByContent);
}
