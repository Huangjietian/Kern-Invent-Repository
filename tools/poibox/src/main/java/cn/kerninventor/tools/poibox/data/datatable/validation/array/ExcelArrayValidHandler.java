package cn.kerninventor.tools.poibox.data.datatable.validation.array;

import cn.kerninventor.tools.poibox.data.datatable.DataColumn;
import cn.kerninventor.tools.poibox.data.datatable.DataTabulation;
import cn.kerninventor.tools.poibox.data.datatable.dictionary.ExcelDictionaryLibrary;
import cn.kerninventor.tools.poibox.data.datatable.dictionary.metaView.MetaViewDictionary;
import cn.kerninventor.tools.poibox.data.datatable.dictionary.view.ViewBody;
import cn.kerninventor.tools.poibox.data.datatable.dictionary.view.ViewDictionary;
import cn.kerninventor.tools.poibox.data.datatable.validation.DataValidHandler;
import cn.kerninventor.tools.poibox.data.nameName.NameManegeUtil;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: ExcelArrayValidHandler
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.validation.array
 * @Author Kern
 * @Date 2019/12/13 15:39
 * @Description: TODO
 */
public class ExcelArrayValidHandler implements DataValidHandler<ExcelValid_ARRAY> {

    @Override
    public void addValidation(DataTabulation dataTabulation, DataColumn dataColumn, Sheet sheet, ExcelValid_ARRAY excelValid) {
        annotationValid(dataColumn, excelValid);
        Class clazz = excelValid.dictionary();
        List<ViewBody> view;
        if (MetaViewDictionary.class.isAssignableFrom(clazz)){
            view = ExcelDictionaryLibrary.referMetaViewDict(clazz);
        } else if (ViewDictionary.class.isAssignableFrom(clazz)){
            view = ExcelDictionaryLibrary.referViewDict(clazz);
        } else {
            throw new IllegalArgumentException("The dictionary must be an enumeration that implements the interface MetaViewEnum or a concrete implementation that inherits the abstarct class MetaViewDictionaryCover / ViewDictionaryCover.");
        }
        if (view == null){
            view = new ArrayList<>();
        }
        List<String> viewDatas = view.stream().map(e -> e.getViewdata().toString()).collect(Collectors.toList());
        if (viewDatas.size() == 0){
            viewDatas.add("NO DATA");
        }
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        CellRangeAddressList dvRange = new CellRangeAddressList(
                dataTabulation.getStartRowIndex(),
                (dataTabulation.getStartTextRowIndex() + dataTabulation.getTextRowNum()) * 50,
                dataColumn.getColumnIndex(),
                dataColumn.getColumnIndex()
        );
        DataValidationConstraint dvConstraint ;
        if (viewDatas.toString().length() <= 255){
            dvConstraint = dvHelper.createExplicitListConstraint(viewDatas.toArray(new String[viewDatas.size()]));
        } else {
            String nameName = NameManegeUtil.addNameManage(sheet, "hidden", dataColumn.getFieldName(), dataColumn.getTitleName(), viewDatas.toArray(new String[viewDatas.size()]));
            dvConstraint = dvHelper.createFormulaListConstraint(nameName);
        }
        DataValidation dataValidation = dvHelper.createValidation(dvConstraint, dvRange);
        sheet.addValidationData(dataValidation);
    }

    private void annotationValid(DataColumn dataColumn, ExcelValid_ARRAY excelValid) {
//        if (!java.util.List.class.isAssignableFrom(dataColumn.getFieldClass())){
//            throw new IllegalArgumentException("The field Annotated @ExcelValid_ARRAY must be Inheritance in java.util.List! Field: " + dataColumn.getFieldName());
//        }
    }

}
