package cn.kerninventor.tools.poibox.data.datatable.datavalidation.date;

import cn.kerninventor.tools.poibox.data.datatable.ExcelcolumnDataAccepter;
import cn.kerninventor.tools.poibox.data.datatable.ExcelTabulationDataProcessor;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.DataValidBuilder;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.MessageBoxUtil;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.text.ParseException;
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
public class ExcelValidDateBuilder implements DataValidBuilder<ExcelValidDate> {

    private ExcelValidDate excelValid;
    private SimpleDateFormat sdf;
    private String dateEx;
    private String optionalDateEx;
    private Date date;
    private Date optionalDate;

    public ExcelValidDateBuilder(ExcelValidDate excelValid) {
        this.excelValid = excelValid;
    }

    public void addValidation(ExcelTabulationDataProcessor processor, ExcelcolumnDataAccepter accepter, Sheet sheet) {
        //TODO 解析格式验证时间字段的有效性
        try {
            annotationValid(accepter);
        } catch (ParseException e) {
            throw new IllegalArgumentException("ExcelVCalid_DATE parse date failed! parseFormat, please check your configuration of field : " + accepter.getFieldName());
        }

        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DVConstraint dvConstraint = DVConstraint.createDateConstraint(
                excelValid.compareType().getCode(),
                dateEx,
                optionalDateEx,
                excelValid.parseFormat()
        );
        CellRangeAddressList dvRange = new CellRangeAddressList(
                processor.getTableTextRdx(),
                processor.getTableTextRdx()+ processor.getTextRowNum(),
                accepter.getColumnIndex(),
                accepter.getColumnIndex()
        );
        DataValidation dataValidation = dvHelper.createValidation(dvConstraint, dvRange);
        MessageBoxUtil.setPrompBoxMessage(dataValidation, excelValid.prompMessage());
        MessageBoxUtil.setErrorBoxMessage(dataValidation, excelValid.errorMessage());
        sheet.addValidationData(dataValidation);
    }


    private void annotationValid(ExcelcolumnDataAccepter accepter) throws ParseException {
        sdf = new SimpleDateFormat(excelValid.parseFormat());
        Date current = new Date();
        if (ExcelValidDate.NOW.equals(excelValid.date())) {
            date = current;
            dateEx = sdf.format(date);
        } else {
            date = sdf.parse(dateEx = excelValid.date().trim());
        }
        if (ExcelValidDate.NOW.equals(excelValid.optionalDate().trim())) {
            optionalDate = current;
            optionalDateEx = sdf.format(optionalDate);
        } else if (!"".equals(excelValid.optionalDate().trim())){
            optionalDate = sdf.parse(optionalDateEx = excelValid.optionalDate().trim());
        }
        if (excelValid.compareType().isOptionalValueValidity()){
            Objects.requireNonNull(optionalDate, "The optionalDate() is not be Empty when compareType is bettwen or notBettwen! Field: " + accepter.getFieldName());
            if (date.after(optionalDate)){
                throw new IllegalArgumentException("The optionalDate() is must be less than date() when compareType is bettwen or notBettwen! Field: " + accepter.getFieldName());
            }
        }
    }

}
