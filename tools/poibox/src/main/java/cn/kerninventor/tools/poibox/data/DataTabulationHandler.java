package cn.kerninventor.tools.poibox.data;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.datatable.ExcelTabulationDataProcessor;
import cn.kerninventor.tools.poibox.data.datatable.result.SheetTemplate;
import org.apache.poi.ss.usermodel.Sheet;
import org.omg.CosNaming.IstringHelper;

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
    public SheetTemplate templateTo(String sheetName, Class sourceClass) {
        Sheet sheet = BoxGadget.getSheetForce(getParent().workbook(), sheetName);
        return templateTo(sheet, sourceClass);
    }

    @Override
    public SheetTemplate templateTo(int sheetIndex, Class sourceClass) {
        Sheet sheet = BoxGadget.getSheetForce(getParent().workbook(), sheetIndex);
        return templateTo(sheet, sourceClass);
    }

    @Override
    public SheetTemplate templateTo(Sheet sheet, Class sourceClass) {
        ExcelTabulationDataProcessor processor = new ExcelTabulationDataProcessor(sourceClass);
        processor.tabulateTo(sheet, getParent(), true);
        return new SheetTemplate(sheet);
    }

    @Override
    public <T> void writeTo(Sheet sheet, Class<T> sourceClass , List<T> datas) {
        ExcelTabulationDataProcessor processor = new ExcelTabulationDataProcessor(sourceClass);
        processor.writeDatasTo(sheet, getParent(), datas, false);
    }


    @Override
    public <T> List<T> readDatasFrom(String sheetName, Class<T> clazz) {
        Sheet sheet = getParent().workbook().getSheet(sheetName);
        if (sheet == null) {
            throw new NullPointerException("The worksheet data cannot be read because the worksheet with the name " + sheetName + " is empty");
        }
        return new ExcelTabulationDataProcessor(clazz).readFrom(sheet, getParent());
    }

    @Override
    public <T> List<T> readDatasFrom(int sheetIndex, Class<T> clazz) {
        Sheet sheet = getParent().workbook().getSheetAt(sheetIndex);
        if (sheet == null) {
            throw new NullPointerException("The worksheet data cannot be read because the worksheet with the index " + sheetIndex + " is empty");
        }
//        return new ExcelTabulationDataProcessor(clazz).extractDatasFrom(sheet);
        return null;
    }

}
