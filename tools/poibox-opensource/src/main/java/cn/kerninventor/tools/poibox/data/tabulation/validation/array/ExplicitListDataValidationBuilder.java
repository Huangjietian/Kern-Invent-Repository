package cn.kerninventor.tools.poibox.data.tabulation.validation.array;

import cn.kerninventor.tools.poibox.data.tabulation.validation.AbstractDvBuilder;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;

/**
 * @author Kern
 * @date 2020/5/6 9:37
 * @description
 */
public class ExplicitListDataValidationBuilder extends AbstractDvBuilder<ExplicitListDataValid> {

    public ExplicitListDataValidationBuilder(ExplicitListDataValid explicitListDataValid) {
        super(explicitListDataValid);
    }

    @Override
    protected DataValidationConstraint createDvConstraint(DataValidationHelper dvHelper) {
        ExplicitListDataValid explicitListDataValid = getAnnotation();
        DataValidationConstraint dvConstraint = dvHelper.createExplicitListConstraint(explicitListDataValid.list());
        return dvConstraint;
    }

    @Override
    protected void setBoxMessage(DataValidation dataValidation) {
        dataValidation.createPromptBox(getPromptBoxName(), getAnnotation().promptMessage());
        dataValidation.createPromptBox(getErrorBoxName(), getAnnotation().errorMessage());
    }

}
