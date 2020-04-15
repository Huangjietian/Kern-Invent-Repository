package cn.kerninventor.tools.poibox.data.templated.validation.integer;

import cn.kerninventor.tools.poibox.data.templated.initializer.ExcelColumnInitializer;
import cn.kerninventor.tools.poibox.data.templated.initializer.ExcelTabulationInitializer;
import cn.kerninventor.tools.poibox.data.templated.validation.DataValidationBuilder;
import cn.kerninventor.tools.poibox.data.templated.validation.MessageBoxSetter;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;

/**
 * @Title: ExcelIntValidHandler
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.validation.integer
 * @Author Kern
 * @Date 2019/12/13 14:31
 * @Description: TODO
 */
public class IntDataValidationBuilder implements DataValidationBuilder<IntDataValid> {

    private IntDataValid dataValid;

    public IntDataValidationBuilder(IntDataValid dataValid) {
        this.dataValid = dataValid;
    }

    @Override
    public void addValidation(ExcelTabulationInitializer tabulationInit, ExcelColumnInitializer columnInit, Sheet sheet) {
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

    private void annotationValid(ExcelColumnInitializer columnInit) {
        if (dataValid.compareType().isOptionalValueValidity()){
            if (dataValid.value() > dataValid.optionalVal()){
                throw new IllegalArgumentException("The optionalVal() must be greater than or equal to value()! Field:" + columnInit.getFieldName());
            }
        }
    }




}
