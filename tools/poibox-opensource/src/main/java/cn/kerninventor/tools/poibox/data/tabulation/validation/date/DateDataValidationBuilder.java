package cn.kerninventor.tools.poibox.data.tabulation.validation.date;

import cn.kerninventor.tools.poibox.data.tabulation.context.ClassFileColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.context.ClassFileTableContext;
import cn.kerninventor.tools.poibox.data.tabulation.validation.DataValidationBuilder;
import cn.kerninventor.tools.poibox.data.tabulation.validation.MessageBoxSetter;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author Kern
 * @date 2019/12/13 11:30
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

    public static DateDataValidationBuilder getInstance(DateDataValid dateDataValid) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateDataValid.parseFormat());
        Date current = new Date();
//        if (DateDataValid.NOW.equals(dataValid.date())) {
//            date = current;
//            dateEx = sdf.format(date);
//        } else {
//            date = sdf.parse(dateEx = dataValid.date().trim());
//        }
//        if (DateDataValid.NOW.equals(dataValid.optionalDate().trim())) {
//            optionalDate = current;
//            optionalDateEx = sdf.format(optionalDate);
//        } else if (!"".equals(dataValid.optionalDate().trim())){
//            optionalDate = sdf.parse(optionalDateEx = dataValid.optionalDate().trim());
//        }
//        if (dataValid.compareType().isOptionalValueValidity()){
//            Objects.requireNonNull(optionalDate, "The optionalDate() is not be Empty when compareType is bettwen or notBettwen! Field: " + columnInit.getFieldName());
//            if (date.after(optionalDate)){
//                throw new IllegalArgumentException("The optionalDate() is must be less than date() when compareType is bettwen or notBettwen! Field: " + columnInit.getFieldName());
//            }
//        }
        return null;
    }

    public DateDataValidationBuilder(SimpleDateFormat sdf, String dateEx, String optionalDateEx, Date date, Date optionalDate) {
        this.sdf = sdf;
        this.dateEx = dateEx;
        this.optionalDateEx = optionalDateEx;
        this.date = date;
        this.optionalDate = optionalDate;
    }

    public void addValidation(ClassFileTableContext tabulationInit, ClassFileColumnDefinition columnInit, Sheet sheet) {
        annotationValid(columnInit);
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DataValidationConstraint dvConstraint = dvHelper.createDateConstraint(
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


    private void annotationValid(ClassFileColumnDefinition columnInit) {
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
