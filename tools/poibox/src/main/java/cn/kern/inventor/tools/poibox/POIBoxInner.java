package cn.kern.inventor.tools.poibox;

import cn.kern.inventor.tools.poibox.layout.POILayouter;
import cn.kern.inventor.tools.poibox.layout.POILayouterInner;
import cn.kern.inventor.tools.poibox.style.POIFonter;
import cn.kern.inventor.tools.poibox.style.POIFonterInner;
import cn.kern.inventor.tools.poibox.style.POIStyler;
import cn.kern.inventor.tools.poibox.style.POIStylerInner;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Objects;

/**
 * @Title: POIBox
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/29 14:16
 */
public class POIBoxInner implements POIBox {

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
        layouter = new POILayouterInner();
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
