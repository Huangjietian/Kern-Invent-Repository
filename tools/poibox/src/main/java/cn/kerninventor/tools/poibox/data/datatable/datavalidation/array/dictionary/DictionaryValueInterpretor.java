package cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.dictionary;

import cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.ExcelValidArray;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.dictionary.metaView.MetaViewBody;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.dictionary.metaView.MetaViewDictionary;
import cn.kerninventor.tools.poibox.data.utils.CellValueUtil;
import org.apache.poi.ss.usermodel.Cell;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @Title: DictValueTranslator
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.dictionary
 * @Author Kern
 * @Date 2020/3/12 22:09
 * @Description: TODO
 */
public class DictionaryValueInterpretor {

    public boolean isInterpretable() {
        return interpretable;
    }

    private boolean interpretable;
    private ExcelValidArray excelValidArray;

    public static DictionaryValueInterpretor newInstance(Annotation excelValid) {
        DictionaryValueInterpretor interpretor = new DictionaryValueInterpretor();
        interpretor.interpretable = (excelValid instanceof ExcelValidArray && MetaViewDictionary.class.isAssignableFrom(((ExcelValidArray) excelValid).dictionary()));
        if (interpretor.interpretable){
            interpretor.excelValidArray = (ExcelValidArray) excelValid;
            return interpretor;
        }
        return new DictionaryValueInterpretor();
    }

    private DictionaryValueInterpretor() {
    }

    public Object interpreteOf(Object metaData) {
        if (metaData == null || !interpretable) {
            return metaData;
        }
        List<MetaViewBody> metaViewBodies = ExcelDictionaryLibrary.referDict(excelValidArray.dictionary());
        if (metaViewBodies == null || metaViewBodies.isEmpty()) {
            return metaData;
        }
        Object register = null;
        for (MetaViewBody body : metaViewBodies){
            if (body.getMetadata() != null && body.getMetadata().equals(metaData)){
                register = body.getViewdata();
                break;
            }
        }
        return register;
    }

    public Object getMetaDataFrom(Cell cell) {
        List<MetaViewBody> metaViewBodies = ExcelDictionaryLibrary.referDict(excelValidArray.dictionary());
        if (metaViewBodies != null && !metaViewBodies.isEmpty()) {
            Class viewType = metaViewBodies.get(0).getVType();
            Object value = CellValueUtil.getCellValueBySpecifiedType(cell, viewType);
            for (MetaViewBody body : metaViewBodies){
                if (body.getViewdata().equals(value)){
                    return body.getMetadata();
                }
            }
        }
        return null;
    }
}
