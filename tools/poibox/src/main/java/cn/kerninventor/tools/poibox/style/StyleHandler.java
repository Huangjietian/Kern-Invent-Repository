package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.elements.StylerElements;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import java.util.HashMap;
import java.util.Objects;

/**
 * @Title: StyleCreator
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/29 17:25
 */
public final class StyleHandler extends BoxBracket implements Styler {

    private CellStyle cellStyle;
    HashMap<String, CellStyle> styleBox = new HashMap<>();

    public StyleHandler(POIBox poiBox) {
        super(poiBox);
        cellStyle = getParent().workbook().createCellStyle();
        CellStyle defaultStyle = getParent().workbook().createCellStyle();
        styleBox.put(Styler.DEFAULT_KEY, defaultStyle);
    }

    @Override
    public CellStyle getDefault() {
        return styleBox.get(Styler.DEFAULT_KEY);
    }

    @Override
    public CellStyle get() {
        CellStyle style = getParent().workbook().createCellStyle();
        style.cloneStyleFrom(this.cellStyle);
        return style;
    }

    @Override
    public Styler set(CellStyle style) {
        this.cellStyle = style;
        return this;
    }

    @Override
    public Styler reset() {
        this.cellStyle.cloneStyleFrom(styleBox.get(Styler.DEFAULT_KEY));
        return this;
    }

    @Override
    public CellStyle putInStyle(String key, CellStyle style) {
        CellStyle cellStyle = getParent().workbook().createCellStyle();
        cellStyle.cloneStyleFrom(Objects.requireNonNull(style, "The style cannot be null"));
        styleBox.put(Objects.requireNonNull(key, "Style key cannot be null"), cellStyle);
        return style;
    }

    @Override
    public CellStyle putOutStyle(String key) {
        return Objects.requireNonNull(styleBox.get(key), "The style for the key does not exist");
    }

    @Override
    public Styler setFont(Font font) {
        cellStyle.setFont(font);
        return this;
    }

    @Override
    public Styler setBorder(StylerElements.CellDirection direction, BorderStyle borderStyle) {
        switch (direction){
            case TOP :
                cellStyle.setBorderTop(borderStyle);
                break;
            case BOTTOM:
                cellStyle.setBorderBottom(borderStyle);
                break;
            case LEFT:
                cellStyle.setBorderLeft(borderStyle);
                break;
            case RIGHT:
                cellStyle.setBorderRight(borderStyle);
                break;
            case SURROUND:
                cellStyle.setBorderTop(borderStyle);
                cellStyle.setBorderBottom(borderStyle);
                cellStyle.setBorderLeft(borderStyle);
                cellStyle.setBorderRight(borderStyle);
                break;
            default:
        }
        return this;
    }

    @Override
    public Styler setBorderColor(StylerElements.CellDirection direction, HSSFColor.HSSFColorPredefined hssfColorPredefined) {
        switch (direction){
            case TOP :
                cellStyle.setTopBorderColor(hssfColorPredefined.getIndex());
                break;
            case BOTTOM:
                cellStyle.setBottomBorderColor(hssfColorPredefined.getIndex());
                break;
            case LEFT:
                cellStyle.setLeftBorderColor(hssfColorPredefined.getIndex());
                break;
            case RIGHT:
                cellStyle.setRightBorderColor(hssfColorPredefined.getIndex());
                break;
            case SURROUND:
                cellStyle.setTopBorderColor(hssfColorPredefined.getIndex());
                cellStyle.setBottomBorderColor(hssfColorPredefined.getIndex());
                cellStyle.setLeftBorderColor(hssfColorPredefined.getIndex());
                cellStyle.setRightBorderColor(hssfColorPredefined.getIndex());
                break;
            default:

        }
        return this;
    }

    @Override
    public Styler setFillPattern(FillPatternType fillPatternType) {
        cellStyle.setFillPattern(fillPatternType);
        return this;
    }

    @Override
    public Styler setFillBackgroundColor(HSSFColor.HSSFColorPredefined hssfColorPredefined) {
        cellStyle.setFillBackgroundColor(hssfColorPredefined.getIndex());
        return this;
    }

    @Override
    public Styler setFillForegroundColor(HSSFColor.HSSFColorPredefined hssfColorPredefined) {
        cellStyle.setFillForegroundColor(hssfColorPredefined.getIndex());
        return this;
    }

    @Override
    public Styler setVerticalAlignment(VerticalAlignment verticalAlignment) {
        cellStyle.setVerticalAlignment(verticalAlignment);
        return this;
    }

    @Override
    public Styler setAlignment(HorizontalAlignment horizontalAlignment) {
        cellStyle.setAlignment(horizontalAlignment);
        return this;
    }

    @Override
    public Styler setWholeCenter() {
        setVerticalAlignment(VerticalAlignment.CENTER).setAlignment(HorizontalAlignment.CENTER);
        return this;
    }

    @Override
    public Styler setWrapText(boolean wrapText) {
        cellStyle.setWrapText(wrapText);
        return this;
    }

    @Override
    public Styler setLocked(boolean locked) {
        cellStyle.setLocked(locked);
        return this;
    }

    @Override
    public Styler setIndention(short indention) {
        cellStyle.setIndention(indention);
        return this;
    }

    @Override
    public Styler setHidden(Boolean hidden) {
        cellStyle.setHidden(hidden);
        return this;
    }

    @Override
    public Styler setDataFormat(short index) {
        cellStyle.setDataFormat(index);
        return this;
    }

    @Override
    public CellStyle usualHeadLine(Integer fontSize) {
        return new StyleHandler(getParent())
                .reset()
                .setWholeCenter()
                .setBorder(StylerElements.CellDirection.SURROUND, BorderStyle.DOUBLE)
                .setFont(
                        new FontHandler(getParent())
                                .produce()
                                .setFontName(Fonter.NAME_HEADER)
                                .setFontSize(fontSize == null ? Fonter.SIZE_HEADLINE : fontSize)
                                .setBold(true)
                                .get())
                .get();
    }

    @Override
    public CellStyle usualTableHeader(Integer fontSize) {
        return new StyleHandler(getParent())
                .reset()
                .setWholeCenter()
                .setBorder(StylerElements.CellDirection.SURROUND, BorderStyle.THIN)
                .setFont(new FontHandler(getParent()).newSimpleFont(Fonter.NAME_HEADER, fontSize == null ? Fonter.SIZE_TABLEHEADER : fontSize))
                .get();
    }

    @Override
    public CellStyle usualTextPart(Integer fontSize) {
        return new StyleHandler(getParent())
                .reset()
                .setWholeCenter()
                .setWrapText(true)
                .setBorder(StylerElements.CellDirection.SURROUND, BorderStyle.THIN)
                .setFont(new FontHandler(getParent()).newSimpleFont(Fonter.NAME_TEXTPART, fontSize == null ? Fonter.SIZE_TEXTPART : fontSize))
                .get();
    }
}
