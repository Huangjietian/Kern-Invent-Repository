package cn.kerninventory.tools.excel.anno.reader;

import cn.kerninventory.tools.excel.anno.writer.Writer;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public interface Reader<T> {

    Writer<T> convert();

//    Reader<T> read(ReadingSupervisor supervisor, ReadResult<T> readResult);
//
//    Reader<T> read(int sheetAt, ReadingSupervisor supervisor, ReadResult<T> readResult);
//
//    Reader<T> read(String sheetName, ReadingSupervisor supervisor, ReadResult<T> readResult);

    ReadBreakpoint breakOff();
}
