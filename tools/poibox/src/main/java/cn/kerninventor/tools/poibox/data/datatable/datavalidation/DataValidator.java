package cn.kerninventor.tools.poibox.data.datatable.datavalidation;

import cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.ExcelValidArray;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.ExcelValidArrayBuilder;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.date.ExcelValidDate;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.date.ExcelValidDateBuilder;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.decimal.ExcelValidDecimal;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.decimal.ExcelValidDecimalBuilder;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.integer.ExcelValidInt;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.integer.ExcelValidIntBuilder;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.textLength.ExcelValidTextLengthBuilder;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.textLength.ExcelValidTextlength;

import java.lang.annotation.Annotation;
import java.lang.annotation.AnnotationFormatError;

/**
 * @Title ValidExecutor
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.datavalidation
 * @Author Kern
 * @Date 2020/3/11 11:11
 * @Description TODO
 */
public class DataValidator {




    static Boolean valid(Annotation annotation, Object value) {
        if (null == annotation){
            return true;
        }
        //date validation
        if (annotation instanceof ExcelValidDate){

        }
        else if (annotation instanceof ExcelValidDecimal){

        }
        else if (annotation instanceof ExcelValidInt){

        }
        else if (annotation instanceof ExcelValidTextlength){

        }
        else if (annotation instanceof ExcelValidArray){
//            ((ExcelValidArray)annotation).dictionary().
        }
        else {
            throw new AnnotationFormatError("not found ExcelValidHandler by this annotation: " + annotation.getClass());
        }


        return null;
    }
}
