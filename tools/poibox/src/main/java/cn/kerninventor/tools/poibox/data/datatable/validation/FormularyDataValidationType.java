package cn.kerninventor.tools.poibox.data.datatable.validation;

/**
 * @Title: ExcelDataValidation
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.validation
 * @Author Kern
 * @Date 2019/12/11 15:39
 * @Description: excel data validation
 *
 * AnyValueValidation
 * IntegerValidation < = >    minValue maxValue  ignoreNull
 * DecimalsValidation < = >    minValue maxValue  ignoreNull
 * ArraysValidation   Text arrays cascade   ignoreNull  provideDropDownArrow
 * DateValidation < = >   startDate endDate   ignoreNull
 * TimeValidation < = >   startTime endTime   ignoreNull
 * TextLengthValidation  minLength maxLength   ignoreNull
 * FunctionValidation  function   ignoreNull
 *
 */
public enum  FormularyDataValidationType {

    ANY_VALUE,
    DIGITAL,
    DATE,
    TEXT_LENGTH,
    ;

}
