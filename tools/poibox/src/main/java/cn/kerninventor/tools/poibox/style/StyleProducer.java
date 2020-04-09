package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.style.enums.BorderDirection;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

/**
 * @Author Kern
 * @Date 2020/3/16 11:16
 */
public class StyleProducer {

    private CellStyle cellStyle;

    public StyleProducer(CellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }

    public CellStyle get() {
        return cellStyle;
    }

    public StyleProducer setFont(Font font) {
        cellStyle.setFont(font);
        return this;
    }

    public StyleProducer setBorder(BorderDirection direction, BorderStyle borderStyle) {
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

    public StyleProducer setBorderColor(BorderDirection direction, HSSFColor.HSSFColorPredefined hssfColorPredefined) {
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

    public StyleProducer setFillPattern(FillPatternType fillPatternType) {
        cellStyle.setFillPattern(fillPatternType);
        return this;
    }

    public StyleProducer setFillBackgroundColor(HSSFColor.HSSFColorPredefined hssfColorPredefined) {
        cellStyle.setFillBackgroundColor(hssfColorPredefined.getIndex());
        return this;
    }

    public StyleProducer setFillForegroundColor(HSSFColor.HSSFColorPredefined hssfColorPredefined) {
        cellStyle.setFillForegroundColor(hssfColorPredefined.getIndex());
        return this;
    }

    public StyleProducer setVerticalAlignment(VerticalAlignment verticalAlignment) {
        cellStyle.setVerticalAlignment(verticalAlignment);
        return this;
    }

    public StyleProducer setAlignment(HorizontalAlignment horizontalAlignment) {
        cellStyle.setAlignment(horizontalAlignment);
        return this;
    }

    public StyleProducer setWholeCenter() {
        setVerticalAlignment(VerticalAlignment.CENTER).setAlignment(HorizontalAlignment.CENTER);
        return this;
    }

    public StyleProducer setWrapText(boolean wrapText) {
        cellStyle.setWrapText(wrapText);
        return this;
    }

    public StyleProducer setLocked(boolean locked) {
        cellStyle.setLocked(locked);
        return this;
    }

    public StyleProducer setIndention(short indention) {
        cellStyle.setIndention(indention);
        return this;
    }

    public StyleProducer setHidden(Boolean hidden) {
        cellStyle.setHidden(hidden);
        return this;
    }

    public StyleProducer setDataFormat(short index) {
        cellStyle.setDataFormat(index);
        return this;
    }
}
