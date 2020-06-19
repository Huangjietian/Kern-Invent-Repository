package cn.kerninventor.tools.poibox.data.tabulation.validation.textlength;

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
public class TextLengthDataValidationBuilder extends AbstractDvBuilder<TextLengthDataValid> {

    public TextLengthDataValidationBuilder(TextLengthDataValid textLengthDataValid) {
        super(textLengthDataValid);
    }

    @Override
    protected DataValidationConstraint createDvConstraint(DataValidationHelper dvHelper) {
        TextLengthDataValid textLengthDataValid = getAnnotation();
        String var1 = textLengthDataValid.value() + "";
        String var2 = textLengthDataValid.optionalVal() == -1 ? null : textLengthDataValid.optionalVal() + "";
        DataValidationConstraint dvConstraint = dvHelper.createTextLengthConstraint(
                textLengthDataValid.compareType().getCode(),
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

    private void annotationValid(ColumnDefinition columnDefinition, TextLengthDataValid textLengthDataValid) {
        if (textLengthDataValid.compareType().isOptionalValueValidity()){
            if (textLengthDataValid.value() > textLengthDataValid.optionalVal()){
                throw new IllegalArgumentException("The optionalVal() must be greater than or equal to value()! Field:" + columnDefinition.getFieldName());
            }
        }
    }


}
