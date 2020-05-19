package cn.kerninventor.tools.poibox.opensource;

import cn.kerninventor.tools.poibox.opensource.data.DataTabulationHandler;
import cn.kerninventor.tools.poibox.opensource.data.DataTabulator;
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
public final class PoiboxDefaultImplement implements Poibox {

    private Workbook workbook;
    private DataTabulationHandler dataTabulationHandler;
    private Styler styler;
    private Fonter fonter;
    private Layouter layouter;

    protected PoiboxDefaultImplement() {
        workbook = new HSSFWorkbook();
    }

    public PoiboxDefaultImplement(Workbook workbook) {
        this.workbook = workbook;
    }

    @Override
    public DataTabulator dataTabulator() {
        if (dataTabulationHandler == null) {
            dataTabulationHandler = new DataTabulationHandler(this);
        }
        return dataTabulationHandler;
    }

    @Override
    public Styler styler() {
        if (styler == null) {
            styler = new StyleHandler(this);
        }
        return styler;
    }

    @Override
    public Fonter fonter() {
        if (fonter == null) {
            fonter = new FontHandler(this);
        }
        return fonter;
    }

    @Override
    public Layouter layouter() {
        if (layouter == null) {
            layouter = new LayoutHandler(this);
        }
        return layouter;
    }

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
