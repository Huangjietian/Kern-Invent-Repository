package cn.kerninventor.tools.poibox.opensource.data.standard.reader;

/**
 * @author Kern
 * @date 2020/5/22 17:28
 * @description
 */
public class DataCell implements Comparable<DataCell>{

    private int index;

    private Object value;

    public DataCell(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public DataCell setIndex(int index) {
        this.index = index;
        return this;
    }

    public Object getValue() {
        return value;
    }

    public DataCell setValue(Object value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return "CellValue{" +
                "index=" + index +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(DataCell o) {
        if (this.index > o.index) {
            return 1;
        } else if (this.index < o.index) {
            return -1;
        }
        return 0;
    }
}
