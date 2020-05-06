package cn.kerninventor.tools.poibox.opensource.data.templated.initializer.configuration;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

/**
 * @author Kern
 * @date 2020/4/13 11:58
 * @description
 */
public interface TabConfiguration {

    TabConfiguration addBanner(String value, CellStyle cellStyle, int row1, int row2);

    TabConfiguration addBanner(String value, CellStyle cellStyle, int row1, int row2, int col1, int col2);

    TabConfiguration addBanner(String value, CellStyle cellStyle, CellRangeAddress cellRangeAddress);

    List<? extends BannerDefinition> getBanners();

    BannerDefinition getBannerAt(int index);

}
