package cn.kerninventor.tools.poibox.opensource.data.templated.validation.array;

import cn.kerninventor.tools.poibox.opensource.data.templated.initializer.EColumnInitiator;
import cn.kerninventor.tools.poibox.opensource.data.templated.initializer.ETabulationInitiator;
import cn.kerninventor.tools.poibox.opensource.data.templated.validation.DataValidationBuilder;
import cn.kerninventor.tools.poibox.opensource.data.templated.validation.MessageBoxSetter;
import cn.kerninventor.tools.poibox.opensource.utils.BeanUtil;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;

/**
 * @author Kern
 * @date 2020/5/6 9:37
 * @description
 */
public class ExplicitListDataValidationBuilder implements DataValidationBuilder<ExplicitListDataValid> {

    private ExplicitListDataValid explicitListDataValid;

    public ExplicitListDataValidationBuilder(ExplicitListDataValid explicitListDataValid) {
        this.explicitListDataValid = explicitListDataValid;
    }

    @Override
    public void addValidation(ETabulationInitiator tabulationInit, EColumnInitiator columnInit, Sheet sheet) {
        if (!BeanUtil.hasElement(explicitListDataValid.list())) {
            return;
        }
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DataValidationConstraint dvConstraint = dvHelper.createExplicitListConstraint(explicitListDataValid.list());
        CellRangeAddressList dvRange = new CellRangeAddressList(
                tabulationInit.getTbodyFirstRowIndex(),
                (tabulationInit.getTbodyFirstRowIndex() + tabulationInit.getEffectiveRows() - 1),
                columnInit.getColumnIndex(),
                columnInit.getColumnIndex()
        );
        DataValidation dataValidation = dvHelper.createValidation(dvConstraint, dvRange);
        MessageBoxSetter.setPrompBoxMessage(dataValidation, explicitListDataValid.prompMessage());
        MessageBoxSetter.setErrorBoxMessage(dataValidation, explicitListDataValid.errorMessage());
        sheet.addValidationData(dataValidation);
    }
}
