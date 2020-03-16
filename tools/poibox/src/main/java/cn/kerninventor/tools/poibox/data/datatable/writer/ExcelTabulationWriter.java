package cn.kerninventor.tools.poibox.data.datatable.writer;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.datatable.initializer.ExcelColumnInitializer;
import cn.kerninventor.tools.poibox.data.datatable.initializer.ExcelTabulationInitializer;
import cn.kerninventor.tools.poibox.data.datatable.templator.Templator;
import cn.kerninventor.tools.poibox.data.utils.CellValueUtil;
import cn.kerninventor.tools.poibox.data.utils.InstanceGetter;
import cn.kerninventor.tools.poibox.utils.ReflectUtil;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

/**
 * @Title ExcelTabulationWriter
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.writer
 * @Author Kern
 * @Date 2020/3/12 19:13
 * @Description TODO
 */
public class ExcelTabulationWriter<T> {
    private boolean templated;
    private ExcelTabulationInitializer initializer;

    /**
     * TODO 考虑在ExcelTabulationInitializer 初始化ColumnStyle的可行性。
     * TODO 先实现所有功能和结构调整，然后再简化代码
     * @param sheet
     * @param datas
     * @param templator
     */
    public void writeTo(Sheet sheet, List<T> datas, Templator<T> templator){
        int start = 0 ;
        if (templator != null){
            templated = true;
            initializer = ((InstanceGetter<ExcelTabulationInitializer>)templator).getInstance();
            start = initializer.getTableTextRdx();
            initializer.setTextRowNum(datas.size());
            templator.tabulateTo(sheet, false);
        }
        if (datas == null || datas.isEmpty()) {
            return;
        }
        if (initializer == null) {
            initializer = new ExcelTabulationInitializer(datas.get(0).getClass());
        }

        List<ExcelColumnInitializer> columnInitializers = initializer.getColumnsContainer();
        DataFormat dataFormat = sheet.getWorkbook().createDataFormat();
        //列
        for (ExcelColumnInitializer column : columnInitializers) {
            CellStyle columnStyle = null;
            if (!templated && column.getDataFormatEx() != null){
                columnStyle = sheet.getWorkbook().createCellStyle();
                columnStyle.setDataFormat(dataFormat.getFormat(column.getDataFormatEx()));
            }
            //行
            for (int i = 0; i < datas.size() ; i ++ ){
                Row row = BoxGadget.getRowForce(sheet , start++);
                Cell cell = BoxGadget.getCellForce(row, column.getColumnIndex());
                Object value = null;
                try {
                    value = ReflectUtil.getFieldValue(column.getField(), datas.get(i));
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException("Field value get error., field name: " + column.getFieldName());
                }
                if (column.getInterpretor().isInterpretable()) {
                    value = column.getInterpretor().interpreteOf(value);
                }
                //根据内容合并

                CellValueUtil.setCellValue(cell, value);
                cell.setCellStyle(columnStyle);
            }
            start -= datas.size();
        }
    }
}
