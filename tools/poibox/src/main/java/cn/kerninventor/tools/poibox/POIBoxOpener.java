package cn.kerninventor.tools.poibox;

import cn.kerninventor.tools.fileformat.FileFormatCensor;
import cn.kerninventor.tools.fileformat.enums.FileFormatEnum;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.*;

/**
 * @Title: Opener
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/29 14:18
 */
public class POIBoxOpener {

    public static POIBox open(InputStream source) throws IOException, InvalidFormatException {
        FileFormatCensor.checkE(source, "不支持的文件格式, File format error, upload xls file or xlsx file please", FileFormatEnum.XLSX_EXCEL, FileFormatEnum.XLS_EXCEL);
        return new POIBoxInner(source);
    }

    public static POIBox open(File file) throws IOException, InvalidFormatException {
        if (!file.exists() || !file.isFile()){
            throw new IllegalArgumentException("文件不存在,File does not exist");
        }
        return open(new FileInputStream(file));
    }

    public static POIBox open(String path) throws IOException, InvalidFormatException {
        return open(new File(path));
    }

    public static POIBox open(){
        return new POIBoxInner();
    }

}
