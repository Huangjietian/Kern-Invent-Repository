package cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary;

import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.ArrayDataValid;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary.api.DictionaryEntry;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary.api.ReferDictionaryEntry;
import cn.kerninventor.tools.poibox.data.utils.CellValueUtil;
import org.apache.poi.ss.usermodel.Cell;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author Kern
 * @date 2020/3/12 22:09
 */
public class DictionaryInterpretor {

    private boolean interpretable;
    private ArrayDataValid arrayDataValid;
    private List<? extends DictionaryEntry> entries;

    public boolean isInterpretable() {
        return interpretable;
    }

    public ArrayDataValid getArrayDataValid() {
        return arrayDataValid;
    }

    public List<? extends DictionaryEntry> getEntries() {
        return entries;
    }

    DictionaryInterpretor setInterpretable(boolean interpretable) {
        this.interpretable = interpretable;
        return this;
    }

    DictionaryInterpretor setArrayDataValid(ArrayDataValid arrayDataValid) {
        this.arrayDataValid = arrayDataValid;
        return this;
    }

    DictionaryInterpretor setEntries(List<? extends DictionaryEntry> entries) {
        this.entries = entries;
        return this;
    }

    DictionaryInterpretor() {
    }

    public Object interpreteOf(Object metaData) {
        if (metaData == null || !isInterpretable()) {
            return metaData;
        }
        List<ReferDictionaryEntry> referEntries = (List<ReferDictionaryEntry>) getEntries();
        Object register = null;
        for (ReferDictionaryEntry body : referEntries){
            if (body.getMetadata() != null && body.getMetadata().equals(metaData)){
                register = body.getViewdata();
                break;
            }
        }
        return register;
    }

    public Object getMetaDataFrom(Cell cell) {
        if (!isInterpretable()){
            return null;
        }
        List<ReferDictionaryEntry> referEntries = (List<ReferDictionaryEntry>) getEntries();
        ReferDictionaryEntry referEntry = referEntries.get(0);
        ParameterizedType entryType = DictionaryLibrary.getEntryType(referEntry);
        Class viewType = (Class) entryType.getActualTypeArguments()[0];
        Object value = CellValueUtil.getCellValueBySpecifiedType(cell, viewType);
        for (ReferDictionaryEntry body : referEntries){
            if (body.getViewdata().equals(value)){
                return body.getMetadata();
            }
        }
        return null;
    }
}
