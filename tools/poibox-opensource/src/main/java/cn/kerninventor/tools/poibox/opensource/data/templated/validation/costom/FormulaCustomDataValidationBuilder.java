package cn.kerninventor.tools.poibox.opensource.data.templated.validation.costom;

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
 * @date 2020/5/6 10:18
 * @description
 */
public class FormulaCustomDataValidationBuilder implements DataValidationBuilder<FormulaCustomDataValid> {

    private FormulaCustomDataValid formulaCustomDataValid;

    public FormulaCustomDataValidationBuilder(FormulaCustomDataValid formulaCustomDataValid) {
        this.formulaCustomDataValid = formulaCustomDataValid;
    }

    @Override
    public void addValidation(ETabulationInitiator tabulationInit, EColumnInitiator columnInit, Sheet sheet) {
        if (BeanUtil.isEmpty(formulaCustomDataValid.formula())) {
            return;
        }
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DataValidationConstraint dvConstraint = dvHelper.createCustomConstraint(formulaCustomDataValid.formula());
        CellRangeAddressList dvRange = new CellRangeAddressList(
                tabulationInit.getTbodyFirstRowIndex(),
                (tabulationInit.getTbodyFirstRowIndex() + tabulationInit.getEffectiveRows() - 1),
                columnInit.getColumnIndex(),
                columnInit.getColumnIndex()
        );
        DataValidation dataValidation = dvHelper.createValidation(dvConstraint, dvRange);
        MessageBoxSetter.setPrompBoxMessage(dataValidation, formulaCustomDataValid.prompMessage());
        MessageBoxSetter.setErrorBoxMessage(dataValidation, formulaCustomDataValid.errorMessage());
        sheet.addValidationData(dataValidation);
    }
}
