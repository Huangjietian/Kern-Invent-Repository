package cn.kerninventor.tools.poibox.data.templated.writer.tbody;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.templated.validation.DataValidationBuilderFactory;
import cn.kerninventor.tools.poibox.data.templated.initializer.ExcelColumnInitializer;
import cn.kerninventor.tools.poibox.data.templated.initializer.ExcelTabulationInitializer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @author Kern
 * @date 2020/4/13 16:40
 * @description
 */
public class TbodyEmptyWriter implements TbodyWriter {

    @Override
    public void templateTbody(ExcelTabulationInitializer tabulation, ExcelColumnInitializer column, Sheet sheet, List data) {
        if (column.getDataFormatEx() != null){
            DataFormat dataFormat = tabulation.getParent().workbook().createDataFormat();
            String expression = column.getDataFormatEx();
            short dataformatIndex = dataFormat.getFormat(expression);
            column.getColumnStyle().setDataFormat(dataformatIndex);
        }
        //画每一行
        for (int i = 0 ; i < tabulation.getEffectiveRows(); i ++){
            Row textRow = BoxGadget.getRowForce(sheet, i + tabulation.getTbodyFirstRowIndex());
            Cell textCell = textRow.createCell(column.getColumnIndex());
            textCell.setCellStyle(column.getColumnStyle());
        }
        //数据有效性
        if (column.getValidAnnotation() != null) {
            DataValidationBuilderFactory.getInstance(column.getValidAnnotation())
                    .addValidation(tabulation, column, sheet);
        }
    }
}
