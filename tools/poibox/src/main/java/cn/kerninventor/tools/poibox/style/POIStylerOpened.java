package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.POIBoxLinker;
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
public final class POIStylerOpened extends POIBoxLinker implements POIStyler {

    private CellStyle cellStyle;
    HashMap<String, CellStyle> styleBox = new HashMap<>();

    public POIStylerOpened(POIBox poiBox) {
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
        styleBox.put(Objects.requireNonNull(key, "Style key cannot be null"),
                Objects.requireNonNull(style, "The style cannot be null"));
        return style;
    }

    @Override
    public CellStyle putOutStyle(String key) {
        return Objects.requireNonNull(styleBox.get(key), "The style for the key does not exist");
    }

    @Override
    public POIStyler setFont(Font font) {
        cellStyle.setFont(font);
        return this;
    }

    @Override
    public POIStyler setBorder(StylerElements.CellDirection direction, BorderStyle borderStyle) {
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
    public POIStyler setBorderColor(StylerElements.CellDirection direction, HSSFColor.HSSFColorPredefined hssfColorPredefined) {
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
    public POIStyler setFillPattern(FillPatternType fillPatternType) {
        cellStyle.setFillPattern(fillPatternType);
        return this;
    }

    @Override
    public POIStyler setFillBackgroundColor(HSSFColor.HSSFColorPredefined hssfColorPredefined) {
        cellStyle.setFillBackgroundColor(hssfColorPredefined.getIndex());
        return this;
    }

    @Override
    public POIStyler setFillForegroundColor(HSSFColor.HSSFColorPredefined hssfColorPredefined) {
        cellStyle.setFillForegroundColor(hssfColorPredefined.getIndex());
        return this;
    }

    @Override
    public POIStyler setVerticalAlignment(VerticalAlignment verticalAlignment) {
        cellStyle.setVerticalAlignment(verticalAlignment);
        return this;
    }

    @Override
    public POIStyler setAlignment(HorizontalAlignment horizontalAlignment) {
        cellStyle.setAlignment(horizontalAlignment);
        return this;
    }

    @Override
    public POIStyler setWholeCenter() {
        setVerticalAlignment(VerticalAlignment.CENTER).setAlignment(HorizontalAlignment.CENTER);
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
        return new POIStylerOpened(getParent())
                .reset()
                .setWholeCenter()
                .setBorder(StylerElements.CellDirection.SURROUND, BorderStyle.DOUBLE)
                .setFont(
                        new POIFonterOpened(getParent())
                                .setFontName(POIFonter.NAME_HEADER)
                                .setFontSize(fontSize == null ? POIFonter.SIZE_HEADLINE : fontSize)
                                .setBold(true)
                                .get())
                .get();
    }

    @Override
    public CellStyle usualTableHeader(Integer fontSize) {
        return new POIStylerOpened(getParent())
                .reset()
                .setWholeCenter()
                .setBorder(StylerElements.CellDirection.SURROUND, BorderStyle.THIN)
                .setFont(new POIFonterOpened(getParent()).newSimpleFont(POIFonter.NAME_HEADER, fontSize == null ? POIFonter.SIZE_TABLEHEADER : fontSize))
                .get();
    }

    @Override
    public CellStyle usualTextPart(Integer fontSize) {
        return new POIStylerOpened(getParent())
                .reset()
                .setWholeCenter()
                .setWrapText(true)
                .setBorder(StylerElements.CellDirection.SURROUND, BorderStyle.THIN)
                .setFont(new POIFonterOpened(getParent()).newSimpleFont(POIFonter.NAME_TEXTPART, fontSize == null ? POIFonter.SIZE_TEXTPART : fontSize))
                .get();
    }
}
