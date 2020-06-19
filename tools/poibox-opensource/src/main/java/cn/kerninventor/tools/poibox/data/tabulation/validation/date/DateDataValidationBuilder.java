package cn.kerninventor.tools.poibox.data.tabulation.validation.date;

import cn.kerninventor.tools.poibox.data.tabulation.context.ColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.context.TabulationBeanConfiguration;
import cn.kerninventor.tools.poibox.data.tabulation.validation.AbstractDvBuilder;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author Kern
 * @date 2019/12/13 11:30
 */
public class DateDataValidationBuilder extends AbstractDvBuilder<DateDataValid> {

    private SimpleDateFormat sdf;
    private String dateEx;
    private String optionalDateEx;
    private Date date;
    private Date optionalDate;

    public DateDataValidationBuilder(DateDataValid dateDataValid) {
        super(dateDataValid);
    }

    @Override
    protected DataValidationConstraint createDvConstraint(DataValidationHelper dvHelper) {
        DateDataValid dateDataValid = getAnnotation();
        DataValidationConstraint dvConstraint = dvHelper.createDateConstraint(
                dateDataValid.compareType().getCode(),
                dateEx,
                optionalDateEx,
                dateDataValid.parseFormat()
        );
        return dvConstraint;
    }

    @Override
    protected void setBoxMessage(DataValidation dataValidation) {
        dataValidation.createPromptBox(getPromptBoxName(), getAnnotation().promptMessage());
        dataValidation.createPromptBox(getErrorBoxName(), getAnnotation().errorMessage());
    }

    @Override
    public void setDataValidation(TabulationBeanConfiguration tableContext, ColumnDefinition columnDefinition, Sheet sheet) {
        annotationValid(columnDefinition, getAnnotation());
        super.setDataValidation(tableContext, columnDefinition, sheet);
    }

    private void annotationValid(ColumnDefinition columnDefinition, DateDataValid dateDataValid) {
        try {
            sdf = new SimpleDateFormat(dateDataValid.parseFormat());
            Date current = new Date();
            if (DateDataValid.NOW.equals(dateDataValid.date())) {
                date = current;
                dateEx = sdf.format(date);
            } else {
                date = sdf.parse(dateEx = dateDataValid.date().trim());
            }
            if (DateDataValid.NOW.equals(dateDataValid.optionalDate().trim())) {
                optionalDate = current;
                optionalDateEx = sdf.format(optionalDate);
            } else if (!"".equals(dateDataValid.optionalDate().trim())){
                optionalDate = sdf.parse(optionalDateEx = dateDataValid.optionalDate().trim());
            }
            if (dateDataValid.compareType().isOptionalValueValidity()){
                Objects.requireNonNull(optionalDate, "The optionalDate() is not be Empty when compareType is bettwen or notBettwen! Field: " + columnDefinition.getFieldName());
                if (date.after(optionalDate)){
                    throw new IllegalArgumentException("The optionalDate() is must be less than date() when compareType is bettwen or notBettwen! Field: " + columnDefinition.getFieldName());
                }
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("Date parse failed! parseFormat, please check your configuration of field : " + columnDefinition.getFieldName());
        }
    }

}
