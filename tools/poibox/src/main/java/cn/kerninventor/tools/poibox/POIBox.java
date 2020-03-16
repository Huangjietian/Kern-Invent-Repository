package cn.kerninventor.tools.poibox;

import cn.kerninventor.tools.fileformat.FileFormatCensor;
import cn.kerninventor.tools.fileformat.enums.FileFormatEnum;
import cn.kerninventor.tools.poibox.data.DataTabulator;
import cn.kerninventor.tools.poibox.layout.Layouter;
import cn.kerninventor.tools.poibox.style.Fonter;
import cn.kerninventor.tools.poibox.style.Styler;
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

    /**
     * 数据表格
     * @return
     */
    DataTabulator dataTabulator();

    /**
     * 风格
     * @return
     */
    Styler styler();

    /**
     * 字体
     * @return
     */
    Fonter fonter();

    /**
     * 布局
     * @return
     */
    Layouter layouter();

    /**
     * 返回Workbook 对象
     * @return
     */
    Workbook workbook();

    /**
     * 把文件流写入 HttpServletResponse
     * @param response
     * @param fileName
     * @throws IOException
     */
    void writeToHttp(HttpServletResponse response, String fileName) throws IOException;

    /**
     * 根据本地路径创建EXCEL文件
     * @param fileFullName  文件的完全限定名
     * @throws IOException
     */
    void writeToLocal(String fileFullName) throws IOException;

    /**
     * 工厂方法，根据输入流创建
     * @param source
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    static POIBox open(InputStream source) throws IOException {
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

    /**
     * 工厂方法，根据File实例创建
     * @param file
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    static POIBox open(File file) throws IOException, InvalidFormatException {
        if (!file.exists() || !file.isFile()){
            throw new IllegalArgumentException("File does not exist!");
        }
        return open(new FileInputStream(file));
    }

    /**
     * 工厂方法，根据本地文件路径创建
     * @param path
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    static POIBox open(String path) throws IOException, InvalidFormatException {
        return open(new File(path));
    }

    /**
     * 工厂类，创建
     * @return
     */
    static POIBox open(){
        return new POIBoxOpened();
    }
}
