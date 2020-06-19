package cn.kerninventor.tools.poibox.data.tabulation.validation.decimal;

import cn.kerninventor.tools.poibox.data.tabulation.context.ColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.context.TabulationBeanConfiguration;
import cn.kerninventor.tools.poibox.data.tabulation.validation.AbstractDvBuilder;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Kern
 * @date 2019/12/13 14:31
 */
public class DecimalDataValidationBuilder extends AbstractDvBuilder<DecimalDataValid> {

    public DecimalDataValidationBuilder(DecimalDataValid decimalDataValid) {
        super(decimalDataValid);
    }

    @Override
    protected DataValidationConstraint createDvConstraint(DataValidationHelper dvHelper) {
        DecimalDataValid decimalDataValid = getAnnotation();
        String var1 = decimalDataValid.value() + "";
        String var2 = decimalDataValid.optionalVal() == -1.00 ? null : decimalDataValid.optionalVal() + "";
        DataValidationConstraint dvConstraint = dvHelper.createDecimalConstraint(
                decimalDataValid.compareType().getCode(),
                var1,
                var2
        );
        return dvConstraint;
    }

    @Override
    protected void setBoxMessage(DataValidation dataValidation) {
        dataValidation.createPromptBox(getPromptBoxName(), getAnnotation().promptMessage());
        dataValidation.createPromptBox(getErrorBoxName(), getAnnotation().errorMessage());
    }

    @Override
    public void setDataValidation(TabulationBeanConfiguration tableContext, ColumnDefinition columnDefinition, Sheet sheet) {
        annotationValid(columnDefinition, getAnnotation());
        super.setDataValidation(tableContext, columnDefinition, sheet);
    }

    private void annotationValid(ColumnDefinition columnDefinition, DecimalDataValid decimalDataValid) {
        if (decimalDataValid.compareType().isOptionalValueValidity()){
            if (decimalDataValid.value() > decimalDataValid.optionalVal()){
                throw new IllegalArgumentException("The optionalVal() must be greater than or equal to value()! Field:" + columnDefinition.getFieldName());
            }
        }
    }


}
