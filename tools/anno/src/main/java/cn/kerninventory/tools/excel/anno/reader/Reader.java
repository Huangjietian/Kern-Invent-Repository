package cn.kerninventory.tools.excel.anno.reader;

import cn.kerninventory.tools.excel.anno.supervisor.ReadingSupervisor;

import java.util.List;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public interface Reader {

    List read(ReadingSupervisor supervisor);
}
