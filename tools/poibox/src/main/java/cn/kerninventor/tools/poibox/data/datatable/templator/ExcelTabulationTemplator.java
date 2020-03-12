package cn.kerninventor.tools.poibox.data.datatable.templator;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.DataValidBuilder;
import cn.kerninventor.tools.poibox.data.datatable.initializer.ExcelColumnInitializer;
import cn.kerninventor.tools.poibox.data.datatable.initializer.ExcelTabulationInitializer;
import cn.kerninventor.tools.poibox.style.Fonter;
import cn.kerninventor.tools.poibox.style.Styler;
import org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import java.util.List;


/**
 * @Title ExcelTabulationTemplator
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.poibox.data.datatable
 * @Author Kern
 * @Date 2020/3/12 18:53
 * @Description TODO
 */
public class ExcelTabulationTemplator {

    private ExcelTabulationInitializer initializer;

    private Headline headline;

    public ExcelTabulationTemplator(ExcelTabulationInitializer initializer) {
        this.initializer = initializer;
    }

    public Headline getHeadline() {
        return headline;
    }

    public ExcelTabulationTemplator tabulateTo(Sheet sheet, POIBox poiBox, boolean valid) {
        drawHeadLine(sheet, poiBox);
        drawTable(sheet, valid);
        return this;
    }

    /**
     * draw headline
     * @param sheet
     * @param poiBox
     */
    private void drawHeadLine(Sheet sheet, POIBox poiBox){
        if (initializer.getStartRowIndex() == initializer.getHeadlineRdx()){
            CellRangeAddress range = new CellRangeAddress(
                    initializer.getHeadlineRdx(),
                    initializer.getHeadlineRdx(),
                    initializer.getFirstColumnIndex(),
                    initializer.getLastColumnIndex()
            );
            CellStyle style = Styler.cloneStyle(sheet.getWorkbook(), initializer.getTabulationStyle().getHeadLineStyle());
            String content = initializer.getHeadline();
            poiBox.layouter()
                    .mergedRegion(sheet, range)
                    .setMergeRangeContent(content)
                    .setMergeRangeStyle(style);
            this.headline = new Headline(this, sheet,range, style, content);
        }
    }

    /**
     * draw table
     */
    private void drawTable(Sheet sheet, boolean valid){
        Row row = sheet.createRow(initializer.getTableHeadRdx());
        CellStyle tableHeadStyle = Styler.cloneStyle(sheet.getWorkbook(), initializer.getTabulationStyle().getTableHeadStyle());
        Font tableHeadFont = sheet.getWorkbook().getFontAt(tableHeadStyle.getFontIndexAsInt());
        CellStyle tableTextStyle = Styler.cloneStyle(sheet.getWorkbook(), initializer.getTabulationStyle().getTextStyle());
        DataFormat dataFormat = sheet.getWorkbook().createDataFormat();

        List<ExcelColumnInitializer> columnsContainer = initializer.getColumnsContainer();
        columnsContainer.forEach( e -> {
            Cell cell = row.createCell(e.getColumnIndex());
            //value
            cell.setCellValue(e.getTitleName());
            //style
            cell.setCellStyle(tableHeadStyle);

            //column width
            if (e.getColumnWidth() == -1 ){
                int columnWidth = BoxGadget.getCellWidthByStringContent(e.getTitleName(), tableHeadFont.getFontHeightInPoints());
                sheet.setColumnWidth(e.getColumnIndex(), columnWidth);
            } else {
                sheet.setColumnWidth(e.getColumnIndex(), e.getColumnWidth());
            }

            //text style
            CellStyle columnStyle = sheet.getWorkbook().createCellStyle();
            columnStyle.cloneStyleFrom(tableTextStyle);
            if (e.getDataFormatEx() != null){
                columnStyle.setDataFormat(dataFormat.getFormat(e.getDataFormatEx()));
            }
            for (int i = 0 ; i < initializer.getTextRowNum(); i ++){
                Row textRow = BoxGadget.getRowForce(sheet, i + initializer.getTableTextRdx());
                Cell textCell = textRow.createCell(e.getColumnIndex());
                textCell.setCellStyle(columnStyle);
            }

            //data validation
            if (valid && e.getValidAnnotation() != null) {
                DataValidBuilder.getInstance(e.getValidAnnotation())
                        .addValidation(initializer, e, sheet);
            }

        });
    }


}
