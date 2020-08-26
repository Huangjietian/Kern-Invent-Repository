package cn.kerninventory.tools.excel.anno.performing;

/**
 * <p>
 *     Supervisor when performing write.
 * </p>
 *
 * @author Kern
 */
public interface WritingSupervisor extends PerformingSupervisor {

    ReadingSupervisor convert();
}
