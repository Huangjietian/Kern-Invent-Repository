package cn.kerninventor.tools.poibox.data;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.datatable.initializer.ExcelTabulationInitializer;
import cn.kerninventor.tools.poibox.data.datatable.templator.ExcelTabulationTemplator;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;
import java.util.Map;

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
    public ExcelTabulationTemplator templateTo(String sheetName, Class sourceClass) {
        Sheet sheet = BoxGadget.getSheetForce(getParent().workbook(), sheetName);
        return templateTo(sheet, sourceClass);
    }

    @Override
    public ExcelTabulationTemplator templateTo(int sheetIndex, Class sourceClass) {
        Sheet sheet = BoxGadget.getSheetForce(getParent().workbook(), sheetIndex);
        return templateTo(sheet, sourceClass);
    }

    @Override
    public ExcelTabulationTemplator templateTo(Sheet sheet, Class sourceClass) {
        ExcelTabulationInitializer initializer = new ExcelTabulationInitializer(sourceClass);
        return new ExcelTabulationTemplator(initializer).tabulateTo(sheet, getParent(), true);
    }

    @Override
    public <T> void writeDataTo(String sheetName, Class<T> sourceClass, List<T> datas) {
        Sheet sheet = BoxGadget.getSheetForce(getParent().workbook(), sheetName);
        ExcelTabulationInitializer processor = new ExcelTabulationInitializer(sourceClass);
        processor.writeDatasTo(sheet, getParent(), datas, false);
    }

    @Override
    public <T> void writeDataTo(int sheetIndex, Class<T> sourceClass, List<T> datas) {
        Sheet sheet = BoxGadget.getSheetForce(getParent().workbook(), sheetIndex);
        ExcelTabulationInitializer processor = new ExcelTabulationInitializer(sourceClass);
        processor.writeDatasTo(sheet, getParent(), datas, false);
    }

    @Override
    public <T> void writeDataTo(Sheet sheet, Class<T> sourceClass , List<T> datas) {
        ExcelTabulationInitializer processor = new ExcelTabulationInitializer(sourceClass);
        processor.writeDatasTo(sheet, getParent(), datas, false);
    }

    @Override
    public <T> List<T> readDatasFrom(String sheetName, Class<T> clazz) {
        Sheet sheet = getParent().workbook().getSheet(sheetName);
        if (sheet == null) {
            throw new NullPointerException("The worksheet data cannot be read because the worksheet with the name " + sheetName + " is empty");
        }
        return new ExcelTabulationInitializer(clazz).readFrom(sheet);
    }

    @Override
    public <T> List<T> readDatasFrom(int sheetIndex, Class<T> clazz) {
        Sheet sheet = getParent().workbook().getSheetAt(sheetIndex);
        if (sheet == null) {
            throw new NullPointerException("The worksheet data cannot be read because the worksheet with the index " + sheetIndex + " is empty");
        }
        return new ExcelTabulationInitializer(clazz).readFrom(sheet);
    }

    @Override
    public <T> List<T> readDatasFrom(Sheet sheet, Class<T> clazz) {
        if (sheet == null) {
            throw new NullPointerException("The worksheet data cannot be read because the worksheet with the name " + sheet.getSheetName() + " is empty");
        }
        return new ExcelTabulationInitializer(clazz).readFrom(sheet);
    }

}
