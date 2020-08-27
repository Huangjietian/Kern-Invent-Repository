package cn.kerninventory.tools.excel.anno.performing;

/**
 * <p>
 *     Supervisor when performing a read.
 * </p>
 *
 * @author Kern
 */
public class ReadingSupervisor extends PerformingSupervisorHolder {

    public ReadingSupervisor(PerformingSupervisor performingSupervisor) {
        super(performingSupervisor);
    }

    public WritingSupervisor convert() {
        return new WritingSupervisor(performingSupervisor);
    }
}
