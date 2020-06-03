package cn.kerninventory.tools.common.chronograph;

import com.sun.istack.internal.NotNull;

/**
 * @author Kern
 * @date 2020/6/3 16:01
 * @description
 */
public interface Chronograph {

    double click(Object marker, String message);

    double calculate(Object marker, String message);

    double calculate(Object marker, String message, int firstTime, int lastTime);

    void reset(Object marker);

    RuntimeUnit getUnit();

    default double blockCount(@NotNull Runnable runnable) {
        return runningNanoTime(runnable).doubleValue() / getUnit().getProduct();
    }

    default Long runningNanoTime(@NotNull Runnable runnable) {
        long nano1 = System.nanoTime();
        runnable.run();
        long nano2 = System.nanoTime();
        return nano2 - nano1;
    }
}