package cn.kerninventor.tools.poibox.data.tabulation.validation.array;

import cn.kerninventor.tools.poibox.data.tabulation.validation.AbstractDvBuilder;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;

/**
 * @author Kern
 * @date 2020/4/15 12:25
 */
public class FormulaListDataValidationBuilder extends AbstractDvBuilder<FormulaListDataValid> {

    public FormulaListDataValidationBuilder(FormulaListDataValid formulaListDataValid) {
        super(formulaListDataValid);
    }

    @Override
    protected DataValidationConstraint createDvConstraint(DataValidationHelper dvHelper) {
        FormulaListDataValid formulaListDataValid = getAnnotation();
        DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint(FormulaListDataValid.NAME_PRIFIIX + formulaListDataValid.value());
        return dvConstraint;
    }

    @Override
    protected void setBoxMessage(DataValidation dataValidation) {
        dataValidation.createPromptBox(getPromptBoxName(), getAnnotation().promptMessage());
        dataValidation.createPromptBox(getErrorBoxName(), getAnnotation().errorMessage());
    }

}
