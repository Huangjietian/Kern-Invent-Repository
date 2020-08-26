package cn.kerninventory.tools.excel.anno.reader;

import cn.kerninventory.tools.excel.anno.performing.ReadingSupervisor;
import cn.kerninventory.tools.excel.anno.writer.Writer;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public interface Reader <T> {

    Workbook getWorkbook();

    Writer<T> convert();

    <E> Reader<E> rebind(Class<E> tClass);

    Reader<T> read(ReadingSupervisor supervisor, ReadResult<T> readResult);

    Reader<T> read(int sheetAt, ReadingSupervisor supervisor, ReadResult<T> readResult);

    Reader<T> read(String sheetName, ReadingSupervisor supervisor, ReadResult<T> readResult);


}
