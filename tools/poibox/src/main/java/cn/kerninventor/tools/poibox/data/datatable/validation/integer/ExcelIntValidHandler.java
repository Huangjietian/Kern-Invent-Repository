package cn.kerninventor.tools.poibox.data.datatable.validation.integer;

import cn.kerninventor.tools.poibox.data.datatable.DataColumn;
import cn.kerninventor.tools.poibox.data.datatable.DataTabulation;
import cn.kerninventor.tools.poibox.data.datatable.validation.DataValidHandler;
import cn.kerninventor.tools.poibox.data.datatable.validation.MessageBoxUtil;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;

/**
 * @Title: ExcelIntValidHandler
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.validation.integer
 * @Author Kern
 * @Date 2019/12/13 14:31
 * @Description: TODO
 */
public class ExcelIntValidHandler implements DataValidHandler<ExcelValid_INT> {

    @Override
    public void addValidation(DataTabulation dataTabulation, DataColumn dataColumn, Sheet sheet, ExcelValid_INT excelValid) {
        annotationValid(dataColumn, excelValid);
        String var1 = excelValid.value() + "";
        String var2 = excelValid.optionalVal() == -1 ? null : excelValid.optionalVal() + "";
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DataValidationConstraint dvConstraint = dvHelper.createIntegerConstraint(
                excelValid.compareType().getCode(),
                var1,
                var2
        );
        CellRangeAddressList dvRange = new CellRangeAddressList(
                dataTabulation.getStartRowIndex(),
                dataTabulation.getStartTextRowIndex() + dataTabulation.getTextRowNum(),
                dataColumn.getColumnIndex(),
                dataColumn.getColumnIndex()
        );
        DataValidation dataValidation = dvHelper.createValidation(dvConstraint, dvRange);
        MessageBoxUtil.setPrompBoxMessage(dataValidation, excelValid.prompMessage());
        MessageBoxUtil.setErrorBoxMessage(dataValidation, excelValid.errorMessage());
        sheet.addValidationData(dataValidation);
    }

    private void annotationValid(DataColumn dataColumn, ExcelValid_INT excelValid) {
        if (excelValid.compareType().isOptionalValueValidity()){
            if (excelValid.value() > excelValid.optionalVal()){
                throw new IllegalArgumentException("The optionalVal() must be greater than or equal to value()! Field:" + dataColumn.getFieldName());
            }
        }
    }


}
