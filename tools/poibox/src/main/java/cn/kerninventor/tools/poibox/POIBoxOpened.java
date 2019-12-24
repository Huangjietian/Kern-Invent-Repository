package cn.kerninventor.tools.poibox;

import cn.kerninventor.tools.poibox.data.POIDataProcessor;
import cn.kerninventor.tools.poibox.data.POIDataProcessorOpened;
import cn.kerninventor.tools.poibox.layout.POILayouter;
import cn.kerninventor.tools.poibox.layout.POILayouterOpened;
import cn.kerninventor.tools.poibox.style.POIFonter;
import cn.kerninventor.tools.poibox.style.POIFonterOpened;
import cn.kerninventor.tools.poibox.style.POIStyler;
import cn.kerninventor.tools.poibox.style.POIStylerOpened;
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

    private POIStyler styler;

    private POIFonter fonter;

    private POILayouter layouter;

    private POIDataProcessor dataProcessor;

    protected POIBoxOpened() {
        workbook = new HSSFWorkbook();
        init();
    }

    protected POIBoxOpened(InputStream source) throws IOException, InvalidFormatException {
        workbook = WorkbookFactory.create(source);
        init();
    }

    private void init(){
        styler = new POIStylerOpened(this);
        fonter = new POIFonterOpened(this);
        layouter = new POILayouterOpened(this);
        dataProcessor = new POIDataProcessorOpened(this);
    }

    @Override
    public POIDataProcessor dataProcessor() {
        return dataProcessor;
    }

    @Override
    public POIStyler styler() {
        return styler;
    }

    @Override
    public POIFonter fonter() {
        return fonter;
    }

    @Override
    public POILayouter layouter() { return layouter; }

    @Override
    public Workbook working() {
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
