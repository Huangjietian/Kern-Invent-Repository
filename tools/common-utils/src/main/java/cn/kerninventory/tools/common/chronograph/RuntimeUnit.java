package cn.kerninventory.tools.common.chronograph;

/**
 * <h1>中文注释</h1>
 * <p>
 *     运行时间单位枚举类
 * </p>
 * @author Kern
 * @version 1.0
 */
public enum RuntimeUnit {

    Nanoseconds(1),
    Microsecond(1000 * RuntimeUnit.Nanoseconds.getProduct()),
    Millisecond(1000 * RuntimeUnit.Microsecond.getProduct()),
    Second(1000 * RuntimeUnit.Millisecond.getProduct()),
    Minute(60 * RuntimeUnit.Second.getProduct()),
    Hour(60 * RuntimeUnit.Minute.getProduct())
    ;

    RuntimeUnit(long product) {
        this.product = product;
    }

    private long product;

    public long getProduct() {
        return product;
    }

}
