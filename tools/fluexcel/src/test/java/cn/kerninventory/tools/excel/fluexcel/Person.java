package cn.kerninventory.tools.excel.fluexcel;

import cn.kerninventory.tools.excel.fluexcel.elements.ExcelColumn;
import cn.kerninventory.tools.excel.fluexcel.elements.ExcelTabulation;
import cn.kerninventory.tools.excel.fluexcel.elements.caption.Section;
import cn.kerninventory.tools.excel.fluexcel.elements.caption.HeadLine;
import cn.kerninventory.tools.excel.fluexcel.elements.caption.Line;
import cn.kerninventory.tools.excel.fluexcel.elements.caption.MultiLine;
import cn.kerninventory.tools.excel.fluexcel.elements.style.Font;
import cn.kerninventory.tools.excel.fluexcel.elements.style.Style;
import cn.kerninventory.tools.excel.fluexcel.elements.style.StyleGroup;
import cn.kerninventory.tools.excel.fluexcel.elements.style.StyleGroups;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */

@StyleGroups({
        @StyleGroup(
                value = 0,
                headStyle = @Style(font = @Font(fontName = "黑体", fontSize = 16), fillColor = HSSFColor.HSSFColorPredefined.BLUE),
                bodyStyle = @Style(font = @Font(fontSize = 12))
        ),
        @StyleGroup(
                value = 1,
                headStyle = @Style(font = @Font(fontName = "黑体", fontSize = 16), fillColor = HSSFColor.HSSFColorPredefined.RED),
                bodyStyle = @Style(font = @Font(fontSize = 12))
        )
})
@MultiLine(value = {
        @Line(value = {
                @Section(value = "日期: ", colEnd = 2),
                @Section(value = "%d{yyyy-MM-dd}", colEnd = 5),
                @Section(value = "单位： ", colEnd = 7),
                @Section(value = "XXX公司财务部", colEnd = 10),
                @Section(value = "主管签字:", colEnd = 12),
                @Section(value = "           ", colEnd = 15)
        }),
        @Line
}
)
@HeadLine(value = "大标题", rowNumber = 2)
@ExcelTabulation
public class Person {

    private Long id;

    @ExcelColumn(value = "姓名", styleSubs = 0)
    private String name;

    @ExcelColumn(value = "年龄", styleSubs = 1)
    private int age;
}
