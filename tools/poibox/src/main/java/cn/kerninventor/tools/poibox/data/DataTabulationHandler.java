package cn.kerninventor.tools.poibox.data;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.datatable.ExcelTabulationDataProcessor;
import cn.kerninventor.tools.poibox.data.datatable.result.ExcelTemplate;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @Title: POIDataBoxOpened
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data
 * @Author Kern
 * @Date 2019/12/11 17:05
 * @Description: TODO
 */
public final class DataTabulationHandler extends BoxBracket implements DataTabulator {

    public DataTabulationHandler(POIBox poiBox) {
        super(poiBox);
    }

    @Override
    public ExcelTemplate templateTo(String sheetName, Class sourceClass) {
        Sheet sheet = BoxGadget.getSheetForce(getParent().workbook(), sheetName);
        new ExcelTabulationDataProcessor(sourceClass).tabulateTo(sheet, getParent(),null);
        return new ExcelTemplate(sheet);
    }

    @Override
    public ExcelTemplate templateTo(int sheetIndex, Class sourceClass) {
        Sheet sheet = BoxGadget.getSheetForce(getParent().workbook(), sheetIndex);
        new ExcelTabulationDataProcessor(sourceClass).tabulateTo(sheet, getParent(),null);
        return new ExcelTemplate(sheet);
    }

    @Override
    public ExcelTemplate templateTo(String sheetName, Class sourceClass, List datas) {
        Sheet sheet = BoxGadget.getSheetForce(getParent().workbook(), sheetName);
        new ExcelTabulationDataProcessor(sourceClass).tabulateTo(sheet, getParent(),datas);
        return new ExcelTemplate(sheet);
    }

    @Override
    public <T> List<T> readDatasFrom(String sheetName, Class<T> clazz) {
        Sheet sheet = getParent().workbook().getSheet(sheetName);
        if (sheet == null) {
            throw new NullPointerException("The worksheet data cannot be read because the worksheet with the name " + sheetName + " is empty");
        }
        return new ExcelTabulationDataProcessor(clazz).extractDatasFrom(sheet);
    }

    @Override
    public <T> List<T> readDatasFrom(int sheetIndex, Class<T> clazz) throws NoSuchMethodException {
        Sheet sheet = getParent().workbook().getSheetAt(sheetIndex);
        if (sheet == null) {
            throw new NullPointerException("The worksheet data cannot be read because the worksheet with the index " + sheetIndex + " is empty");
        }
        return new ExcelTabulationDataProcessor(clazz).extractDatasFrom(sheet);
    }

}
