package cn.kerninventor.tools.poibox.data.tabulation.context;

import org.apache.poi.ss.usermodel.CellStyle;

/**
 * @author Kern
 * @date 2020/4/13 12:02
 * @description
 */
public interface ColumnDefinitionModifier {

    ColumnDefinitionModifier setTitleName(String titleName);

    ColumnDefinitionModifier setTheadStyle(CellStyle cellStyle);

    ColumnDefinitionModifier setTbodyStyle(CellStyle cellStyle);

    ColumnDefinitionModifier setColumnWidth(int columnWidth);

    ColumnDefinitionModifier setDataFormatExpreesion(String dataFormatExpreesion);

    ColumnDefinitionModifier setFormula(String formula);
}
