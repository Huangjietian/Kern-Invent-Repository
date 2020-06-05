package cn.kerninventor.tools.poibox.data.tabulation.validation.array;

import cn.kerninventor.tools.poibox.data.tabulation.context.ColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.context.TableContext;
import cn.kerninventor.tools.poibox.data.tabulation.validation.DataValidationBuilder;
import cn.kerninventor.tools.poibox.data.tabulation.validation.MessageBoxSetter;
import cn.kerninventor.tools.poibox.utils.BeanUtil;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;

/**
 * @author Kern
 * @date 2020/4/15 12:25
 */
public class FormulaListDataValidationBuilder implements DataValidationBuilder<FormulaListDataValid> {

    private FormulaListDataValid formulaListDataValid;

    public FormulaListDataValidationBuilder(FormulaListDataValid formulaListDataValid) {
        this.formulaListDataValid = formulaListDataValid;
    }

    @Override
    public void addValidation(TableContext tabulationInit, ColumnDefinition columnInit, Sheet sheet) {
        if (BeanUtil.isEmpty(formulaListDataValid.value())) {
            return;
        }
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint(FormulaListDataValid.NAME_PRIFIIX + formulaListDataValid.value());
        CellRangeAddressList dvRange = new CellRangeAddressList(
                tabulationInit.getTbodyFirstRowIndex(),
                (tabulationInit.getTbodyFirstRowIndex() + tabulationInit.getEffectiveRows() - 1),
                columnInit.getColumnIndex(),
                columnInit.getColumnIndex()
        );
        DataValidation dataValidation = dvHelper.createValidation(dvConstraint, dvRange);
        MessageBoxSetter.setPrompBoxMessage(dataValidation, formulaListDataValid.prompMessage());
        MessageBoxSetter.setErrorBoxMessage(dataValidation, formulaListDataValid.errorMessage());
        sheet.addValidationData(dataValidation);
    }
}