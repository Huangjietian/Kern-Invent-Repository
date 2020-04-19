package cn.kerninventor.tools.poibox.data.templated.validation.date;

import cn.kerninventor.tools.poibox.data.templated.initializer.EColumnInitiator;
import cn.kerninventor.tools.poibox.data.templated.initializer.ETabulationInitiator;
import cn.kerninventor.tools.poibox.data.templated.validation.DataValidationBuilder;
import cn.kerninventor.tools.poibox.data.templated.validation.MessageBoxSetter;
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
public class DateDataValidationBuilder implements DataValidationBuilder<DateDataValid> {

    private DateDataValid dataValid;
    private SimpleDateFormat sdf;
    private String dateEx;
    private String optionalDateEx;
    private Date date;
    private Date optionalDate;

    public DateDataValidationBuilder(DateDataValid dataValid) {
        this.dataValid = dataValid;
    }

    public void addValidation(ETabulationInitiator tabulationInit, EColumnInitiator columnInit, Sheet sheet) {
        annotationValid(columnInit);

        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DVConstraint dvConstraint = DVConstraint.createDateConstraint(
                dataValid.compareType().getCode(),
                dateEx,
                optionalDateEx,
                dataValid.parseFormat()
        );
        CellRangeAddressList dvRange = new CellRangeAddressList(
                tabulationInit.getTbodyFirstRowIndex(),
                tabulationInit.getTbodyFirstRowIndex()+ tabulationInit.getEffectiveRows() - 1,
                columnInit.getColumnIndex(),
                columnInit.getColumnIndex()
        );
        DataValidation dataValidation = dvHelper.createValidation(dvConstraint, dvRange);
        MessageBoxSetter.setPrompBoxMessage(dataValidation, dataValid.prompMessage());
        MessageBoxSetter.setErrorBoxMessage(dataValidation, dataValid.errorMessage());
        sheet.addValidationData(dataValidation);
    }


    private void annotationValid(EColumnInitiator columnInit) {
        try {
            sdf = new SimpleDateFormat(dataValid.parseFormat());
            Date current = new Date();
            if (DateDataValid.NOW.equals(dataValid.date())) {
                date = current;
                dateEx = sdf.format(date);
            } else {
                date = sdf.parse(dateEx = dataValid.date().trim());
            }
            if (DateDataValid.NOW.equals(dataValid.optionalDate().trim())) {
                optionalDate = current;
                optionalDateEx = sdf.format(optionalDate);
            } else if (!"".equals(dataValid.optionalDate().trim())){
                optionalDate = sdf.parse(optionalDateEx = dataValid.optionalDate().trim());
            }
            if (dataValid.compareType().isOptionalValueValidity()){
                Objects.requireNonNull(optionalDate, "The optionalDate() is not be Empty when compareType is bettwen or notBettwen! Field: " + columnInit.getFieldName());
                if (date.after(optionalDate)){
                    throw new IllegalArgumentException("The optionalDate() is must be less than date() when compareType is bettwen or notBettwen! Field: " + columnInit.getFieldName());
                }
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("Date parse failed! parseFormat, please check your configuration of field : " + columnInit.getFieldName());
        }
    }

}
