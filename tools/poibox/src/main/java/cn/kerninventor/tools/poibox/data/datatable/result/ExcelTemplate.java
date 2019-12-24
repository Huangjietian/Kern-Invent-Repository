package cn.kerninventor.tools.poibox.data.datatable.result;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @Title: CompletedTemplate
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data
 * @Author Kern
 * @Date 2019/12/17 10:25
 * @Description: TODO
 */
public class ExcelTemplate {

    private Workbook workbook;
    private Sheet sheet;
    private int sheetAt;

    public ExcelTemplate(Sheet sheet) {
        this.sheet = sheet;
        workbook = sheet.getWorkbook();
        sheetAt = workbook.getSheetIndex(sheet);
    }

    public static void main(String[] args) {
        XSSFWorkbook workbook = new XSSFWorkbook();
    }
}
