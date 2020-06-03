package cn.kerninventory.tools.common.chronograph;

/**
 * @author Kern
 * @date 2020/6/3 16:07
 * @description
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
