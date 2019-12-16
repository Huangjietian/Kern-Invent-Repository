package cn.kerninventor.tools.poibox.data.datatable;

import cn.kerninventor.tools.poibox.POIGadget;
import cn.kerninventor.tools.poibox.data.POIDataBoxOpened;
import cn.kerninventor.tools.poibox.data.datatable.validation.DataValidHandler;
import cn.kerninventor.tools.poibox.data.datatable.validation.ExcelValid;
import cn.kerninventor.tools.poibox.style.TabulationStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Title: DataTable
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable
 * @Author Kern
 * @Date 2019/12/9 15:25
 * @Description: TODO
 */
public class DataTabulation {

    /**
     * data tabulation class
     */
    private Class tabulationClass;
    /**
     * Default headline content, you can set value to this attribution in initialize.
     */
    private String headline;
    /**
     * Table start row index, default 0.
     */
    private int startRowIndex = 0;
    /**
     * The first body row index.
     */
    private int startTextRowIndex;
    /**
     * The number of lines in the body.
     */
    private int textRowNum = 20;
    /**
     * Default value is true, poibox adopt auto index in default.
     */
    private boolean autoColumnIndex = true;
    /**
     * NullAble, no styles are added to the cells when this attribution is null.
     */
    private TabulationStyle tabulationStyle;
    /**
     * NotNull
     */
    private List<DataColumn> columnsContainer;


    public int getFirstColumnIndex() {
        return columnsContainer.get(0).getColumnIndex();
    }

    public int getLastColumnIndex() {
        return columnsContainer.get(columnsContainer.size()-1).getColumnIndex();
    }

    public int getColumnSize() {
        return columnsContainer.size();
    }

    public Class getTabulationClass() {
        return tabulationClass;
    }

    public String getHeadline() {
        return headline;
    }

    public int getStartRowIndex() {
        return startRowIndex;
    }

    public int getStartTextRowIndex() {
        return startTextRowIndex;
    }

    public int getTextRowNum() {
        return textRowNum;
    }

    public boolean isAutoColumnIndex() {
        return autoColumnIndex;
    }

    public TabulationStyle getTabulationStyle() {
        return tabulationStyle;
    }

    public List<DataColumn> getColumnsContainer() {
        return columnsContainer;
    }

    /**
     * Annotation way
     * @param tableClass
     */
    public DataTabulation(Class tableClass) {
        ExcelTabulation excelTabulation;
        if ((excelTabulation = (ExcelTabulation) tableClass.getAnnotation(ExcelTabulation.class)) != null){
            tabulationClass = tableClass;
            headline = "".equals(excelTabulation.headline().trim()) ? null : excelTabulation.headline();
            startRowIndex = excelTabulation.startRowIndex();
            startTextRowIndex = headline == null ? startRowIndex + 1 : startRowIndex + 2;
            textRowNum = excelTabulation.textRowNum();
            autoColumnIndex = excelTabulation.autoColumnIndex();
            try {
                tabulationStyle = excelTabulation.style().newInstance();
            } catch (Exception e) {
                throw new IllegalArgumentException("An explicit, parameterless constructor is required in Tabulation.style()");
            }
            columnsContainer = resolveTable(tableClass);
        } else {
            throw new IllegalArgumentException("Data table object need to annotated @ExcelTabulation annotation!");
        }
    }

    private List<DataColumn> resolveTable(Class tableClass){
        List<DataColumn> dataColumns = new ArrayList();
        Field[] fields = tableClass.getDeclaredFields();
        ExcelColumn excelColumn;
        /**
         * auto index schema, the first column index is 0
         */
        if (autoColumnIndex){
            int index = 0 ;
            for (Field field : fields){
                if ((excelColumn = field.getDeclaredAnnotation(ExcelColumn.class)) != null
                        && excelColumn.value() != null
                        && !"".equals(excelColumn.value().trim())) {
                    DataColumn dataColumn = DataColumn.getInstance(
                            field,
                            field.getName(),
                            excelColumn.value(),
                            index++,
                            excelColumn.columnWidth(),
                            excelColumn.regEx(),
                            excelColumn.dateFormat()
                    );
                    dataColumns.add(dataColumn);
                }
            }
        } else {
            for (Field field : fields){
                if ((excelColumn = field.getDeclaredAnnotation(ExcelColumn.class)) != null
                        && excelColumn.value() != null
                        && !"".equals(excelColumn.value().trim())) {
                    DataColumn dataColumn = DataColumn.getInstance(
                            field,
                            field.getName(),
                            excelColumn.value(),
                            excelColumn.columnIndex(),
                            excelColumn.columnWidth(),
                            excelColumn.regEx(),
                            excelColumn.dateFormat()
                    );
                    dataColumns.add(dataColumn);
                }
            }
        }
        if (dataColumns.size() == 0){
            throw new IllegalArgumentException("Data table missing column definition, you should use @ExcelColumn to annotate object's fields!");
        }
        Collections.sort(dataColumns);
        return dataColumns;
    }

    public void tabulateTo(POIDataBoxOpened poiDataBox) {
        Sheet sheet = poiDataBox.getSheet();
        final int[] currentRowIndex = {getStartRowIndex()};
        String headline = getHeadline();
        /**
         * draw headline
         */
        if (headline != null && !"".equals(headline)){
            CellRangeAddress range = new CellRangeAddress(currentRowIndex[0], currentRowIndex[0], getFirstColumnIndex(), getLastColumnIndex());
            poiDataBox.getParent().layouter()
                    .mergedRegion(sheet, range)
                    .setMergeRangeContent(headline)
                    .setMergeRangeStyle(getTabulationStyle()
                            .getHeadLineStyle());
            currentRowIndex[0]++;
        }

        /**
         * draw table
         */
        Row row = sheet.createRow(currentRowIndex[0]);
        CellStyle cellStyle = poiDataBox.getParent().working().createCellStyle();
        getColumnsContainer().forEach( e -> {
            Cell cell = row.createCell(e.getColumnIndex());
            //type
            cell.setCellType(CellType.STRING);
            //value
            cell.setCellValue(e.getTitleName());
            //style
            cellStyle.cloneStyleFrom(getTabulationStyle().getTableHeadStyle());
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
                    handler.addValidation(this, e, sheet, annotation);
                }
            }

            for (int i = currentRowIndex[0] + 1 ; i < getTextRowNum() + currentRowIndex[0] + 1 ; i ++){
                Row textRow = POIGadget.getRowForce(sheet, i);
                Cell textCell = textRow.createCell(e.getColumnIndex());
                cellStyle.cloneStyleFrom(getTabulationStyle().getTextStyle());
                textCell.setCellStyle(cellStyle);
            }
        });

    }



}
