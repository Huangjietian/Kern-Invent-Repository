package cn.kerninventor.tools.poibox.data.tabulation.validation.array;

import cn.kerninventor.tools.poibox.data.tabulation.validation.AbstractDvBuilder;
import cn.kerninventor.tools.poibox.exception.IllegalColumnConfigureException;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;

import java.util.Arrays;

/**
 * @author Kern
 * @date 2020/5/25 15:53
 * @description
 */
public class EnumExplicitListDataValidationBuilder extends AbstractDvBuilder<EnumExplicitListDataValid> {

    public EnumExplicitListDataValidationBuilder(EnumExplicitListDataValid enumExplicitListDataValid) {
        super(enumExplicitListDataValid);
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
        EnumExplicitListDataValid enumExplicitListDataValid = getAnnotation();
        Class enumClazz = enumExplicitListDataValid.enumClass();
        if (!enumClazz.isEnum()) {
            throw new IllegalColumnConfigureException("EnumExplicitListDataValid enumClass() must specify an enumeration class!");
        }
        EnumExplicitList[] explicitLists = (EnumExplicitList[]) enumClazz.getEnumConstants();
        String[] list = Arrays.stream(explicitLists).map(EnumExplicitList::explicitList).toArray(String[]::new);
        DataValidationConstraint dvConstraint = dvHelper.createExplicitListConstraint(list);
        return dvConstraint;
    }

}
