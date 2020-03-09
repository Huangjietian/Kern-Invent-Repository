package cn.kerninventor.tools.poibox;

import cn.kerninventor.tools.poibox.data.DataTabulator;
import cn.kerninventor.tools.poibox.data.DataTabulationHandler;
import cn.kerninventor.tools.poibox.layout.Layouter;
import cn.kerninventor.tools.poibox.layout.LayoutHandler;
import cn.kerninventor.tools.poibox.style.Fonter;
import cn.kerninventor.tools.poibox.style.FontHandler;
import cn.kerninventor.tools.poibox.style.Styler;
import cn.kerninventor.tools.poibox.style.StyleHandler;
import cn.kerninventor.tools.poibox.utils.ExcelDownloader;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Title: POIBox
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/29 14:16
 */
public final class POIBoxOpened implements POIBox {

    private Workbook workbook;

    private Styler styler;

    private Fonter fonter;

    private Layouter layouter;

    private DataTabulator dataTabulator;

    protected POIBoxOpened() {
        workbook = new HSSFWorkbook();
        init();
    }

    protected POIBoxOpened(InputStream source) throws IOException, InvalidFormatException {
        workbook = WorkbookFactory.create(source);
        init();
    }

    private void init(){
        styler = new StyleHandler(this);
        fonter = new FontHandler(this);
        layouter = new LayoutHandler(this);
        dataTabulator = new DataTabulationHandler(this);
    }

    @Override
    public DataTabulator dataTabulator() {
        return dataTabulator;
    }

    @Override
    public Styler styler() {
        return styler;
    }

    @Override
    public Fonter fonter() {
        return fonter;
    }

    @Override
    public Layouter layouter() { return layouter; }

    @Override
    public Workbook workbook() {
        return workbook;
    }

    @Override
    public void reset() {
        styler.reset();
    }

    @Override
    public void wirteToHttp(HttpServletResponse response, String fileName) throws IOException {
        ExcelDownloader.writeToHttp(workbook, response, fileName);
    }

    @Override
    public void wirteToLocal(String fileFullName) throws IOException {
        ExcelDownloader.wirteToLocal(workbook, fileFullName);
    }

}
