package cn.kerninventory.tools.excel.fluexcel.parser;

import cn.kerninventory.tools.excel.fluexcel.elements.ExcelTabulation;
import cn.kerninventory.tools.excel.fluexcel.elements.caption.Caption;
import cn.kerninventory.tools.excel.fluexcel.elements.caption.Line;
import cn.kerninventory.tools.excel.fluexcel.elements.caption.MultiLine;
import cn.kerninventory.tools.excel.fluexcel.elements.style.StyleGroup;
import cn.kerninventory.tools.excel.fluexcel.elements.style.StyleGroups;
import cn.kerninventory.tools.excel.fluexcel.elements.suspension.Picture;
import cn.kerninventory.tools.excel.fluexcel.elements.suspension.Pictures;
import cn.kerninventory.tools.excel.fluexcel.elements.suspension.TextBox;
import cn.kerninventory.tools.excel.fluexcel.elements.suspension.TextBoxes;
import org.apache.poi.ss.usermodel.CellStyle;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public class AnnotatedClassParser<T> {

    private Map<Integer, CellStyle> stylePublisher;
    private Object tabulationDefinition;
    private List columnDefinitions;
    private List lineDefinitions;
    private List textBoxDefinitions;
    private List pictureDefinitions;

    private AnnotatedClassParser() {
    }

    public static <T> AnnotatedClassParser<T> of(Class<T> tClass) {
        ExcelTabulation tabulation = Objects.requireNonNull(
                Objects.requireNonNull(tClass,
                        "Invalid configuration bean, the configuration bean is null!").getDeclaredAnnotation(ExcelTabulation.class),
                "Invalid configuration bean, The beans must be annotated with the " + ExcelTabulation.class.getName()
        );
        StyleGroup styleGroup = tClass.getDeclaredAnnotation(StyleGroup.class);
        StyleGroups styleGroups = tClass.getDeclaredAnnotation(StyleGroups.class);
        Caption caption = tClass.getDeclaredAnnotation(Caption.class);
        Line line = tClass.getDeclaredAnnotation(Line.class);
        MultiLine multiLine = tClass.getDeclaredAnnotation(MultiLine.class);
        TextBox textBox = tClass.getDeclaredAnnotation(TextBox.class);
        TextBoxes textBoxes = tClass.getDeclaredAnnotation(TextBoxes.class);
        Picture picture = tClass.getDeclaredAnnotation(Picture.class);
        Pictures pictures = tClass.getDeclaredAnnotation(Pictures.class);


        Field[] declaredFields = tClass.getDeclaredFields();

        /**
         * TODO do parse work!!!
         */




        return new AnnotatedClassParser<T>();
    }
}
