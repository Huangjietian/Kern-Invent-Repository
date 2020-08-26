package cn.kerninventory.tools.excel.anno.performing;

/**
 * <p>
 *     Supervisor when performing a read.
 * </p>
 *
 * @author Kern
 */
public interface ReadingSupervisor extends PerformingSupervisor {

    WritingSupervisor convert();
}
