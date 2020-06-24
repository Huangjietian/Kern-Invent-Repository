package cn.kerninventor.tools.poibox.data.tabulation.validation.array;

import cn.kerninventor.tools.poibox.data.tabulation.validation.AbstractDvBuilder;
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
    protected String getPromptBoxMessage() {
        return getAnnotation().promptMessage();
    }

    @Override
    protected String getErrorBoxMessage() {
        return getAnnotation().errorMessage();
    }

    @Override
    protected DataValidationConstraint createDvConstraint(DataValidationHelper dvHelper) {
        ExplicitListDataValid explicitListDataValid = getAnnotation();
        DataValidationConstraint dvConstraint = dvHelper.createExplicitListConstraint(explicitListDataValid.list());
        return dvConstraint;
    }

}
