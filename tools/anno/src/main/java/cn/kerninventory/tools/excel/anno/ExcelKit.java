package cn.kerninventory.tools.excel.anno;

import cn.kerninventory.tools.excel.anno.constants.DocumentType;
import cn.kerninventory.tools.excel.anno.parser.AnnotatedClassParser;
import cn.kerninventory.tools.excel.anno.reader.Reader;
import cn.kerninventory.tools.excel.anno.writer.Writer;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public class ExcelKit {

    public static <T> Writer<T> callWriter(Class<T> tClass) {
        return callWriter(tClass, DocumentType.XLSX);
    }

    public static <T> Writer<T> callWriter(Class<T> tClass, DocumentType documentType) {
        Workbook workbook = documentType.createWorkbook();
        AnnotatedClassParser parser = AnnotatedClassParser.of(tClass);
        return null;
    }

    public static <T> Reader<T> callReader(Class<T> tClass, DocumentSource source) {
        Workbook workbook = source.getWorkbook();
        AnnotatedClassParser parser = AnnotatedClassParser.of(tClass);
        return null;
    }

}
