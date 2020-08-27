package cn.kerninventory.tools.excel.anno.performing;

/**
 * <p>
 *     Supervisor when performing write.
 * </p>
 *
 * @author Kern
 */
public class WritingSupervisor extends PerformingSupervisorHolder {

    public WritingSupervisor(PerformingSupervisor performingSupervisor) {
        super(performingSupervisor);
    }

    public ReadingSupervisor convert() {
        return new ReadingSupervisor(performingSupervisor);
    }

}
