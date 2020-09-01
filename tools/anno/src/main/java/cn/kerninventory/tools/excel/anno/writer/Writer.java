package cn.kerninventory.tools.excel.anno.writer;

import cn.kerninventory.tools.excel.anno.Appender;
import cn.kerninventory.tools.excel.anno.reader.Reader;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public interface Writer<T> extends WriteBreakpoint {

    /**
     * 是否在写入时覆盖已有的sheet页
     * true -> 删除重名的sheet页，重新创建
     * false -> throw new Exception();
     * @param overwrite
     * @return
     */
    Writer<T> overwrite(boolean overwrite);

    Reader<T> convert();

//    Writer<T> write(WritingSupervisor supervisor);
//
//    Writer<T> write(List<T> tList, WritingSupervisor supervisor);
//
//    Writer<T> write(String sheetName, WritingSupervisor supervisor);
//
//    Writer<T> write(String sheetName, List<T> tList, WritingSupervisor supervisor);

    WriteBreakpoint output(Appender... appenderArr);



}
