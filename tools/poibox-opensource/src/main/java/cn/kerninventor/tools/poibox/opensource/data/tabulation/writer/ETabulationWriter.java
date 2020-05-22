package cn.kerninventor.tools.poibox.opensource.data.tabulation.writer;

import cn.kerninventor.tools.poibox.opensource.BoxGadget;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.ExcelColumn;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.ExcelTabulation;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.element.Textbox;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.BannerDefinition;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.ColumnDefinition;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.TableContext;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.ReInitializer;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.TabContextModifier;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.validation.array.FormulaListDataValid;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.writer.tbody.TbodyWriter;
import cn.kerninventor.tools.poibox.opensource.layout.MergedRange;
import cn.kerninventor.tools.poibox.opensource.style.Styler;
import cn.kerninventor.tools.poibox.opensource.utils.BeanUtil;
import cn.kerninventor.tools.poibox.opensource.utils.FormulaListUtil;
import cn.kerninventor.tools.poibox.opensource.utils.ReflectUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.*;


/**
 * @author Kern
 * @date 2020/3/12 18:53
 */
public final class ETabulationWriter<T> implements TabulationWriter<T> {

    private TableContext<T> tableContext;

    private Map<String, Set<String>> formulaListMap;

    public ETabulationWriter(TableContext<T> context) {
        this.tableContext = Objects.requireNonNull(context);
    }

    private TableContext getTableContext() {
        return this.tableContext;
    }

    @Override
    public TabulationWriter<T> writeTo(String sheetName) {
        Sheet sheet = tableContext.getParent().getSheet(sheetName);
        return writeTo(sheet);
    }

    @Override
    public TabulationWriter<T> writeTo(Sheet sheet) {
        TbodyWriter tbodyWriter = getTemplateTbodyWriter();
        execute(sheet, null, null, tbodyWriter);
        return this;
    }

    @Override
    public TabulationWriter<T> writeTo(String sheetName, List<T> datas, String... ignore) {
        Sheet sheet = tableContext.getParent().getSheet(sheetName);
        return writeTo(sheet, datas, ignore);
    }

    @Override
    public TabulationWriter<T> writeTo(Sheet sheet, List<T> datas, String... ignore) {
        TbodyWriter tbodyWriter = getDataTbodyWriter();
        execute(sheet, datas, ignore, tbodyWriter);
        return this;
    }

    private void execute(Sheet sheet, List datas, final String[] igonre, final TbodyWriter tbodyWriter) {
        //TODO 先决条件 影响很多
        /**
         * TODO 写如 preWrite 方法里。 写入 filterIgnore 方法里
         */
        TableContext tabulation = getTableContext();
        tabulation.init();
        List<ColumnDefinition> columnsContainer = tabulation.getColumnsContainer();

        //2. 过滤ignore 列
        if (BeanUtil.hasElement(igonre)) {
            columnsContainer = ((ReInitializer<List<ColumnDefinition>>) colums -> {
                List<ColumnDefinition> result = new ArrayList<>();
                colums.forEach(column -> {
                    if (Arrays.stream(igonre).noneMatch(e -> column.getTitleName().equals(e.trim()))) {
                        result.add(column);
                    }
                });
                return result;
            }).reInit(columnsContainer);
            tabulation.setColumnsIndex(columnsContainer);
        }

        //1. 设置名称管理器 TODO 独立的
        /**
         *   1.formulaListWriter.writeTo(sheet);
         *   2.WriteChain chain = new FormulaListWriter(sheet);
         *   chainList.add(chain);
         */
        formulaLists2Sheet(sheet);


        /**
         * TableHeadWriter theadWriter = getBasicWriter(tabulation);
         * theadWriter.setTbodyWriter(tbodyWriter);
         * chainList.add(theadWriter);
         */
        //3. 绘制表头
        Row headRow = sheet.createRow(tabulation.getTheadRowIndex());
        setRowHeight(headRow, tabulation.getTheadRowHeight());
        Workbook workbook = tabulation.getParent().workbook();
        DataFormat dataFormat = workbook.createDataFormat();
        Styler styler = tabulation.getParent().styler();
        for (Iterator<ColumnDefinition> iterator = columnsContainer.iterator(); iterator.hasNext() ; ) {
            ColumnDefinition column = iterator.next();
            Cell headRowCell = headRow.createCell(column.getColumnIndex());
            headRowCell.setCellValue(column.getTitleName());
            headRowCell.setCellStyle(column.getTheadStyle());
            Short theadFontHeightInPoints = BoxGadget.getFontFrom(column.getTheadStyle(), workbook).getFontHeightInPoints();
            setColumnWidth(tabulation, column, sheet, 0, theadFontHeightInPoints);
            CellStyle tbodyStyle = column.getTbodyStyle();
            if (BeanUtil.isNotEmpty(column.getDataFormatEx())){
                tbodyStyle = styler.copyStyle(tbodyStyle);
                tbodyStyle.setDataFormat(dataFormat.getFormat(column.getDataFormatEx()));
            }
            //4. 绘制表体
            tbodyWriter.templateTbody(tabulation, column, tbodyStyle, sheet, datas);
        }
        //5. 写横幅 TODO 依赖于tabulation的初始化
        /**
         *
         */
        tempalateBanners(tabulation, columnsContainer, sheet);


        //6. 添加文本框 TODO 依赖于tabulation的初始化
        Textbox[] textboxes = tabulation.getTextboxes();
        for (Textbox textbox : textboxes) {
            tabulation.getParent().layouter().addTextBox(sheet, textbox);
        }
    }

    private TbodyWriter getTemplateTbodyWriter(){
        return (table, col, tbSt, sh, data) -> {
            for (int rowIndex = table.getTbodyFirstRowIndex() ; rowIndex < table.getEffectiveRows() + table.getTbodyFirstRowIndex(); rowIndex ++){
                Row bodyRow = BoxGadget.getRowForce(sh, rowIndex);
                setRowHeight(bodyRow, table.getTbodyRowHeight());
                Cell bodyCell = bodyRow.createCell(col.getColumnIndex());
                //设置风格
                if (tbSt != null) {
                    bodyCell.setCellStyle(tbSt);
                }
                //设置函数
                if (BeanUtil.isNotEmpty(col.getFormula())) {
                    bodyCell.setCellFormula(col.getFormula());
                }
            }
            //设置数据有效性校验
            if (col.getDataValidationBuilder() != null) {
                col.getDataValidationBuilder().addValidation(table, col, sh);
            }
        };
    }

    private TbodyWriter getDataTbodyWriter(){
        return (table, col, tbSt, sh, data) -> {
            col.getColWriter().pre();
            for (int datasIndex = 0, rowIndex = table.getTbodyFirstRowIndex(); datasIndex < data.size() ; datasIndex ++ , rowIndex++){
                Row bodyRow = BoxGadget.getRowForce(sh, rowIndex);
                setRowHeight(bodyRow, table.getTbodyRowHeight());
                Cell bodyCell = bodyRow.createCell(col.getColumnIndex());
                //设置风格
                if (tbSt != null) {
                    bodyCell.setCellStyle(tbSt);
                }
                Object value = null;
                try {
                    value = ReflectUtil.getFieldValue(col.getField(), data.get(datasIndex));
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException("Field value get error., field name: " + col.getFieldName());
                }
                col.getColWriter().setCellValue(bodyCell, value);
                col.getColWriter().interrupt();
            }
            col.getColWriter().flush();
        };
    }

    private void formulaLists2Sheet(Sheet sheet) {
        //设值名称管理器
        if (formulaListMap != null && !formulaListMap.isEmpty()){
            formulaListMap.keySet().forEach(e -> {
                FormulaListUtil.addFormulaList(sheet, FormulaListDataValid.NAME_PRIFIIX + e, formulaListMap.get(e));
            });
            formulaListMap.clear();
        }
    }

    private void setColumnWidth(TableContext tabulation, ColumnDefinition column, Sheet sheet, int width, int var) {
        if (column.getColumnWidth() == ExcelColumn.DefaultColumnWidth){
            width = BoxGadget.getCellWidthByContent(column.getTitleName(), var);
            width = width < tabulation.getMinimumColumnsWidth() ? tabulation.getMinimumColumnsWidth() : width;
            width = width > tabulation.getMaxmunColumnsWidth() ? tabulation.getMaxmunColumnsWidth() : width;
        } else {
            width = BoxGadget.adjustCellWidth(column.getColumnWidth());
        }
        sheet.setColumnWidth(column.getColumnIndex(), width);
    }

    private void setRowHeight(Row row, float height) {
        if (ExcelTabulation.DefaultRowHeight != height) {
            row.setHeightInPoints(height);
        }
    }

    private void tempalateBanners(TableContext tabulation, List<ColumnDefinition> columns, Sheet sheet) {
        List<BannerDefinition> bannerContainer = tabulation.getBannerContainer();
        bannerContainer.forEach(e -> {
            MergedRange mergedRange = tabulation.getParent().layouter().mergedRegion(
                    sheet,
                    e.adjustCellRangeAddress(tabulation, columns)
            );
            mergedRange.setRowHeight(e.getRowHeight()).setMergeRangeContent(e.getValue()).setMergeRangeStyle(e.getCellStyle());
        });
    }

    @Override
    public TabulationWriter<T> addFormulaList(String name, Set<String> formulaList) {
        if (this.formulaListMap == null) {
            this.formulaListMap = new HashMap<>();
        }
        this.formulaListMap.put(name, formulaList);
        return this;
    }

    @Override
    public TabulationWriter<T> addAllFormulaList(Map<String, Set<String>> formulaListMap) {
        if (this.formulaListMap == null) {
            this.formulaListMap = new HashMap<>();
        }
        this.formulaListMap.putAll(formulaListMap);
        return this;
    }

    @Override
    public TabContextModifier getConfiguration() {
        return tableContext;
    }

    @Override
    public TabulationWriter addBanner(String value, CellStyle style, CellRangeAddress range) {
        this.tableContext.addBanner(value, style, range);
        return this;
    }

    @Override
    public TabulationWriter addBanner(String value, CellStyle style, int row1, int row2) {
        this.tableContext.addBanner(value, style, row1, row2);
        return null;
    }

    @Override
    public TabulationWriter addBanner(String value, CellStyle style, int row1, int row2, int col1, int col2) {
        this.tableContext.addBanner(value, style, row1, row2, col1, col2);
        return this;
    }
}
