package cn.kerninventor.tools.poibox.data.tabulation.validation.array;

import cn.kerninventor.tools.poibox.data.tabulation.context.ColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.context.TableContext;
import cn.kerninventor.tools.poibox.data.tabulation.validation.DataValidationBuilder;
import cn.kerninventor.tools.poibox.data.tabulation.validation.MessageBoxSetter;
import cn.kerninventor.tools.poibox.exception.IllegalColumnConfigureException;
import cn.kerninventor.tools.poibox.utils.BeanUtil;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.Arrays;

/**
 * @author Kern
 * @date 2020/5/25 15:53
 * @description
 */
public class EnumExplicitListDataValidationBuilder implements DataValidationBuilder<EnumExplicitListDataValid> {

    private EnumExplicitListDataValid enumExplicitListDataValid;

    public EnumExplicitListDataValidationBuilder(EnumExplicitListDataValid enumExplicitListDataValid) {
        this.enumExplicitListDataValid = enumExplicitListDataValid;
    }

    @Override
    public void addValidation(TableContext tabulationInit, ColumnDefinition columnInit, Sheet sheet) {
        if (BeanUtil.isNull(enumExplicitListDataValid)) {
            return;
        }
        Class enumClazz = enumExplicitListDataValid.enumClass();
        if (!enumClazz.isEnum()) {
            throw new IllegalColumnConfigureException("EnumExplicitListDataValid enumClass() must specify an enumeration class!");
        }
        EnumExplicitList[] explicitLists = (EnumExplicitList[]) enumClazz.getEnumConstants();
        String[] list = Arrays.stream(explicitLists).map(EnumExplicitList::explicitList).toArray(String[]::new);
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DataValidationConstraint dvConstraint = dvHelper.createExplicitListConstraint(list);
        CellRangeAddressList dvRange = new CellRangeAddressList(
                tabulationInit.getTbodyFirstRowIndex(),
                (tabulationInit.getTbodyFirstRowIndex() + tabulationInit.getEffectiveRows() - 1),
                columnInit.getColumnIndex(),
                columnInit.getColumnIndex()
        );
        DataValidation dataValidation = dvHelper.createValidation(dvConstraint, dvRange);
        MessageBoxSetter.setPrompBoxMessage(dataValidation, enumExplicitListDataValid.prompMessage());
        MessageBoxSetter.setErrorBoxMessage(dataValidation, enumExplicitListDataValid.errorMessage());
        sheet.addValidationData(dataValidation);
    }
}
