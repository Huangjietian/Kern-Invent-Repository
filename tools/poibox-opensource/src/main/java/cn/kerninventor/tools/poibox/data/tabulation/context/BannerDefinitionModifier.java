package cn.kerninventor.tools.poibox.data.tabulation.context;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @author Kern
 * @date 2020/4/13 11:59
 * @description
 */
public interface BannerDefinitionModifier {

    String  getContent();

    BannerDefinitionModifier setContent(String value);

    BannerDefinitionModifier setCellStyle(CellStyle style);

    BannerDefinitionModifier setRange(CellRangeAddress cellRangeAddress);

    BannerDefinitionModifier setRange(int row1, int row2, int col1, int col2);
}