package cn.kerninventor.tools.poibox.data.datatable.datavalidation.integer;

import cn.kerninventor.tools.poibox.data.datatable.ExcelcolumnDataAccepter;
import cn.kerninventor.tools.poibox.data.datatable.ExcelTabulationDataProcessor;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.DataValidHandler;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.MessageBoxUtil;
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
    public void addValidation(ExcelTabulationDataProcessor processor, ExcelcolumnDataAccepter accepter, Sheet sheet, ExcelValid_INT excelValid) {
        annotationValid(accepter, excelValid);
        String var1 = excelValid.value() + "";
        String var2 = excelValid.optionalVal() == -1 ? null : excelValid.optionalVal() + "";
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DataValidationConstraint dvConstraint = dvHelper.createIntegerConstraint(
                excelValid.compareType().getCode(),
                var1,
                var2
        );
        CellRangeAddressList dvRange = new CellRangeAddressList(
                processor.getTableTextRdx() ,
                processor.getTableTextRdx() + processor.getTextRowNum(),
                accepter.getColumnIndex(),
                accepter.getColumnIndex()
        );
        DataValidation dataValidation = dvHelper.createValidation(dvConstraint, dvRange);
        MessageBoxUtil.setPrompBoxMessage(dataValidation, excelValid.prompMessage());
        MessageBoxUtil.setErrorBoxMessage(dataValidation, excelValid.errorMessage());
        sheet.addValidationData(dataValidation);
    }

    private void annotationValid(ExcelcolumnDataAccepter accepter, ExcelValid_INT excelValid) {
        if (excelValid.compareType().isOptionalValueValidity()){
            if (excelValid.value() > excelValid.optionalVal()){
                throw new IllegalArgumentException("The optionalVal() must be greater than or equal to value()! Field:" + accepter.getFieldName());
            }
        }
    }




}
