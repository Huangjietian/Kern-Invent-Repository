package cn.kerninventor.tools.poibox;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Kern
 * @date 2020/4/9 9:17
 * @description factory
 */
public final class PoiboxFactory {

    public static Poibox open(Workbook workbook) {
        return new PoiboxImplement(workbook);
    }

    public static Poibox open(InputStream source) throws IOException {
        Workbook workbook = WorkbookFactory.create(source);
        return open(workbook);
    }

    public static Poibox open(File file) throws IOException {
        if (!file.exists() || !file.isFile()){
            throw new IllegalArgumentException("File does not exist!");
        }
        return open(new FileInputStream(file));
    }

    public static Poibox open(String path) throws IOException {
        return open(new File(path));
    }

    public static Poibox open(){
        return new PoiboxImplement();
    }

}
