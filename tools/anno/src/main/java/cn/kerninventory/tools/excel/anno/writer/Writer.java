package cn.kerninventory.tools.excel.anno.writer;

import cn.kerninventory.tools.excel.anno.supervisor.WritingSupervisor;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public interface Writer {

    Writer write(String sheetName, WritingSupervisor supervisor);

    /**
     * 表示任务的结束
     */
    void flush();
}
