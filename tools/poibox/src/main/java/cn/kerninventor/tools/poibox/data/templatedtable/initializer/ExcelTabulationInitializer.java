package cn.kerninventor.tools.poibox.data.templatedtable.initializer;

import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.data.exception.IllegalSourceClassOfTabulationException;
import cn.kerninventor.tools.poibox.data.exception.TabulationConfigureException;
import cn.kerninventor.tools.poibox.data.templatedtable.element.Banner;
import cn.kerninventor.tools.poibox.data.templatedtable.ExcelColumn;
import cn.kerninventor.tools.poibox.data.templatedtable.ExcelTabulation;
import cn.kerninventor.tools.poibox.data.templatedtable.element.Range;
import cn.kerninventor.tools.poibox.data.utils.PositiveInteger;
import cn.kerninventor.tools.poibox.developer.ReadyToDevelop;
import cn.kerninventor.tools.poibox.style.Styler;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @Author Kern
 * @Date 2019/12/9 15:25
 * @Description: excel 数据表解析处理类
 */
public class ExcelTabulationInitializer<T> extends BoxBracket {

    private Class<T> tabulationClass;

    private int startRowIndex, theadStartRowIndex;




    private int headlineRdx, tableHeadRdx, tableTextRdx;

    private int textRowNum;

    private boolean autoColumnIndex;

    private List<ExcelColumnInitializer> columnsContainer;

    public int getFirstColumnIndex() {
        return columnsContainer.get(0).getColumnIndex();
    }

    public int getLastColumnIndex() {
        return columnsContainer.get(columnsContainer.size() -1 ).getColumnIndex();
    }


    public Class<T> getTabulationClass() {
        return tabulationClass;
    }

    public int getStartRowIndex() {
        return startRowIndex;
    }

    public int getHeadlineRdx() {
        return headlineRdx;
    }


    public int getTableHeadRdx() {
        return tableHeadRdx;
    }


    public int getTableTextRdx() {
        return tableTextRdx;
    }


    public int getTextRowNum() {
        return textRowNum;
    }

    public void setTextRowNum(int textRowNum) {
        this.textRowNum = textRowNum;
    }


    public List<ExcelColumnInitializer> getColumnsContainer() {
        return columnsContainer;
    }

    public ExcelTabulationInitializer(Class<T> tableClass, POIBox poiBox) {
        super(poiBox);
        tabulationClass = dataTabulationSourceClassValidate(tableClass);
    }

    @ReadyToDevelop
    public void init(){
        //可能需要先初始化columns`
        ExcelTabulation excelTabulation = getTabulationClass().getAnnotation(ExcelTabulation.class);
        Banner[] banners = excelTabulation.banners();
        //起始行   表头行   表体行
        startRowIndex = excelTabulation.startRowIndex();
        //如果没有标题行， 表头行 == 起始行
        if (banners.length == 0) {
            theadStartRowIndex = startRowIndex;
        }





        initialzeColumns(getTabulationClass().getDeclaredFields());
        if (getColumnsContainer().size() == 0){
            throw new IllegalSourceClassOfTabulationException("Data table lack column definition, you should use @ExcelColumn to annotate object's fields!");
        }


        tableHeadRdx = startRowIndex + 1;
        tableTextRdx = startRowIndex + 2;




    }

    private void initialzeColumns(Field[] fields){
        columnsContainer = new ArrayList(fields.length);
        ExcelColumn excelColumn;
        CellStyle columnStyle = Styler.cloneStyle(getParent().workbook(), tableTextStyle);
        if (autoColumnIndex){
            int index = 0 ;
            for (Field field : fields){
                if ((excelColumn = field.getDeclaredAnnotation(ExcelColumn.class)) != null) {
                    columnsContainer.add(ExcelColumnInitializer.getInstance(field, excelColumn, index++, columnStyle));
                }
            }
        } else {
            for (Field field : fields){
                if ((excelColumn = field.getDeclaredAnnotation(ExcelColumn.class)) != null) {
                    columnsContainer.add(ExcelColumnInitializer.getInstance(field, excelColumn, excelColumn.columnIndex(), columnStyle));
                }
            }
        }
        Collections.sort(columnsContainer);
    }



    public static Class dataTabulationSourceClassValidate(Class tabulationClass) throws IllegalSourceClassOfTabulationException {
        if (tabulationClass.getAnnotation(ExcelTabulation.class) == null){
            throw new IllegalSourceClassOfTabulationException("Data tabulation POJO need to annotated @ExcelTabulation annotation!");
        }
        return tabulationClass;
    }

}
