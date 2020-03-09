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
     * The first body row index.
     */
    private int startTextRowIndex;
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
        headline = "".equals(excelTabulation.headline().trim()) ? null : excelTabulation.headline();
        startRowIndex = excelTabulation.startRowIndex();
        startTextRowIndex = headline == null ? startRowIndex + 1 : startRowIndex + 2;
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

    public void tabulateTo(Sheet sheet, POIBox poiBox, List<T> datas) {
        Workbook workbook = sheet.getWorkbook();
        final int[] currentRowIndex = {getStartRowIndex()};
        String headline = getHeadline();
        /**
         * draw headline
         */
        if (headline != null && !"".equals(headline)){
            CellRangeAddress range = new CellRangeAddress(currentRowIndex[0], currentRowIndex[0], getFirstColumnIndex(), getLastColumnIndex());
            poiBox.layouter()
                    .mergedRegion(sheet, range)
                    .setMergeRangeContent(headline)
                    .setMergeRangeStyle(tabulationStyle.getHeadLineStyle());
            currentRowIndex[0]++;
        }

        /**
         * draw table
         */
        Row row = sheet.createRow(currentRowIndex[0]++);
        CellStyle tableHeadStyle = Styler.cloneStyle(poiBox.workbook(), tabulationStyle.getTableHeadStyle());
        Font tableHeadFont = sheet.getWorkbook().getFontAt(tableHeadStyle.getFontIndexAsInt());
        CellStyle tableTextStyle = Styler.cloneStyle(poiBox.workbook(), tabulationStyle.getTextStyle());
        Font tableTextFont = sheet.getWorkbook().getFontAt(tableTextStyle.getFontIndexAsInt());
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
            } else {
                DataValidHandler.qualifiedTypeValidHandler(this, e, sheet);
            }
            //text style
            CellStyle columnStyle = workbook.createCellStyle();
            columnStyle.cloneStyleFrom(tableTextStyle);
            if (e.getDataFormatEx() != null){
                columnStyle.setDataFormat(dataFormat.getFormat(e.getDataFormatEx()));
            }
            for (int i = 0 ; i < getTextRowNum(); i ++){
                Row textRow = BoxGadget.getRowForce(sheet, i + currentRowIndex[0]);
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
        //错误提示字体
        Font errorFont = sheet.getWorkbook().createFont();
        errorFont.setColor(Font.COLOR_RED);

        int tableWidth = columnsContainer.size();
        List<T> dataList = new ArrayList<>();
        //TODO 应该是数据起始行开始，到单元格的最后一个有效行
        for (int i = startTextRowIndex ; i < sheet.getLastRowNum() ; i ++){
            T t;
            try {
                t = (T) tabulationClass.newInstance();
            } catch (Exception e) {
                throw new IllegalSourceClassOfTabulationException("An explicit, parameterless constructor is required in Tabulation");
            }

            Row row = sheet.getRow(i);
            if (row != null){
                columnsContainer.forEach(e -> {
                    Cell cell = row.getCell(e.getColumnIndex());
                    //TODO 1. 进行取值(根据类型，根据字典) 2. 对所取值进行校验  3. 赋值
                    Object o = null;
                    try {
                        o = CellValueUtil.getSpecifiedTypeCellValue(cell, e.getFieldType());
                    } catch (IllegalTypeOfCellValueException ex) {
                        //TODO ,拼装error对象
                        ex.printStackTrace();
                    }

                    //数据校验再看下.
                    if (e.getRegEx() != null) {
                        boolean b = Pattern.matches(e.getRegEx(), o.toString());
                    }

                    //校验失败时 cell 字体标红
//                    cell.getCellStyle().setFont(errorFont);
                    //校验成功时， 设置正常风格
//                    cell.setCellStyle(tabulationStyle.getTextStyle());

                    //赋值
                    try {
                        ReflectUtil.setFieldValue(e.getField(), t, o);
                    } catch (Exception ex) {
                        //TODO 异常消息
                        throw new IllegalArgumentException(ex.getMessage(), ex.getCause());
                    }
                });
                dataList.add(t);
            }
        }
        return dataList;
    }


    public static void dataTabulationSourceClassValidate(Class tabulationClass) throws IllegalSourceClassOfTabulationException {
        if (tabulationClass.getAnnotation(ExcelTabulation.class) == null){
            throw new IllegalSourceClassOfTabulationException("Data tabulation POJO need to annotated @ExcelTabulation annotation!");
        }
    }

}
