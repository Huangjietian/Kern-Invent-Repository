package cn.kerninventor.tools.poibox.data.datatable.datavalidation.date;

import cn.kerninventor.tools.poibox.data.datatable.ExcelcolumnDataAccepter;
import cn.kerninventor.tools.poibox.data.datatable.ExcelTabulationDataProcessor;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.DataValidHandler;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.MessageBoxUtil;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @Title: ExcelDateValid
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.validation
 * @Author Kern
 * @Date 2019/12/13 11:30
 * @Description: TODO
 */
public class ExcelDateValidHandler implements DataValidHandler<ExcelValid_DATE> {

    public void addValidation(ExcelTabulationDataProcessor excelTabulationDataProcessor, ExcelcolumnDataAccepter excelcolumnDataAccepter, Sheet sheet, ExcelValid_DATE excelValid) {
        annotationValid(excelcolumnDataAccepter, excelValid);
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DVConstraint dvConstraint = DVConstraint.createDateConstraint(
                excelValid.compareType().getCode(),
                dateExpResolve(excelValid.date()),
                dateExpResolve(excelValid.optionalDate()),
                null
        );
        CellRangeAddressList dvRange = new CellRangeAddressList(
                excelTabulationDataProcessor.getStartRowIndex(),
                excelTabulationDataProcessor.getStartTextRowIndex() + excelTabulationDataProcessor.getTextRowNum(),
                excelcolumnDataAccepter.getColumnIndex(),
                excelcolumnDataAccepter.getColumnIndex()
        );

        DataValidation dataValidation = dvHelper.createValidation(dvConstraint, dvRange);
        MessageBoxUtil.setPrompBoxMessage(dataValidation, excelValid.prompMessage());
        MessageBoxUtil.setErrorBoxMessage(dataValidation, excelValid.errorMessage());
        sheet.addValidationData(dataValidation);
    }

    private void annotationValid(ExcelcolumnDataAccepter excelcolumnDataAccepter, ExcelValid_DATE excelValid) {
//        SimpleDateFormat sdf = null;
//        try {
//            sdf = new SimpleDateFormat(excelValid.pattern());
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Date format pattern is invalid! Field: " + dataColumn.getFieldName());
//        }
//        Date date = null;
//        try {
//            date = sdf.parse(excelValid.date());
//        } catch (ParseException e) {
//            throw new IllegalArgumentException("The date() value is invalid! Field: " + dataColumn.getFieldName());
//        }
        String d2 = "".equals(excelValid.optionalDate()) ? null : excelValid.optionalDate();
//        Date optionalDate = null;
//        if (d2 != null){
//            try {
//                optionalDate = sdf.parse(d2);
//            } catch (ParseException e) {
//                throw new IllegalArgumentException("The optionalDate() value is invalid! Field: " + dataColumn.getFieldName());
//            }
//        }
        if (excelValid.compareType().isOptionalValueValidity()){
            Objects.requireNonNull(d2, "The optionalDate() is not be Empty when compareType is bettwen or notBettwen! Field: " + excelcolumnDataAccepter.getFieldName());
//            if (date.compareTo(optionalDate) == 1){
//                throw new IllegalArgumentException("The optionalDate() must be greater than or equal to date() when compareType is bettwen or notBettwen! Field: " + dataColumn.getFieldName());
//            }
        }
    }

    private static String dateExpResolve(String dateExp) {
        if (dateExp == null){
            return null;
        }
        if ("now()".equals(dateExp)){
            return new SimpleDateFormat("YYYY-MM-dd").format(new Date());
        }
        return "".equals(dateExp.trim()) ? null : dateExp;
    }
}
