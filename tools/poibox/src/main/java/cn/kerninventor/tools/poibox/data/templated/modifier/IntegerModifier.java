package cn.kerninventor.tools.poibox.data.templated.modifier;


import org.apache.poi.ss.usermodel.Cell;

/**
 * @author Kern
 * @date 2020/4/14 18:48
 * @description
 */
public class IntegerModifier implements CellModifier<Integer> {
    @Override
    public void set(Cell cell, Integer integer) {
        cell.setCellValue(integer);
    }

    @Override
    public Integer get(Cell cell) {
        Double d = cell.getNumericCellValue();
        return d.intValue();
    }
}
