package cn.kerninventory.tools.excel.anno.constants;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * <p>
 *     Excel document type enums.
 * </p>
 *
 * @author Kern
 */
public enum  DocumentType {

    XLS( "The 1997 to 2003 edition."),
    XLSX("The edition since 2003."),
    ;

    private String description;

    DocumentType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Workbook createWorkbook() {
        if (DocumentType.XLSX == this) {
            return new XSSFWorkbook();
        } else {
            return new HSSFWorkbook();
        }
    }

    @Override
    public String toString() {
        return "DocumentType{" +
                "description='" + getDescription() + '\'' +
                '}';
    }
}
