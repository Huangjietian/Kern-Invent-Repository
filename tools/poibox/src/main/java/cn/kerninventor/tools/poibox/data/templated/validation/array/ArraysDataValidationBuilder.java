package cn.kerninventor.tools.poibox.data.templated.validation.array;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.exception.IllegalColumnConfigureException;
import cn.kerninventor.tools.poibox.data.templated.validation.DataValidationBuilder;
import cn.kerninventor.tools.poibox.data.templated.validation.MessageBoxSetter;
import cn.kerninventor.tools.poibox.data.templated.validation.array.dictionary.DictionaryInterpretor;
import cn.kerninventor.tools.poibox.data.templated.validation.array.dictionary.api.DictionaryEntry;
import cn.kerninventor.tools.poibox.data.templated.initializer.EColumnInitiator;
import cn.kerninventor.tools.poibox.data.templated.initializer.ETabulationInitiator;
import cn.kerninventor.tools.poibox.utils.NameManegeUtil;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Kern
 * @Date 2019/12/13 15:39
 */
public class ArraysDataValidationBuilder implements DataValidationBuilder<ArrayDataValid> {

    private ArrayDataValid excelValid;

    public ArraysDataValidationBuilder(ArrayDataValid excelValid) {
        this.excelValid = excelValid;
    }

    @Override
    public void addValidation(ETabulationInitiator tabulationInit, EColumnInitiator columnInit, Sheet sheet) {
        List<DictionaryEntry> entries = (List<DictionaryEntry>) columnInit.getInterpretor().getEntries();
        if (entries == null){
            entries = new ArrayList<>();
            for (String str : excelValid.defValuesWhenEmpty()) {
                entries.add(new DictionaryEntry() {
                    @Override
                    public Object getViewdata() {
                        return str;
                    }
                });
            }
        }
        List<String> viewData = entries.stream().map(e -> e.getViewdata().toString()).collect(Collectors.toList());
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DataValidationConstraint dvConstraint ;
        if (viewData.toString().length() <= 255){
            String[] array = viewData.toArray(new String[viewData.size()]);
            dvConstraint = dvHelper.createExplicitListConstraint(array);
        } else {
            String nameName = columnInit.getFieldName() + columnInit.getTitleName();
            NameManegeUtil.addNameManage(sheet, nameName, viewData);
            dvConstraint = dvHelper.createFormulaListConstraint(nameName);
        }
//        List<String> viewData = entries.stream().map(e -> e.getViewdata().toString()).collect(Collectors.toList());
//        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
//        String nameName = columnInit.getFieldName() + columnInit.getTitleName();
//        NameManegeUtil.addNameManage(sheet, nameName, viewData);
//        DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint(nameName);
        CellRangeAddressList dvRange = new CellRangeAddressList(
                tabulationInit.getTbodyFirstRowIndex(),
                (tabulationInit.getTbodyFirstRowIndex() + tabulationInit.getEffectiveRows() - 1),
                columnInit.getColumnIndex(),
                columnInit.getColumnIndex()
        );
        DataValidation dataValidation = dvHelper.createValidation(dvConstraint, dvRange);
        MessageBoxSetter.setPrompBoxMessage(dataValidation, excelValid.prompMessage());
        MessageBoxSetter.setErrorBoxMessage(dataValidation, excelValid.errorMessage());
        sheet.addValidationData(dataValidation);
    }

    public void addCascadeDataValidation(ETabulationInitiator tabulationInit, EColumnInitiator columnInit, Sheet sheet) {
        //级联 !"".equals(excelValid.cascadeFlow().trim())
        if (true) {
            DictionaryInterpretor interpretor = columnInit.getInterpretor();
            if (interpretor.getEntries() != null && interpretor.getEntries().isEmpty()) {

            }



            EColumnInitiator parentColumn = tabulationInit.getColumnInitializerByTitleName("excelValid.cascadeFlow()");
            if (parentColumn == null) {
                throw new IllegalColumnConfigureException("The field name as " + "excelValid.cascadeFlow()" + "'s field does not found, please check your configuration on @ArrayDataValid");
            }
            //查询上级和下级字典。组装成上下级。
            List<DictionaryEntry> pEntries = (List<DictionaryEntry>) parentColumn.getInterpretor().getEntries();
            List<DictionaryEntry> cEntries = (List<DictionaryEntry>) columnInit.getInterpretor().getEntries();

            int sourceColumnIndex = parentColumn.getColumnIndex();
            String indexTransfer = BoxGadget.transferExcelColumnIndex(sourceColumnIndex);
            for (int i = tabulationInit.getTbodyFirstRowIndex() ; i < tabulationInit.getTbodyFirstRowIndex() + tabulationInit.getEffectiveRows() ; i ++) {
                CellRangeAddressList dvRange = new CellRangeAddressList(i, i, sourceColumnIndex, sourceColumnIndex);
                DataValidationHelper dvHelper = sheet.getDataValidationHelper();
                DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint("=INDIRECT(" + indexTransfer + i + ")");
                DataValidation dataValidation = dvHelper.createValidation(dvConstraint, dvRange);
                sheet.addValidationData(dataValidation);
            }
        }
    }

}
