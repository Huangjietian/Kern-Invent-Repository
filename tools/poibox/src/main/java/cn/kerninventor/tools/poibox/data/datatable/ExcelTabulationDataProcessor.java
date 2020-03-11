package cn.kerninventor.tools.poibox.data.datatable;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.DataValidBuilder;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.ExcelValid;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.ExcelValidArray;
import cn.kerninventor.tools.poibox.data.datatable.dictionary.ExcelDictionaryLibrary;
import cn.kerninventor.tools.poibox.data.datatable.dictionary.metaView.MetaViewBody;
import cn.kerninventor.tools.poibox.data.datatable.dictionary.metaView.MetaViewDictionary;
import cn.kerninventor.tools.poibox.data.exception.IllegalSourceClassOfTabulationException;
import cn.kerninventor.tools.poibox.data.utils.CellValueUtil;
import cn.kerninventor.tools.poibox.style.Styler;
import cn.kerninventor.tools.poibox.style.TabulationStyle;
import cn.kerninventor.tools.poibox.utils.ReflectUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

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

    public void tabulateTo(Sheet sheet, POIBox poiBox, boolean valid) {
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
            if (valid && e.getValidAnnotation() != null) {
                DataValidBuilder.getInstance(e.getValidAnnotation())
                        .addValidation(this, e, sheet);
            }
//            else {
//                ExcelQualifiedTypeValidType.getQualifiedTypeValidHandler(e.getFieldType())
//                        .addValidation(this,e,sheet);
//            }

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

    public void writeDatasTo(Sheet sheet, POIBox poiBox, List<T> datas, boolean templateless){
        int start = 0 ;
        if (!templateless){
            tabulateTo(sheet, poiBox, false);
            start = tableTextRdx;
        }
        CellStyle errorStyle = Styler.cloneStyle(sheet.getWorkbook(), tabulationStyle.getTextStyle());
        Font errorFont = sheet.getWorkbook().getFontAt(errorStyle.getFontIndexAsInt());
        errorFont.setColor(Font.COLOR_RED);
        errorStyle.setFont(errorFont);

        for (int i = 0; i < datas.size() ; i ++ ){
            Row row = BoxGadget.getRowForce(sheet , start++);
            T t = datas.get(i);
            columnsContainer.forEach(c -> {
                Cell cell = BoxGadget.getCellForce(row, c.getColumnIndex());
                Object value = null;
                try {
                    value = ReflectUtil.getFieldValue(c.getField(), t);
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException("没有按照标准的方法给出get方法");
                }
                //如果使用校验，将校验源数据的有效性。
                if (c.getValidAnnotation() instanceof ExcelValidArray) {
                    ExcelValidArray excelValidArray = (ExcelValidArray) c.getValidAnnotation();
                    List<MetaViewBody> metaViewBodies = ExcelDictionaryLibrary.referDict(excelValidArray.dictionary());
                    MetaViewBody metaViewBody = null;
                    for (MetaViewBody body : metaViewBodies){
                        if (body.getMetadata().equals(value)){
                            metaViewBody = new MetaViewBody() {
                                @Override
                                public Object getMetadata() {
                                    return body.getMetadata();
                                }
                                @Override
                                public Object getViewdata() {
                                    return body.getViewdata();
                                }
                            };
                            break;
                        }
                    }
                    if (metaViewBody == null) {
                        cell.setCellStyle(errorStyle);
                    } else {
                        value = metaViewBody.getViewdata();
                    }
                }
                CellValueUtil.setCellValue(cell, value);
            });
        }
    }

    public List<T> readFrom(Sheet sheet, POIBox poiBox) {
        List<T> list = new ArrayList();

        for (int i = tableTextRdx; i <= sheet.getLastRowNum() ; i ++) {
            T t = null;
            try {
                t = (T) ReflectUtil.newInstance(tabulationClass);
            } catch (Exception e) {
                throw new IllegalArgumentException("The tabulation Class Missing parameterless constructor!");
            }

            Row row = BoxGadget.getRowForce(sheet, i);
            for (ExcelcolumnDataAccepter accepter : columnsContainer){
                Cell cell = BoxGadget.getCellForce(row, accepter.getColumnIndex());
                //TODO 如果需要翻译的话如何处理
                Object value = CellValueUtil.getCellValue(cell, accepter.getFieldType());

                //TODO null 计数
                try {
                    ReflectUtil.setFieldValue(accepter.getField(), t, value);
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException("Set value to Field error! Field: " + accepter.getFieldName());
                }
            }
            list.add(t);
        }
        return list;
    }


    public static void dataTabulationSourceClassValidate(Class tabulationClass) throws IllegalSourceClassOfTabulationException {
        if (tabulationClass.getAnnotation(ExcelTabulation.class) == null){
            throw new IllegalSourceClassOfTabulationException("Data tabulation POJO need to annotated @ExcelTabulation annotation!");
        }
    }

}
