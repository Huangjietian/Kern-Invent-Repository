package cn.kerninventor.tools.poibox.data.datatable.validation;

import org.apache.poi.ss.usermodel.DataValidation;

/**
 * @Title: ExcelDataValidHandlerCommonComponent
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.validation
 * @Author Kern
 * @Date 2019/12/13 12:52
 * @Description: TODO
 */
public class MessageBoxUtil {

    public static void setPrompBoxMessage(DataValidation dataValidation, String message) {
        if (!"".equals(message)){
            dataValidation.createPromptBox("promp", message);
        }
    }

    public static void setErrorBoxMessage(DataValidation dataValidation, String message) {
        if (!"".equals(message)){
            dataValidation.createErrorBox("error", message);
        }
    }

}
