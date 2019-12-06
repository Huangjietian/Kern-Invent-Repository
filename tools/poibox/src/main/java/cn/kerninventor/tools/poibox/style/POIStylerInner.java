package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.enums.StylerElements;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import java.util.HashMap;
import java.util.Objects;

/**
 * @Title: StyleCreator
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/29 17:25
 */
public final class POIStylerInner extends POICreator implements POIStyler {

    private CellStyle cellStyle;
    HashMap<String, CellStyle> styleBox = new HashMap<>();

    public POIStylerInner(POIBox poiBox) {
        super(poiBox);
        cellStyle = getParent().working().createCellStyle();
        styleBox.put(POIStyler.DEFAULT_KEY, cellStyle);
    }

    @Override
    public CellStyle getDefault() {
        return styleBox.get(POIStyler.DEFAULT_KEY);
    }

    @Override
    public CellStyle get() {
        return cellStyle;
    }

    @Override
    public POIStyler set(CellStyle style) {
        this.cellStyle = style;
        return this;
    }

    @Override
    public POIStyler reset() {
        this.cellStyle = styleBox.get(POIStyler.DEFAULT_KEY);
        return this;
    }

    @Override
    public CellStyle putInStyle(String key, CellStyle style) {
        styleBox.put(Objects.requireNonNull(key, "风格标识不能为空，Style key cannot be null"),
                Objects.requireNonNull(style, "风格不能为空，The style cannot be null"));
        return style;
    }

    @Override
    public CellStyle putOutStyle(String key) {
        return Objects.requireNonNull(styleBox.get(key), "该标识的风格不存在，The style for the key does not exist");
    }

    @Override
    public POIStyler setFont(Font font) {
        cellStyle.setFont(font);
        return this;
    }

    @Override
    public POIStyler setBorder(StylerElements.CellDirection direction, StylerElements.BorderLine borderLine) {
        switch (direction){
            case TOP :
                cellStyle.setBorderTop(borderLine.getIndex());
                break;
            case BOTTOM:
                cellStyle.setBorderBottom(borderLine.getIndex());
                break;
            case LEFT:
                cellStyle.setBorderLeft(borderLine.getIndex());
                break;
            case RIGHT:
                cellStyle.setBorderRight(borderLine.getIndex());
                break;
            case SURROUND:
                cellStyle.setBorderTop(borderLine.getIndex());
                cellStyle.setBorderBottom(borderLine.getIndex());
                cellStyle.setBorderLeft(borderLine.getIndex());
                cellStyle.setBorderRight(borderLine.getIndex());
                break;
            default:

        }
        return this;
    }

    @Override
    public POIStyler setBorderColor(StylerElements.CellDirection direction, HSSFColor color) {
        switch (direction){
            case TOP :
                cellStyle.setTopBorderColor(color.getIndex());
                break;
            case BOTTOM:
                cellStyle.setBottomBorderColor(color.getIndex());
                break;
            case LEFT:
                cellStyle.setLeftBorderColor(color.getIndex());
                break;
            case RIGHT:
                cellStyle.setRightBorderColor(color.getIndex());
                break;
            case SURROUND:
                cellStyle.setTopBorderColor(color.getIndex());
                cellStyle.setBottomBorderColor(color.getIndex());
                cellStyle.setLeftBorderColor(color.getIndex());
                cellStyle.setRightBorderColor(color.getIndex());
                break;
            default:

        }
        return this;
    }

    @Override
    public POIStyler setFillPattern(StylerElements.FillPattern fillPattern) {
        cellStyle.setFillPattern(fillPattern.getIndex());
        return this;
    }

    @Override
    public POIStyler setFillBackgroundColor(HSSFColor color) {
        cellStyle.setFillBackgroundColor(color.getIndex());
        return this;
    }

    @Override
    public POIStyler setFillForegroundColor(HSSFColor color) {
        cellStyle.setFillForegroundColor(color.getIndex());
        return this;
    }

    @Override
    public POIStyler setVerticalAlignment(StylerElements.Vertical vertical) {
        cellStyle.setVerticalAlignment(vertical.getIndex());
        return this;
    }

    @Override
    public POIStyler setAlignment(StylerElements.Align align) {
        cellStyle.setAlignment(align.getIndex());
        return this;
    }

    @Override
    public POIStyler setWholeCenter() {
        setVerticalAlignment(StylerElements.Vertical.VERTICAL_CENTER).setAlignment(StylerElements.Align.ALIGN_CENTER);
        return this;
    }

    @Override
    public POIStyler setWrapText(boolean wrapText) {
        cellStyle.setWrapText(wrapText);
        return this;
    }

    @Override
    public POIStyler setLocked(boolean locked) {
        cellStyle.setLocked(locked);
        return this;
    }

    @Override
    public POIStyler setIndention(short indention) {
        cellStyle.setIndention(indention);
        return this;
    }

    @Override
    public POIStyler setHidden(Boolean hidden) {
        cellStyle.setHidden(hidden);
        return this;
    }

    @Override
    public POIStyler setDataFormat(short index) {
        cellStyle.setDataFormat(index);
        return this;
    }

    @Override
    public CellStyle usualHeadLine(Integer fontSize) {
        return new POIStylerInner(getParent())
                .setWholeCenter()
                .setBorder(StylerElements.CellDirection.SURROUND, StylerElements.BorderLine.BORDER_THIN)
                .setFont(
                        new POIFonterInner(getParent())
                                .setFontName(POIFonter.NAME_HEADER)
                                .setFontSize(fontSize == null ? POIFonter.SIZE_HEADLINE : fontSize)
                                .setBold(true)
                                .get())
                .get();
    }

    @Override
    public CellStyle usualTableHeader(Integer fontSize) {
        return new POIStylerInner(getParent())
                .setWholeCenter()
                .setBorder(StylerElements.CellDirection.SURROUND, StylerElements.BorderLine.BORDER_THIN)
                .setFont(new POIFonterInner(getParent()).newSimpleFont(POIFonter.NAME_HEADER, fontSize == null ? POIFonter.SIZE_TABLEHEADER : fontSize))
                .get();
    }

    @Override
    public CellStyle usualTextPart(Integer fontSize) {
        return new POIStylerInner(getParent())
                .setWholeCenter()
                .setWrapText(true)
                .setBorder(StylerElements.CellDirection.SURROUND, StylerElements.BorderLine.BORDER_THIN)
                .setFont(new POIFonterInner(getParent()).newSimpleFont(POIFonter.NAME_TEXTPART, fontSize == null ? POIFonter.SIZE_TEXTPART : fontSize))
                .get();
    }
}
