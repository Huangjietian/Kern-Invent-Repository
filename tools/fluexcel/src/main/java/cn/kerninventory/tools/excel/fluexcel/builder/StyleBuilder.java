package cn.kerninventory.tools.excel.fluexcel.builder;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

/**
 * <p>
 *     风格生成器
 * </p>
 * @author Kern
 * @version 1.0
 */
public final class StyleBuilder {

    private CellStyle cellStyle;

    public StyleBuilder(CellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }

    /**
     * 获取风格
     * @return
     */
    public CellStyle get() {
        return cellStyle;
    }

    /**
     * 设置字体
     * @param font
     * @return
     */
    public StyleBuilder setFont(Font font) {
        cellStyle.setFont(font);
        return this;
    }

    /**
     * 设置上边框
     * @param borderStyle
     * @param hssfColorPredefined
     * @return
     */
    public StyleBuilder setTopBorder(BorderStyle borderStyle, HSSFColor.HSSFColorPredefined hssfColorPredefined){
        if (borderStyle != null){
            cellStyle.setBorderTop(borderStyle);
        }
        if (hssfColorPredefined != null) {
            cellStyle.setTopBorderColor(hssfColorPredefined.getIndex());
        }
        return this;
    }

    /**
     * 设置下边框
     * @param borderStyle
     * @param hssfColorPredefined
     * @return
     */
    public StyleBuilder setBottomBorder(BorderStyle borderStyle, HSSFColor.HSSFColorPredefined hssfColorPredefined){
        if (borderStyle != null){
            cellStyle.setBorderBottom(borderStyle);
        }
        if (hssfColorPredefined != null) {
            cellStyle.setBottomBorderColor(hssfColorPredefined.getIndex());
        }
        return this;
    }

    /**
     * 设置左边框
     * @param borderStyle
     * @param hssfColorPredefined
     * @return
     */
    public StyleBuilder setLeftBorder(BorderStyle borderStyle, HSSFColor.HSSFColorPredefined hssfColorPredefined){
        if (borderStyle != null){
            cellStyle.setBorderLeft(borderStyle);
        }
        if (hssfColorPredefined != null) {
            cellStyle.setLeftBorderColor(hssfColorPredefined.getIndex());
        }
        return this;
    }

    /**
     * 设置右边框
     * @param borderStyle
     * @param hssfColorPredefined
     * @return
     */
    public StyleBuilder setRightBorder(BorderStyle borderStyle, HSSFColor.HSSFColorPredefined hssfColorPredefined){
        if (borderStyle != null){
            cellStyle.setBorderRight(borderStyle);
        }
        if (hssfColorPredefined != null) {
            cellStyle.setRightBorderColor(hssfColorPredefined.getIndex());
        }
        return this;
    }

    /**
     * 设置四周边框
     * @param borderStyle
     * @param hssfColorPredefined
     * @return
     */
    public StyleBuilder setSurroundBorder(BorderStyle borderStyle, HSSFColor.HSSFColorPredefined hssfColorPredefined) {
        if (borderStyle != null) {
            cellStyle.setBorderTop(borderStyle);
            cellStyle.setBorderBottom(borderStyle);
            cellStyle.setBorderLeft(borderStyle);
            cellStyle.setBorderRight(borderStyle);
        }
        if (hssfColorPredefined != null) {
            cellStyle.setTopBorderColor(hssfColorPredefined.getIndex());
            cellStyle.setBottomBorderColor(hssfColorPredefined.getIndex());
            cellStyle.setLeftBorderColor(hssfColorPredefined.getIndex());
            cellStyle.setRightBorderColor(hssfColorPredefined.getIndex());
        }
        return this;
    }

    /**
     * 设置填充形式
     * @param fillPatternType
     * @return
     */
    public StyleBuilder setFillPattern(FillPatternType fillPatternType) {
        cellStyle.setFillPattern(fillPatternType);
        return this;
    }

    /**
     * 设置背景填充颜色
     * @param hssfColorPredefined
     * @return
     */
    public StyleBuilder setFillBackgroundColor(HSSFColor.HSSFColorPredefined hssfColorPredefined) {
        cellStyle.setFillBackgroundColor(hssfColorPredefined.getIndex());
        return this;
    }

    /**
     * 设置前景填充颜色
     * @param hssfColorPredefined
     * @return
     */
    public StyleBuilder setFillForegroundColor(HSSFColor.HSSFColorPredefined hssfColorPredefined) {
        cellStyle.setFillForegroundColor(hssfColorPredefined.getIndex());
        return this;
    }

    /**
     * 设置垂直居中形式
     * @param verticalAlignment
     * @return
     */
    public StyleBuilder setVerticalAlignment(VerticalAlignment verticalAlignment) {
        cellStyle.setVerticalAlignment(verticalAlignment);
        return this;
    }

    /**
     * 设置水平居中形式
     * @param horizontalAlignment
     * @return
     */
    public StyleBuilder setAlignment(HorizontalAlignment horizontalAlignment) {
        cellStyle.setAlignment(horizontalAlignment);
        return this;
    }

    /**
     * 设置完全居中
     * @return
     */
    public StyleBuilder setWholeCenter() {
        setVerticalAlignment(VerticalAlignment.CENTER).setAlignment(HorizontalAlignment.CENTER);
        return this;
    }

    /**
     * 设置自动换行
     * @param wrapText
     * @return
     */
    public StyleBuilder setWrapText(boolean wrapText) {
        cellStyle.setWrapText(wrapText);
        return this;
    }

    /**
     * 设置风格锁
     * @param locked
     * @return
     */
    public StyleBuilder setLocked(boolean locked) {
        cellStyle.setLocked(locked);
        return this;
    }

    /**
     * 设置缩进
     * @param indention
     * @return
     */
    public StyleBuilder setIndention(short indention) {
        cellStyle.setIndention(indention);
        return this;
    }

    /**
     * 设置隐藏
     * @param hidden
     * @return
     */
    public StyleBuilder setHidden(Boolean hidden) {
        cellStyle.setHidden(hidden);
        return this;
    }

    /**
     * 设置单元格格式
     * @param index
     * @return
     */
    public StyleBuilder setDataFormat(short index) {
        cellStyle.setDataFormat(index);
        return this;
    }
}
