package cn.kerninventory.tools.excel.anno;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public final class DocumentSource {

    private Workbook workbook;

    private DocumentSource(Workbook workbook) {
        this.workbook = workbook;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public static DocumentSource of(String filePath) throws IOException {
        return of(new File(filePath));
    }

    public static DocumentSource of(File file) throws IOException {
        return of(new FileInputStream(file));
    }

    public static DocumentSource of(InputStream inputStream) throws IOException {
        return of(WorkbookFactory.create(inputStream));
    }

    public static DocumentSource of(Workbook workbook) {
        return new DocumentSource(workbook);
    }
}
