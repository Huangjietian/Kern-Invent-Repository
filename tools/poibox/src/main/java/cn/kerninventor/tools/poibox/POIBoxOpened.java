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

    protected POIBoxOpened() {
        workbook = new HSSFWorkbook();
    }

    protected POIBoxOpened(InputStream source) throws IOException {
        workbook = WorkbookFactory.create(source);
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
    public void writeToHttp(HttpServletResponse response, String fileName) throws IOException {
        ExcelDownloader.writeToHttp(workbook, response, fileName);
    }

    @Override
    public void writeToLocal(String fileFullName) throws IOException {
        ExcelDownloader.wirteToLocal(workbook, fileFullName);
    }

}
