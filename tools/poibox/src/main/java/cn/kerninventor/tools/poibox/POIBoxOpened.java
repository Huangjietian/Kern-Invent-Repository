package cn.kerninventor.tools.poibox;

import cn.kerninventor.tools.poibox.data.POIDataBox;
import cn.kerninventor.tools.poibox.data.POIDataBoxOpened;
import cn.kerninventor.tools.poibox.io.ExcelDownloader;
import cn.kerninventor.tools.poibox.layout.POILayouter;
import cn.kerninventor.tools.poibox.layout.POILayouterOpened;
import cn.kerninventor.tools.poibox.style.POIFonter;
import cn.kerninventor.tools.poibox.style.POIFonterOpened;
import cn.kerninventor.tools.poibox.style.POIStyler;
import cn.kerninventor.tools.poibox.style.POIStylerOpened;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

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

    private Map<String, POIDataBox> dataBoxMap = new HashMap<>();

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
    }

    @Override
    public POIDataBox dataBox(String sheetName) {
        POIDataBox dataBox = dataBoxMap.get(sheetName);
        if (dataBox == null){
            dataBox = new POIDataBoxOpened(this, sheetName);
            dataBoxMap.put(sheetName, dataBox);
        }
        return dataBox;
    }

    @Override
    public POIDataBox dataBoxAt(int index) {
        POIDataBox dataBox = dataBoxMap.get("index_" + index);
        if (dataBox == null){
            dataBox = new POIDataBoxOpened(this, index);
            dataBoxMap.put("index_" + index, dataBox);
        }
        return dataBox;
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
        fonter.reset();
        dataBoxMap.values().forEach(
                e -> {
                    workbook.removeSheetAt(workbook.getSheetIndex(e.getSheet()));
                }
        );
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
