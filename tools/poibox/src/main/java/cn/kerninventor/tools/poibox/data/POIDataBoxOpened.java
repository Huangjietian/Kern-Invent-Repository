package cn.kerninventor.tools.poibox.data;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.POIBoxLinker;
import cn.kerninventor.tools.poibox.POIGadget;
import cn.kerninventor.tools.poibox.data.datatable.DataTabulation;
import cn.kerninventor.tools.poibox.data.datatable.validation.DataValidHandler;
import cn.kerninventor.tools.poibox.data.datatable.validation.ExcelValid;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.lang.annotation.Annotation;

/**
 * @Title: POIDataBoxOpened
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data
 * @Author Kern
 * @Date 2019/12/11 17:05
 * @Description: TODO
 */
public class POIDataBoxOpened extends POIBoxLinker implements POIDataBox {

    private Sheet sheet;

    public POIDataBoxOpened(POIBox poiBox, String sheetName) {
        super(poiBox);
        sheet = getParent().working().getSheet(sheetName) == null ? getParent().working().createSheet(sheetName) : getParent().working().getSheet(sheetName);
    }

    @Override
    public void template(Class clazz) {
        DataTabulation tabulation = new DataTabulation(clazz);
        drawTabulation(tabulation);
    }

    public void drawTabulation(DataTabulation tabulation) {
        final int[] currentRowIndex = {tabulation.getStartRowIndex()};

        String headline = tabulation.getHeadline();
        /**
         * draw headline
         */
        if (headline != null && !"".equals(headline)){
            CellRangeAddress range = new CellRangeAddress(currentRowIndex[0], currentRowIndex[0], tabulation.getFirstColumnIndex(), tabulation.getLastColumnIndex());
            getParent().layouter()
                    .mergedRegion(sheet, range)
                    .setMergeRangeContent(headline)
                    .setMergeRangeStyle(tabulation.getTabulationStyle()
                            .getHeadLineStyle());
            currentRowIndex[0]++;
        }

        /**
         * draw table
         */
        Row row = sheet.createRow(currentRowIndex[0]);
        CellStyle cellStyle = getParent().working().createCellStyle();
        tabulation.getColumnsContainer().forEach( e -> {
            Cell cell = row.createCell(e.getColumnIndex());
            //type
            cell.setCellType(CellType.STRING);
            //value
            cell.setCellValue(e.getTitleName());
            //style
            cellStyle.cloneStyleFrom(tabulation.getTabulationStyle().getTableHeadStyle());
            cell.setCellStyle(cellStyle);
            //column width
            if (e.getColumnWidth() == -1 ){
                sheet.setColumnWidth(e.getColumnIndex(), POIGadget.getCellWidthByStringContent(e.getTitleName()));
            } else {
                sheet.setColumnWidth(e.getColumnIndex(), e.getColumnWidth());
            }

            Annotation[] annotations = e.getField().getDeclaredAnnotations();
            for (Annotation annotation : annotations){
                if (annotation.annotationType().isAnnotationPresent(ExcelValid.class)){
                    DataValidHandler handler = DataValidHandler.getInstance(annotation);
                    handler.addValidation(tabulation, e, sheet, annotation);
                }
            }

            for (int i = currentRowIndex[0] + 1 ; i < tabulation.getTextRowNum() + currentRowIndex[0] + 1 ; i ++){
                Row textRow = POIGadget.getRowForce(sheet, i);
                Cell textCell = textRow.createCell(e.getColumnIndex());
                cellStyle.cloneStyleFrom(tabulation.getTabulationStyle().getTextStyle());
                textCell.setCellStyle(cellStyle);
            }
        });

    }
}
