package cn.kerninventor.tools.poibox.data;

import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.data.datatable.initializer.ExcelTabulationInitializer;
import cn.kerninventor.tools.poibox.data.datatable.templator.ExcelTabulationTemplator;
import cn.kerninventor.tools.poibox.data.datatable.templator.Templator;
import cn.kerninventor.tools.poibox.data.datatable.writer.ExcelTabulationWriter;
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
    public <T> Templator<T> templateTo(String sheetName, Class<T> sourceClass) {
        Sheet sheet = BoxGadget.getSheetForce(getParent().workbook(), sheetName);
        return templateTo(sheet, sourceClass);
    }

    @Override
    public <T> Templator<T> templateTo(int sheetIndex, Class<T> sourceClass) {
        Sheet sheet = BoxGadget.getSheetForce(getParent().workbook(), sheetIndex);
        return templateTo(sheet, sourceClass);
    }

    @Override
    public <T> Templator<T> templateTo(Sheet sheet, Class<T> sourceClass) {
        ExcelTabulationInitializer initializer = new ExcelTabulationInitializer(sourceClass);
        return new ExcelTabulationTemplator(initializer).tabulateTo(sheet, true);
    }

    @Override
    public <T> void writeDataTo(String sheetName, List<T> datas, Templator<T> templator) {
        Sheet sheet = BoxGadget.getSheetForce(getParent().workbook(), sheetName);
        writeDataTo(sheet, datas, templator);
    }

    @Override
    public <T> void writeDataTo(int sheetIndex, List<T> datas, Templator<T> templator) {
        Sheet sheet = BoxGadget.getSheetForce(getParent().workbook(), sheetIndex);
        writeDataTo(sheet, datas, templator);
    }

    @Override
    public <T> void writeDataTo(Sheet sheet, List<T> datas, Templator<T> templator) {
        new ExcelTabulationWriter<T>().writeTo(sheet, datas, templator);
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
