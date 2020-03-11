package cn.kerninventor.tools.poibox.data.datatable.datavalidation.array;

import cn.kerninventor.tools.poibox.data.datatable.ExcelTabulationDataProcessor;
import cn.kerninventor.tools.poibox.data.datatable.ExcelcolumnDataAccepter;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.DataValidBuilder;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.MessageBoxUtil;
import cn.kerninventor.tools.poibox.data.datatable.dictionary.ExcelDictionaryLibrary;
import cn.kerninventor.tools.poibox.data.datatable.dictionary.metaView.MetaViewDictionary;
import cn.kerninventor.tools.poibox.data.datatable.dictionary.view.ViewBody;
import cn.kerninventor.tools.poibox.data.datatable.dictionary.view.ViewDictionary;
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
 * @Title: ExcelArrayValidHandler
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.validation.array
 * @Author Kern
 * @Date 2019/12/13 15:39
 * @Description: TODO
 */
public class ExcelValidArrayBuilder implements DataValidBuilder<ExcelValidArray> {

    private ExcelValidArray excelValid;

    public ExcelValidArrayBuilder(ExcelValidArray excelValid) {
        this.excelValid = excelValid;
    }

    @Override
    public void addValidation(ExcelTabulationDataProcessor processor, ExcelcolumnDataAccepter accepter, Sheet sheet) {
        List<ViewBody> view = ExcelDictionaryLibrary.referDict(excelValid.dictionary());
        if (view == null){
            view = new ArrayList<>();
            view.add(new ViewBody() {
                @Override
                public Object getViewdata() {
                    return "NO DATA";
                }
            });
        }
        List<String> viewDatas = view.stream().map(e -> e.getViewdata().toString()).collect(Collectors.toList());
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DataValidationConstraint dvConstraint ;
        if (viewDatas.toString().length() <= 255){
            dvConstraint = dvHelper.createExplicitListConstraint(viewDatas.toArray(new String[viewDatas.size()]));
        } else {
            String nameName = NameManegeUtil.addNameManage(sheet, "hidden", accepter.getTitleName(), accepter.getFieldName(), viewDatas.toArray(new String[viewDatas.size()]));
            dvConstraint = dvHelper.createFormulaListConstraint(nameName);
        }

        CellRangeAddressList dvRange = new CellRangeAddressList(
                processor.getTableTextRdx(),
                (processor.getTableTextRdx() + processor.getTextRowNum()) * 50,
                accepter.getColumnIndex(),
                accepter.getColumnIndex()
        );
        DataValidation dataValidation = dvHelper.createValidation(dvConstraint, dvRange);
        MessageBoxUtil.setPrompBoxMessage(dataValidation, excelValid.prompMessage());
        MessageBoxUtil.setErrorBoxMessage(dataValidation, excelValid.errorMessage());
        sheet.addValidationData(dataValidation);
    }

}
