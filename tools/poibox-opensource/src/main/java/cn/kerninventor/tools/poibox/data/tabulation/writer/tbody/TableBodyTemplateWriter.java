package cn.kerninventor.tools.poibox.data.tabulation.writer.tbody;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.tabulation.context.ClassFileColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.context.ClassFileTableContext;
import cn.kerninventor.tools.poibox.utils.BeanUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Kern
 * @date 2020/5/25 14:18
 * @description
 */
public final class TableBodyTemplateWriter<T> implements TbodyWriter<T> {

    @Override
    public void templateTbody(ClassFileColumnDefinition<T> classFileColumnDefinition, Sheet sheet) {
        ClassFileTableContext classFileTableContext = classFileColumnDefinition.getClassFileTableContext();
        for (int rowIndex = classFileTableContext.getTbodyFirstRowIndex(); rowIndex < classFileTableContext.getEffectiveRows() + classFileTableContext.getTbodyFirstRowIndex(); rowIndex ++){
            Row bodyRow = BoxGadget.getRowForce(sheet, rowIndex);
            bodyRow.setHeightInPoints(classFileTableContext.getTbodyRowHeight());
            Cell bodyCell = bodyRow.createCell(classFileColumnDefinition.getColumnIndex());
            bodyCell.setCellStyle(classFileColumnDefinition.getTbodyStyle());
            if (BeanUtil.isNotEmpty(classFileColumnDefinition.getFormula())) {
                bodyCell.setCellFormula(classFileColumnDefinition.getFormula());
            }
        }
        if (classFileColumnDefinition.getDataValidationBuilder() != null) {
            classFileColumnDefinition.getDataValidationBuilder().addValidation(classFileTableContext, classFileColumnDefinition, sheet);
        }
    }
}
