package cn.kerninventor.tools.poibox;

import cn.kerninventor.tools.poibox.layout.POILayouter;
import cn.kerninventor.tools.poibox.layout.POILayouterInner;
import cn.kerninventor.tools.poibox.style.POIFonter;
import cn.kerninventor.tools.poibox.style.POIFonterInner;
import cn.kerninventor.tools.poibox.style.POIStyler;
import cn.kerninventor.tools.poibox.style.POIStylerInner;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Title: POIBox
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/29 14:16
 */
public final class POIBoxInner implements POIBox {

    private Workbook workbook;

    private POIStyler styler;

    private POIFonter fonter;

    private POILayouter layouter;


    protected POIBoxInner() {
        workbook = new HSSFWorkbook();
        init();
    }

    protected POIBoxInner(InputStream source) throws IOException, InvalidFormatException {
        workbook = WorkbookFactory.create(source);
        init();
    }

    private void init(){
        styler = new POIStylerInner(this);
        fonter = new POIFonterInner(this);
        layouter = new POILayouterInner(this);
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
    public POILayouter layouter() {return layouter; }

    @Override
    public Workbook working() {
        return workbook;
    }
}
