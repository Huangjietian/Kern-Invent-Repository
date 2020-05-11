package cn.kerninventor.tools.poibox.opensource.data.templated.validation;

import org.apache.poi.ss.usermodel.DataValidation;

/**
 * @author Kern
 * @date 2019/12/13 12:52
 */
public class MessageBoxSetter {

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
