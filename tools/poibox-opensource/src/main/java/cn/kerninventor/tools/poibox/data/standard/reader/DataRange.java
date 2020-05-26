package cn.kerninventor.tools.poibox.data.standard.reader;

import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

/**
 * @author Kern
 * @date 2020/5/22 17:27
 * @description
 */
public class DataRange {

    private CellRangeAddress cellAddresses;

    private List<DataRow> dataRows;

    public DataRange(CellRangeAddress cellAddresses) {
        this.cellAddresses = cellAddresses;
    }

    public CellRangeAddress getCellAddresses() {
        return cellAddresses;
    }

    public DataRange setCellAddresses(CellRangeAddress cellAddresses) {
        this.cellAddresses = cellAddresses;
        return this;
    }

    public List<DataRow> getDataRows() {
        return dataRows;
    }

    public DataRange setDataRows(List<DataRow> dataRows) {
        this.dataRows = dataRows;
        return this;
    }
}
