package cn.kerninventory.tools.excel.anno;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.*;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public class SteamSource {

    private Workbook workbook;

    private SteamSource(Workbook workbook) {
        this.workbook = workbook;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public static SteamSource of(String filePath) throws IOException {
        return of(new File(filePath));
    }

    public static SteamSource of(File file) throws IOException {
        return of(new FileInputStream(file));
    }

    public static SteamSource of(InputStream inputStream) throws IOException {
        return of(WorkbookFactory.create(inputStream));
    }

    public static SteamSource of(Workbook workbook) {
        return new SteamSource(workbook);
    }
}
