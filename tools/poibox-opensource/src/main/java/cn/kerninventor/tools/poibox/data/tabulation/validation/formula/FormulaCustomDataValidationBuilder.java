package cn.kerninventor.tools.poibox.data.tabulation.validation.formula;

import cn.kerninventor.tools.poibox.data.tabulation.validation.AbstractDvBuilder;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;

/**
 * @author Kern
 * @date 2020/5/6 10:18
 */
public class FormulaCustomDataValidationBuilder extends AbstractDvBuilder<FormulaCustomDataValid> {


    public FormulaCustomDataValidationBuilder(FormulaCustomDataValid formulaCustomDataValid) {
        super(formulaCustomDataValid);
    }

    @Override
    protected DataValidationConstraint createDvConstraint(DataValidationHelper dvHelper) {
        FormulaCustomDataValid formulaCustomDataValid = getAnnotation();
        DataValidationConstraint dvConstraint = dvHelper.createCustomConstraint(formulaCustomDataValid.formula());
        return dvConstraint;
    }

    @Override
    protected void setBoxMessage(DataValidation dataValidation) {
        dataValidation.createPromptBox(getPromptBoxName(), getAnnotation().promptMessage());
        dataValidation.createPromptBox(getErrorBoxName(), getAnnotation().errorMessage());
    }

}
