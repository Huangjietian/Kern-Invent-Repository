package cn.kerninventor.tools.poibox.opensource.style;

import cn.kerninventor.tools.poibox.opensource.developer.SealingVersion;
import cn.kerninventor.tools.poibox.opensource.style.enums.FontColor;
import org.apache.poi.ss.usermodel.Font;

/**
 * @author Kern
 * @date 2019/10/29 19:52
 */
@SealingVersion(
        version = 1.00,
        zh_description = "" +
                "1. 字体生成器" +
                "2. 提供一些范式元素的常量" +
                "3. 提供简单字体的通用方法" +
                "4. 根据注释生成字体的方法",
        possible = "部分常量可能不适用于英文环境，考虑后续优化"
)
public interface Fonter {

    String DEF_NAME_HEADER = "Arial Black";

    String DEF_NAME_TEXTPART = "SimSun";

    int DEF_SIZE_HEADLINE = 14;

    int DEF_SIZE_TABLEHEADER = 12;

    int DEF_SIZE_TEXTPART = 10;

    int DEFAULT_FONT_HEIGHT_IN_POINTS = 12;

    FontProducer producer();

    Font simpleFont(String fontName, int fontSize);

    Font simpleFont(String fontName, int fontSize, FontColor fontColor);

    Font simpleFont(String fontName, int fontSize, boolean bold);

    Font generate(cn.kerninventor.tools.poibox.opensource.data.templated.element.Font font);

}
