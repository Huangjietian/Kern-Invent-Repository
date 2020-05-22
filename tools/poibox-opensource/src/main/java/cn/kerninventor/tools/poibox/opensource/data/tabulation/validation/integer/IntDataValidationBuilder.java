package cn.kerninventor.tools.poibox.opensource.data.tabulation.validation.integer;

import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.ColumnDefinition;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.TableContext;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.validation.DataValidationBuilder;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.validation.MessageBoxSetter;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;

/**
 * @author Kern
 * @date 2019/12/13 14:31
 */
public class IntDataValidationBuilder implements DataValidationBuilder<IntDataValid> {

    private IntDataValid dataValid;

    public IntDataValidationBuilder(IntDataValid dataValid) {
        this.dataValid = dataValid;
    }

    @Override
    public void addValidation(TableContext tabulationInit, ColumnDefinition columnInit, Sheet sheet) {
        annotationValid(columnInit);
        String var1 = dataValid.value() + "";
        String var2 = dataValid.optionalVal() == -1 ? null : dataValid.optionalVal() + "";
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DataValidationConstraint dvConstraint = dvHelper.createIntegerConstraint(
                dataValid.compareType().getCode(),
                var1,
                var2
        );
        CellRangeAddressList dvRange = new CellRangeAddressList(
                tabulationInit.getTbodyFirstRowIndex() ,
                tabulationInit.getTbodyFirstRowIndex() + tabulationInit.getEffectiveRows() - 1,
                columnInit.getColumnIndex(),
                columnInit.getColumnIndex()
        );
        DataValidation dataValidation = dvHelper.createValidation(dvConstraint, dvRange);
        MessageBoxSetter.setPrompBoxMessage(dataValidation, dataValid.prompMessage());
        MessageBoxSetter.setErrorBoxMessage(dataValidation, dataValid.errorMessage());
        sheet.addValidationData(dataValidation);
    }

    private void annotationValid(ColumnDefinition columnInit) {
        if (dataValid.compareType().isOptionalValueValidity()){
            if (dataValid.value() > dataValid.optionalVal()){
                throw new IllegalArgumentException("The optionalVal() must be greater than or equal to value()! Field:" + columnInit.getFieldName());
            }
        }
    }




}
