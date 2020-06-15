package cn.kerninventor.tools.poibox.data.tabulation.validation.textlength;

import cn.kerninventor.tools.poibox.data.tabulation.context.ClassFileColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.context.ClassFileTableContext;
import cn.kerninventor.tools.poibox.data.tabulation.validation.DataValidationBuilder;
import cn.kerninventor.tools.poibox.data.tabulation.validation.MessageBoxSetter;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;

/**
 * @author Kern
 * @date 2019/12/13 14:31
 */
public class TextLengthDataValidationBuilder implements DataValidationBuilder<TextLengthDataValid> {

    private TextLengthDataValid dataValid;

    public TextLengthDataValidationBuilder(TextLengthDataValid dataValid) {
        this.dataValid = dataValid;
    }

    @Override
    public void addValidation(ClassFileTableContext tabulationInit, ClassFileColumnDefinition columnInit, Sheet sheet) {
        annotationValid(columnInit);
        String var1 = dataValid.value() + "";
        String var2 = dataValid.optionalVal() == -1 ? null : dataValid.optionalVal() + "";
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DataValidationConstraint dvConstraint = dvHelper.createTextLengthConstraint(
                dataValid.compareType().getCode(),
                var1,
                var2
        );
        CellRangeAddressList dvRange = new CellRangeAddressList(
                tabulationInit.getTbodyFirstRowIndex(),
                tabulationInit.getTbodyFirstRowIndex()+ tabulationInit.getEffectiveRows() - 1,
                columnInit.getColumnIndex(),
                columnInit.getColumnIndex()
        );
        DataValidation dataValidation = dvHelper.createValidation(dvConstraint, dvRange);
        MessageBoxSetter.setPrompBoxMessage(dataValidation, dataValid.prompMessage());
        MessageBoxSetter.setErrorBoxMessage(dataValidation, dataValid.errorMessage());
        sheet.addValidationData(dataValidation);
    }

    private void annotationValid(ClassFileColumnDefinition columnInit) {
        if (dataValid.compareType().isOptionalValueValidity()){
            if (dataValid.value() > dataValid.optionalVal()){
                throw new IllegalArgumentException("The optionalVal() must be greater than or equal to value()! Field:" + columnInit.getFieldName());
            }
        }
    }


}
