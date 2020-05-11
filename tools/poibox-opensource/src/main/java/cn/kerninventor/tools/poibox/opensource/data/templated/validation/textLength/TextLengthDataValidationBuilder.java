package cn.kerninventor.tools.poibox.opensource.data.templated.validation.textLength;

import cn.kerninventor.tools.poibox.opensource.data.templated.initializer.EColumnInitiator;
import cn.kerninventor.tools.poibox.opensource.data.templated.initializer.ETabulationInitiator;
import cn.kerninventor.tools.poibox.opensource.data.templated.validation.DataValidationBuilder;
import cn.kerninventor.tools.poibox.opensource.data.templated.validation.MessageBoxSetter;
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
    public void addValidation(ETabulationInitiator tabulationInit, EColumnInitiator columnInit, Sheet sheet) {
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

    private void annotationValid(EColumnInitiator columnInit) {
        if (dataValid.compareType().isOptionalValueValidity()){
            if (dataValid.value() > dataValid.optionalVal()){
                throw new IllegalArgumentException("The optionalVal() must be greater than or equal to value()! Field:" + columnInit.getFieldName());
            }
        }
    }


}
