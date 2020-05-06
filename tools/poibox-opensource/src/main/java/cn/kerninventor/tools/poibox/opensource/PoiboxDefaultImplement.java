package cn.kerninventor.tools.poibox.opensource;

import cn.kerninventor.tools.poibox.opensource.data.DataTabulationHandler;
import cn.kerninventor.tools.poibox.opensource.data.DataTabulator;
import cn.kerninventor.tools.poibox.opensource.developer.SealingVersion;
import cn.kerninventor.tools.poibox.opensource.layout.LayoutHandler;
import cn.kerninventor.tools.poibox.opensource.layout.Layouter;
import cn.kerninventor.tools.poibox.opensource.style.FontHandler;
import cn.kerninventor.tools.poibox.opensource.style.Fonter;
import cn.kerninventor.tools.poibox.opensource.style.StyleHandler;
import cn.kerninventor.tools.poibox.opensource.style.Styler;
import cn.kerninventor.tools.poibox.opensource.utils.ExcelDownloader;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Kern
 * @date 2019/10/29 14:16
 */
@SealingVersion(
        version = 1.00,
        zh_description = "poibox 默认实现"
)
public final class PoiboxDefaultImplement implements Poibox {

    private Workbook workbook;

    protected PoiboxDefaultImplement() {
        workbook = new HSSFWorkbook();
    }

    public PoiboxDefaultImplement(Workbook workbook) {
        this.workbook = workbook;
    }

    @Override
    public DataTabulator dataTabulator() {
        return new DataTabulationHandler(this);
    }

    @Override
    public Styler styler() {
        return new StyleHandler(this);
    }

    @Override
    public Fonter fonter() {
        return new FontHandler(this);
    }

    @Override
    public Layouter layouter() { return new LayoutHandler(this); }

    @Override
    public Workbook workbook() {
        return workbook;
    }

    @Override
    public Sheet getSheet(String sheetName) {
        return BoxGadget.getSheetForce(workbook, sheetName);
    }

    @Override
    public Sheet getSheet(int sheetAt) {
        return BoxGadget.getSheetForce(workbook, sheetAt);
    }

    @Override
    public void writeToHttp(HttpServletResponse response, String fileName) throws IOException {
        ExcelDownloader.writeToHttp(workbook, response, fileName);
        flush();
    }

    @Override
    public void writeToLocal(String fileFullName) throws IOException {
        ExcelDownloader.wirteToLocal(workbook, fileFullName);
        flush();
    }

    @Override
    public void flush(){
        if (workbook != null) {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
