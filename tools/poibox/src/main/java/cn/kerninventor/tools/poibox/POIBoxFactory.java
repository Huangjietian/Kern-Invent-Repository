package cn.kerninventor.tools.poibox;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Kern
 * @date 2020/4/9 9:17
 * @description factory
 */
public final class POIBoxFactory {

    public static POIBox open(InputStream source) throws IOException {
//        byte[] bytes = new byte[source.available()];
//        source.read(bytes);
//        //FIXME 把文件头校验直接集成到POIBOX中，使工具具备完全的执行能力
//        FileFormatCensor.checkE(new ByteArrayInputStream(bytes), "File format error!", FileFormatEnum.XLSX_EXCEL, FileFormatEnum.XLS_EXCEL);
        return new POIBoxOpened(source);
    }

    public static POIBox open(File file) throws IOException, InvalidFormatException {
        if (!file.exists() || !file.isFile()){
            throw new IllegalArgumentException("File does not exist!");
        }
        return open(new FileInputStream(file));
    }

    public static POIBox open(String path) throws IOException, InvalidFormatException {
        return open(new File(path));
    }

    public static POIBox open(){
        return new POIBoxOpened();
    }

}
