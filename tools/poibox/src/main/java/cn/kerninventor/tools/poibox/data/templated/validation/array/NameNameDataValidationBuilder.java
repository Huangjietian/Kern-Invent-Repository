package cn.kerninventor.tools.poibox.data.templated.validation.array;

import cn.kerninventor.tools.poibox.data.templated.initializer.EColumnInitiator;
import cn.kerninventor.tools.poibox.data.templated.initializer.ETabulationInitiator;
import cn.kerninventor.tools.poibox.data.templated.validation.DataValidationBuilder;
import cn.kerninventor.tools.poibox.data.templated.validation.MessageBoxSetter;
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
public class NameNameDataValidationBuilder implements DataValidationBuilder<NameNameDataValid> {

    private NameNameDataValid excelValid;

    public NameNameDataValidationBuilder(cn.kerninventor.tools.poibox.data.templated.validation.array.NameNameDataValid nameNameDataValid) {
        this.excelValid = nameNameDataValid;
    }

    @Override
    public void addValidation(ETabulationInitiator tabulationInit, EColumnInitiator columnInit, Sheet sheet) {
        if (BeanUtil.isEmpty(excelValid.value())) {
            //TODO 此处应打印日志 warn
            return;
        }
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint(NameNameDataValid.NAME_PRIFIIX + excelValid.value());
        CellRangeAddressList dvRange = new CellRangeAddressList(
                tabulationInit.getTbodyFirstRowIndex(),
                (tabulationInit.getTbodyFirstRowIndex() + tabulationInit.getEffectiveRows() - 1),
                columnInit.getColumnIndex(),
                columnInit.getColumnIndex()
        );
        DataValidation dataValidation = dvHelper.createValidation(dvConstraint, dvRange);
        MessageBoxSetter.setPrompBoxMessage(dataValidation, excelValid.prompMessage());
        MessageBoxSetter.setErrorBoxMessage(dataValidation, excelValid.errorMessage());
        sheet.addValidationData(dataValidation);
    }
}
