package cn.kerninventor.tools.poibox.demo;

import cn.kerninventor.tools.poibox.data.tabulation.ExcelBanner;
import cn.kerninventor.tools.poibox.data.tabulation.ExcelColumn;
import cn.kerninventor.tools.poibox.data.tabulation.ExcelTabulation;
import cn.kerninventor.tools.poibox.data.tabulation.element.*;
import cn.kerninventor.tools.poibox.data.tabulation.translator.ColumnDataTranslate;
import cn.kerninventor.tools.poibox.data.tabulation.validation.array.EnumExplicitListDataValid;
import cn.kerninventor.tools.poibox.data.tabulation.validation.array.ExplicitListDataValid;

/**
 * @author Kern
 * @date 2020/5/7 12:48
 * @description
 */
@ExcelTabulation(
        banners = {
                @ExcelBanner(
                        value = "标题1",
                        range = @Range(fistRow = 0, lastRow = 1),
                        style = @Style(index = 1, font = @Font(fontName = "黑体", fontSize = 15))
                )
        },
        textboxes = @Textbox(
                value = "  \n" +
                        "  注意：\n" +
                        "  1. 行数限制 \n" +
                        "  2. 单元格限制 \n" +
                        "  3. 修改限制 \n" +
                        "  4. 内容限制 \n" +
                        "  ",
                anchorIndex = @Anchor(col1 = 1, row1 = 24, col2 = 5, row2 = 32)
        )

)
public class Humen {

    @ExplicitListDataValid(list = {"中国", "美国", "日本"})
    @ExcelColumn("国家")
    private String country;

    @ExcelColumn("姓名")
    private String name;

    @EnumExplicitListDataValid(enumClass = GenderEnum.class)
    @ExcelColumn(value = "性别", dataTranslate = @ColumnDataTranslate(translator = "gender"))
    private String gender;

    public Humen(String country, String name, String gender) {
        this.country = country;
        this.name = name;
        this.gender = gender;
    }
}
