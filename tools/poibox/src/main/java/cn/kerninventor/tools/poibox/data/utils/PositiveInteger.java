package cn.kerninventor.tools.poibox.data.utils;

/**
 * @author Kern
 * @date 2020/4/10 9:43
 * @description
 */
public class PositiveInteger extends Number implements Comparable<PositiveInteger>{

    private int value;

    public static int mustbe(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Number must be positive integer");
        }
        return i;
    }

    public static PositiveInteger of(int i) {
        return new PositiveInteger(i);
    }
    private PositiveInteger(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Number must be positive integer");
        }
        value = i;
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public long longValue() {
        return value;
    }

    @Override
    public float floatValue() {
        return value;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    @Override
    public int compareTo(PositiveInteger o) {
        if (this.intValue() > o.intValue()) {
            return 1;
        } else if (this.intValue() < o.intValue()){
            return -1;
        }
        return 0;
    }

    public boolean gt(PositiveInteger o) {
        return compareTo(o) == 1;
    }

    public boolean lt(PositiveInteger o) {
        return compareTo(o) == -1;
    }

    public boolean equals(PositiveInteger o) {
        return compareTo(o) == 0;
    }
}
