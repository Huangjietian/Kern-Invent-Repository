package cn.kerninventory.tools.excel.anno.writer;

import cn.kerninventory.tools.excel.anno.SteamTarget;
import cn.kerninventory.tools.excel.anno.performing.WritingSupervisor;
import cn.kerninventory.tools.excel.anno.reader.Reader;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public interface Writer <T> {

    Workbook getWorkbook();

    Reader<T> convert();

    <E> Writer<E> rebind(Class<E> tClass);

    Writer<T> write(WritingSupervisor supervisor);

    Writer<T> write(List<T> tList, WritingSupervisor supervisor);

    Writer<T> write(String sheetName, WritingSupervisor supervisor);

    Writer<T> write(String sheetName, List<T> tList, WritingSupervisor supervisor);

    Writer<T> output(SteamTarget target);
}
