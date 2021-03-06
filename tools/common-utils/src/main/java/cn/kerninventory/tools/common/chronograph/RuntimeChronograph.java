package cn.kerninventory.tools.common.chronograph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <h1>中文注释</h1>
 * <p>
 *     代码运行时间计时器基础实现
 * </p>
 * @author Kern
 * @version 1.0
 */
public class RuntimeChronograph implements Chronograph {

    private RuntimeUnit unit;
    private ConcurrentMap<Object, List<Long>> recordConcurrentMap;

    public RuntimeChronograph(RuntimeUnit unit) {
        this.unit = unit;
        this.recordConcurrentMap = new ConcurrentHashMap<>(16);
    }

    @Override
    public double click(String message) {
        return click(this, message);
    }

    @Override
    public double calculate(String message) {
        return calculate(this, message);
    }

    @Override
    public double calculate(String message, int firstTime, int lastTime) {
        return calculate(this, message, firstTime, lastTime);
    }

    @Override
    public double click(Object marker, String message) {
        List<Long> times = recordConcurrentMap.computeIfAbsent(marker, k -> new ArrayList<>(16));
        times.add(System.nanoTime());
        if (times.size() == 1) {
            return -1.00d;
        }
        return formula(times.get(times.size() -2), times.get(times.size() -1));
    }

    @Override
    public double calculate(Object marker, String message) {
        List<Long> times = recordConcurrentMap.get(marker);
        if (times == null || times.isEmpty()) {
            return 0.00d;
        }
        long nano1 = times.get(0);
        return formula(nano1, System.nanoTime());
    }

    @Override
    public double calculate(Object marker, String message, int firstTime, int lastTime) {
        List<Long> times = recordConcurrentMap.get(marker);
        if (firstTime >= lastTime || times == null || times.isEmpty() || times.size() < lastTime) {
            return 0.00d;
        }
        return formula(times.get(firstTime), times.get(lastTime));
    }

    public void reset(Object marker) {
        recordConcurrentMap.remove(marker);
    }

    @Override
    public void clear() {
        recordConcurrentMap.clear();
    }

    @Override
    public RuntimeUnit getUnit() {
        return unit;
    }

    private double formula(Long nano1, Long nano2) {
        Objects.requireNonNull(nano1);
        Objects.requireNonNull(nano2);
        Long diff = nano2 - nano1;
        return diff.doubleValue() / getUnit().getProduct();
    }

}
