package cn.kerninventor.tools.poibox.data.datatable;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.DataValidHandler;
import cn.kerninventor.tools.poibox.data.exception.IllegalSourceClassOfTabulationException;
import cn.kerninventor.tools.poibox.data.exception.IllegalTypeOfCellValueException;
import cn.kerninventor.tools.poibox.data.utils.CellValueUtil;
import cn.kerninventor.tools.poibox.style.Styler;
import cn.kerninventor.tools.poibox.style.TabulationStyle;
import cn.kerninventor.tools.poibox.utils.ReflectUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @Title: DataTable
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable
 * @Author Kern
 * @Date 2019/12/9 15:25
 * @Description: excel 数据表解析处理类
 */
public class ExcelTabulationDataProcessor<T> {

    /**
     * data tabulation class
     */
    private Class<T> tabulationClass;
    /**
     * Default headline content, you can set value to this attribution in initialize.
     */
    private String headline;
    /**
     * Table start row index, default 0.
     */
    private int startRowIndex;
    /**
     * headline / table head/ table text row index.
     */
    private int headlineRdx, tableHeadRdx, tableTextRdx;
    /**
     * The number of lines in the body.
     */
    private int textRowNum;
    /**
     * Default value is true, poibox adopt auto index in default.
     */
    private boolean autoColumnIndex;
    /**
     * NullAble, no styles are added to the cells when this attribution is null.
     */
    private TabulationStyle tabulationStyle;
    /**
     * NotNull
     */
    private List<ExcelcolumnDataAccepter> columnsContainer;


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

    public int getHeadlineRdx() {
        return headlineRdx;
    }

    public void setHeadlineRdx(int headlineRdx) {
        this.headlineRdx = headlineRdx;
    }

    public int getTableHeadRdx() {
        return tableHeadRdx;
    }

    public void setTableHeadRdx(int tableHeadRdx) {
        this.tableHeadRdx = tableHeadRdx;
    }

    public int getTableTextRdx() {
        return tableTextRdx;
    }

    public void setTableTextRdx(int tableTextRdx) {
        this.tableTextRdx = tableTextRdx;
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

    public List<ExcelcolumnDataAccepter> getColumnsContainer() {
        return columnsContainer;
    }



    /**
     * Annotation way
     * @param tableClass
     */
    public ExcelTabulationDataProcessor(Class<T> tableClass) {
        dataTabulationSourceClassValidate(tableClass);
        ExcelTabulation excelTabulation = tableClass.getAnnotation(ExcelTabulation.class);
        tabulationClass = tableClass;

        //row index calc and headline set value.
        headline = "".equals(excelTabulation.headline().trim()) ? null : excelTabulation.headline();
        startRowIndex = excelTabulation.startRowIndex();
        headlineRdx = headline == null ? startRowIndex - 1 : startRowIndex;
        tableHeadRdx = headlineRdx + 1;
        tableTextRdx = tableHeadRdx + 1;
        textRowNum = excelTabulation.textRowNum();

        autoColumnIndex = excelTabulation.autoColumnIndex();
        try {
            tabulationStyle = excelTabulation.style().newInstance();
        } catch (Exception e) {
            throw new IllegalSourceClassOfTabulationException("An explicit, parameterless constructor is required in Tabulation.style()");
        }
        columnsContainer = resolveTable(tableClass);
    }

    private List<ExcelcolumnDataAccepter> resolveTable(Class tableClass){
        List<ExcelcolumnDataAccepter> excelcolumnDataAccepters = new ArrayList();
        Field[] fields = tableClass.getDeclaredFields();
        ExcelColumn excelColumn;
        if (autoColumnIndex){
            int index = 0 ;
            for (Field field : fields){
                if ((excelColumn = field.getDeclaredAnnotation(ExcelColumn.class)) != null) {
                    excelcolumnDataAccepters.add(ExcelcolumnDataAccepter.getInstance(field, excelColumn, index++));
                }
            }
        } else {
            for (Field field : fields){
                if ((excelColumn = field.getDeclaredAnnotation(ExcelColumn.class)) != null) {
                    excelcolumnDataAccepters.add(ExcelcolumnDataAccepter.getInstance(field, excelColumn,excelColumn.columnIndex()));
                }
            }
        }
        if (excelcolumnDataAccepters.size() == 0){
            throw new IllegalSourceClassOfTabulationException("Data table lack column definition, you should use @ExcelColumn to annotate object's fields!");
        }
        Collections.sort(excelcolumnDataAccepters);
        return excelcolumnDataAccepters;
    }

    public void tabulateTo(Sheet sheet, POIBox poiBox) {
        Workbook workbook = sheet.getWorkbook();
        String headline = getHeadline();
        /**
         * draw headline
         */
        if (startRowIndex == headlineRdx){
            CellRangeAddress range = new CellRangeAddress(headlineRdx, headlineRdx, getFirstColumnIndex(), getLastColumnIndex());
            poiBox.layouter()
                    .mergedRegion(sheet, range)
                    .setMergeRangeContent(headline)
                    .setMergeRangeStyle(tabulationStyle.getHeadLineStyle());
        }

        /**
         * draw table
         */
        Row row = sheet.createRow(tableHeadRdx);
        CellStyle tableHeadStyle = Styler.cloneStyle(poiBox.workbook(), tabulationStyle.getTableHeadStyle());
        Font tableHeadFont = sheet.getWorkbook().getFontAt(tableHeadStyle.getFontIndexAsInt());
        CellStyle tableTextStyle = Styler.cloneStyle(poiBox.workbook(), tabulationStyle.getTextStyle());
        DataFormat dataFormat = workbook.createDataFormat();

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
            //data validation
            if (e.getValidAnnotation() != null) {
                DataValidHandler.getInstance(e.getValidAnnotation())
                        .addValidation(this, e, sheet, e.getValidAnnotation());
            }
            else {
                DataValidHandler.qualifiedTypeValidHandler(this, e, sheet);
            }

            //text style
            CellStyle columnStyle = workbook.createCellStyle();
            columnStyle.cloneStyleFrom(tableTextStyle);
            if (e.getDataFormatEx() != null){
                columnStyle.setDataFormat(dataFormat.getFormat(e.getDataFormatEx()));
            }
            for (int i = 0 ; i < getTextRowNum(); i ++){
                Row textRow = BoxGadget.getRowForce(sheet, i + tableTextRdx);
                Cell textCell = textRow.createCell(e.getColumnIndex());
                textCell.setCellStyle(columnStyle);
            }
        });
    }

    /**
     * TODO 提取数据未完成
     * @param sheet
     * @return
     */
    public List<T> extractDatasFrom(Sheet sheet) {
        return null;
    }


    public static void dataTabulationSourceClassValidate(Class tabulationClass) throws IllegalSourceClassOfTabulationException {
        if (tabulationClass.getAnnotation(ExcelTabulation.class) == null){
            throw new IllegalSourceClassOfTabulationException("Data tabulation POJO need to annotated @ExcelTabulation annotation!");
        }
    }

}
