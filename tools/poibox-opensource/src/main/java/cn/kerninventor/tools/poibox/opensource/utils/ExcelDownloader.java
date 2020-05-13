package cn.kerninventor.tools.poibox.opensource.utils;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @duthor Kern
 * @date 2019/12/6 10:21
 */
public class ExcelDownloader {

    /**
     * @param wb
     * @param response servlet响应对象
     * @param fileName 预设的文件名
     */
    public static void writeToHttp(Workbook wb, HttpServletResponse response, String fileName) throws IOException {
        String fileSuffix = FileFormatEnum.XLS_EXCEL.getSuffix();
        if (wb instanceof XSSFWorkbook) {
            fileSuffix = FileFormatEnum.XLSX_EXCEL.getSuffix();
        }
        OutputStream out = null;
        try {
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            response.reset();
            response.setContentType("application/vnd.ms-excel");
            if (fileName != null && !"".equals(fileName)){
                response.addHeader("Content-Disposition", "attachment;filename=" + fileName + fileSuffix);
            }
            response.addHeader("charset", "UTF-8");
            response.addHeader("Access-Control-Allow-Origin", "*");
            out = response.getOutputStream();
            wb.write(out);
            out.flush();
        } catch (IOException e) {
            throw e;
        } finally {
            out.close();
        }

    }

    /**
     * @param wb
     * @param fileFullName
     * @throws IOException
     */
    public static void wirteToLocal(Workbook wb, String fileFullName) throws IOException {
        String fileSuffix = FileFormatEnum.XLS_EXCEL.getSuffix();
        if (wb instanceof XSSFWorkbook) {
            fileSuffix = FileFormatEnum.XLSX_EXCEL.getSuffix();
        }
        fileFullName = fileFullName.substring(0, fileFullName.lastIndexOf(".")) + fileSuffix;
        FileOutputStream os = null;
        try {
            File file = createNewFile(fileFullName);
            os = new FileOutputStream(file);
            wb.write(os);
            os.flush();
        } catch (IOException e) {
            throw e;
        } finally {
            if (os != null){
                os.close();
            }
        }
    }

    public static File createNewFile(String filePath) throws IOException {
        File parent = new File(filePath.substring(0, filePath.lastIndexOf(File.separator)));
        if (parent == null || !parent.exists()){
            parent.mkdirs();
        }
        File file = new File(filePath);
        file.createNewFile();
        return file;
    }
}
