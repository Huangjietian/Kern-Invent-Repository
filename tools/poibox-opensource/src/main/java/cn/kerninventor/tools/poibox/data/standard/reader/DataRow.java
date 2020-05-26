package cn.kerninventor.tools.poibox.data.standard.reader;

import java.util.List;

/**
 * @author Kern
 * @date 2020/5/22 17:29
 * @description
 */
public class DataRow implements Comparable<DataRow> {

    private int index;

    private List<DataCell> dataCells;

    public DataRow(int index) {
        this.index = index;
    }

    public List<DataCell> getDataCells() {
        return dataCells;
    }

    public DataRow setDataCells(List<DataCell> dataCells) {
        this.dataCells = dataCells;
        return this;
    }

    @Override
    public int compareTo(DataRow o) {
        if (this.index > o.index) {
            return 1;
        } else if (this.index < o.index) {
            return -1;
        }
        return 0;
    }
}
