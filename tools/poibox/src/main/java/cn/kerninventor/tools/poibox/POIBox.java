package cn.kerninventor.tools.poibox;

import cn.kerninventor.tools.fileformat.FileFormatCensor;
import cn.kerninventor.tools.fileformat.enums.FileFormatEnum;
import cn.kerninventor.tools.poibox.data.POIDataBox;
import cn.kerninventor.tools.poibox.layout.POILayouter;
import cn.kerninventor.tools.poibox.style.POIFonter;
import cn.kerninventor.tools.poibox.style.POIStyler;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Title: POIBox
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/29 19:06
 */
public interface POIBox {
    POIDataBox dataBox(String sheetName);
    POIDataBox dataBoxAt(int index);
    POIStyler styler();
    POIFonter fonter();
    POILayouter layouter();
    Workbook working();
    void reset();
    void wirteToHttp(HttpServletResponse response, String fileName) throws IOException;
    void wirteToLocal(String fileFullName) throws IOException;

    static POIBox open(InputStream source) throws IOException, InvalidFormatException {
        int available = 0;
        int time = 0;
        while (available == 0 && time < 10){
            available = source.available();
            time ++ ;
        }
        if (available <= 0){
            throw new IOException("Failed to read file stream");
        }
        byte[] bytes = new byte[available];
        source.read(bytes);
        InputStream stream1 = new ByteArrayInputStream(bytes);
        InputStream stream2 = new ByteArrayInputStream(bytes);
        FileFormatCensor.checkE(stream1, "File format error!", FileFormatEnum.XLSX_EXCEL, FileFormatEnum.XLS_EXCEL);
        return new POIBoxOpened(stream2);
    }

    static POIBox open(File file) throws IOException, InvalidFormatException {
        if (!file.exists() || !file.isFile()){
            throw new IllegalArgumentException("File does not exist!");
        }
        return open(new FileInputStream(file));
    }

    static POIBox open(String path) throws IOException, InvalidFormatException {
        return open(new File(path));
    }

    static POIBox open(){
        return new POIBoxOpened();
    }
}
