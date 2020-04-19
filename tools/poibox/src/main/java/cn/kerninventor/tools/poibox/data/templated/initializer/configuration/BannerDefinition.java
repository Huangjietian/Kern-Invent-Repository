package cn.kerninventor.tools.poibox.data.templated.initializer.configuration;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @author Kern
 * @date 2020/4/13 11:59
 * @description
 */
public interface BannerDefinition {

    BannerDefinition setContent(String value);

    BannerDefinition setCellStyle(CellStyle style);

    BannerDefinition setRange(CellRangeAddress cellRangeAddress);

    BannerDefinition setRange(int row1, int row2, int col1, int col2);
}
