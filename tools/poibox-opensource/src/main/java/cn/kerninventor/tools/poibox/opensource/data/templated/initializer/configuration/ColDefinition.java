package cn.kerninventor.tools.poibox.opensource.data.templated.initializer.configuration;

import org.apache.poi.ss.usermodel.CellStyle;

/**
 * @author Kern
 * @date 2020/4/13 12:02
 * @description
 */
public interface ColDefinition {

    ColDefinition setTitleName(String titleName);

    ColDefinition setTheadStyle(CellStyle cellStyle);

    ColDefinition setTbodyStyle(CellStyle cellStyle);

    ColDefinition setColumnWidth(int columnWidth);

    ColDefinition setDataFormatExpreesion(String dataFormatExpreesion);

    ColDefinition setFormula(String formula);
}
