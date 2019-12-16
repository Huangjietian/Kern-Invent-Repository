package cn.kerninventor.tools.poibox.data;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.POIBoxLinker;
import cn.kerninventor.tools.poibox.data.datatable.DataTabulation;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @Title: POIDataBoxOpened
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data
 * @Author Kern
 * @Date 2019/12/11 17:05
 * @Description: TODO
 */
public class POIDataBoxOpened extends POIBoxLinker implements POIDataBox {

    private Sheet sheet;
    private int sheetIndex;

    public POIDataBoxOpened(POIBox poiBox, String sheetName) {
        super(poiBox);
        sheet = getParent().working().getSheet(sheetName) == null ? getParent().working().createSheet(sheetName) : getParent().working().getSheet(sheetName);
        sheetIndex = getParent().working().getSheetIndex(sheet);
    }

    public POIDataBoxOpened(POIBox poiBox, int index) {
        super(poiBox);
        sheet = getParent().working().getSheetAt(index);
        if (sheet == null) {
            throw new NullPointerException("Sheet index 0 does not exist!");
        }
        sheetIndex = index;
    }

    @Override
    public Sheet getSheet() {
        return sheet;
    }

    @Override
    public int getSheetIndex() {
        return sheetIndex;
    }

    @Override
    public void template(Class clazz) {
        new DataTabulation(clazz).tabulateTo(this);
    }

}
