package cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array;

import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.DataValidationBuilder;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.MessageBoxSetter;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary.api.DictionaryEntry;
import cn.kerninventor.tools.poibox.data.templatedtable.initializer.ExcelColumnInitializer;
import cn.kerninventor.tools.poibox.data.templatedtable.initializer.ExcelTabulationInitializer;
import cn.kerninventor.tools.poibox.data.utils.NameManegeUtil;
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
    public void addValidation(ExcelTabulationInitializer tabulationInit, ExcelColumnInitializer columnInit, Sheet sheet) {
        List<DictionaryEntry> entries = (List<DictionaryEntry>) columnInit.getInterpretor().getEntries();
        if (entries == null){
            entries = new ArrayList<>();
            entries.add(new DictionaryEntry() {
                @Override
                public Object getViewdata() {
                    return "NO DATA";
                }
            });
        }
        List<String> viewDatas = entries.stream().map(e -> e.getViewdata().toString()).collect(Collectors.toList());
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DataValidationConstraint dvConstraint ;
        if (viewDatas.toString().length() <= 255){
            dvConstraint = dvHelper.createExplicitListConstraint(viewDatas.toArray(new String[viewDatas.size()]));
        } else {
            String nameName = NameManegeUtil.addNameManage(sheet, "hidden", columnInit.getTitleName(), columnInit.getFieldName(), viewDatas.toArray(new String[viewDatas.size()]));
            dvConstraint = dvHelper.createFormulaListConstraint(nameName);
        }

        CellRangeAddressList dvRange = new CellRangeAddressList(
                tabulationInit.getTableTextRdx(),
                (tabulationInit.getTableTextRdx() + tabulationInit.getTextRowNum()) * 50,
                columnInit.getColumnIndex(),
                columnInit.getColumnIndex()
        );
        DataValidation dataValidation = dvHelper.createValidation(dvConstraint, dvRange);
        MessageBoxSetter.setPrompBoxMessage(dataValidation, excelValid.prompMessage());
        MessageBoxSetter.setErrorBoxMessage(dataValidation, excelValid.errorMessage());
        sheet.addValidationData(dataValidation);
    }

}
