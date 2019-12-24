package cn.kerninventor.tools.poibox.data.datatable;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.POIGadget;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.DataValidHandler;
import cn.kerninventor.tools.poibox.data.exception.IllegalSourceClassOfTabulationException;
import cn.kerninventor.tools.poibox.data.exception.IllegalTypeOfCellValueException;
import cn.kerninventor.tools.poibox.data.utils.CellValueUtil;
import cn.kerninventor.tools.poibox.style.POIStyler;
import cn.kerninventor.tools.poibox.style.TabulationStyle;
import cn.kerninventor.tools.poibox.utils.ReflectUtil;
import com.sun.istack.internal.Nullable;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Title: DataTable
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable
 * @Author Kern
 * @Date 2019/12/9 15:25
 * @Description: TODO
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
        /**
         * auto index schema, the first column index is 0
         */
        if (autoColumnIndex){
            int index = 0 ;
            for (Field field : fields){
                if ((excelColumn = field.getDeclaredAnnotation(ExcelColumn.class)) != null
                        && excelColumn.value() != null
                        && !"".equals(excelColumn.value().trim())) {
                    ExcelcolumnDataAccepter excelcolumnDataAccepter = ExcelcolumnDataAccepter.getInstance(
                            field,
                            field.getName(),
                            excelColumn.value(),
                            index++,
                            excelColumn.columnWidth(),
                            excelColumn.regEx(),
                            excelColumn.dateFormat(),
                            DataValidHandler.findAnnotationForm(field)
                    );
                    excelcolumnDataAccepters.add(excelcolumnDataAccepter);
                }
            }
        } else {
            for (Field field : fields){
                if ((excelColumn = field.getDeclaredAnnotation(ExcelColumn.class)) != null
                        && excelColumn.value() != null
                        && !"".equals(excelColumn.value().trim())) {
                    ExcelcolumnDataAccepter excelcolumnDataAccepter = ExcelcolumnDataAccepter.getInstance(
                            field,
                            field.getName(),
                            excelColumn.value(),
                            excelColumn.columnIndex(),
                            excelColumn.columnWidth(),
                            excelColumn.regEx(),
                            excelColumn.dateFormat(),
                            DataValidHandler.findAnnotationForm(field)
                    );
                    excelcolumnDataAccepters.add(excelcolumnDataAccepter);
                }
            }
        }
        if (excelcolumnDataAccepters.size() == 0){
            throw new IllegalSourceClassOfTabulationException("Data table lack column definition, you should use @ExcelColumn to annotate object's fields!");
        }
        Collections.sort(excelcolumnDataAccepters);
        return excelcolumnDataAccepters;
    }

    public void tabulateTo(Sheet sheet, POIBox poiBox, @Nullable List<T> datas) {
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
                    .setMergeRangeStyle(getTabulationStyle()
                            .getHeadLineStyle());
            currentRowIndex[0]++;
        }

        /**
         * draw table
         */
        Row row = sheet.createRow(currentRowIndex[0]);
        CellStyle tableHeadStyle = POIStyler.cloneStyle(poiBox.working(), getTabulationStyle().getTableHeadStyle());
        Font tableHeadFont = sheet.getWorkbook().getFontAt(tableHeadStyle.getFontIndexAsInt());
        CellStyle tableTextStyle = POIStyler.cloneStyle(poiBox.working(), getTabulationStyle().getTextStyle());
        Font tableTextFont = sheet.getWorkbook().getFontAt(tableTextStyle.getFontIndexAsInt());
        getColumnsContainer().forEach( e -> {
            Cell cell = row.createCell(e.getColumnIndex());
            //type
            cell.setCellType(CellType.STRING);
            //value
            cell.setCellValue(e.getTitleName());
            //style
            cell.setCellStyle(tableHeadStyle);
            //column width
            if (e.getColumnWidth() == -1 ){
                int columnWidth = POIGadget.getCellWidthByStringContent(e.getTitleName(), tableHeadFont.getFontHeightInPoints());
                sheet.setColumnWidth(e.getColumnIndex(), columnWidth);
            } else {
                sheet.setColumnWidth(e.getColumnIndex(), e.getColumnWidth());
            }
            //data validation
            if (e.getValidAnnotation() != null) {
                DataValidHandler.getInstance(e.getValidAnnotation()).addValidation(this, e, sheet, e.getValidAnnotation());
            } else {
                DataValidHandler.qualifiedTypeValidHandler(this, e, sheet);
            }

            //text style
            for (int i = 0 ; i < getTextRowNum(); i ++){
                Row textRow = POIGadget.getRowForce(sheet, i + currentRowIndex[0] + 1);
                Cell textCell = textRow.createCell(e.getColumnIndex());
                textCell.setCellType(CellType.STRING);
                textCell.setCellStyle(tableTextStyle);

                if (datas != null && datas.size() > 0){
                    T t = datas.get(i);
                    Object obj = null;
                    try {
                        obj = ReflectUtil.getFieldValue(e.getField(), t);
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                    if (obj == null){
                        continue;
                    } else if (obj instanceof Date){
                        cell.setCellValue(e.getSimpleDateFormat().format(obj));
                    } else if (obj instanceof List){
                        List list = (List)obj;
                        String strList = list.toString();
                        obj = strList.substring(1,strList.length()-1);
                        cell.setCellValue(obj.toString());
                    } else {
                        cell.setCellValue(obj.toString());
                    }

                    int columnWidth = POIGadget.getCellWidthByStringContent(obj.toString(), tableTextFont.getFontHeightInPoints());
                    int maxValue = sheet.getColumnWidth(textCell.getColumnIndex());
                    if (columnWidth > maxValue){
                        sheet.setColumnWidth(e.getColumnIndex(), columnWidth);
                    }
                }
            }
        });

    }

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
