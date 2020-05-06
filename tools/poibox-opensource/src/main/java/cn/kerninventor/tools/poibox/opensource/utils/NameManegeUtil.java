package cn.kerninventor.tools.poibox.opensource.utils;

import cn.kerninventor.tools.poibox.opensource.BoxGadget;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

/**
 * @author Kern
 * @date 2019/12/13 17:08
 */
public class NameManegeUtil {

    public final static String HIDDEN_SHEET_NAME = "Pandora";

    public static String addNameManage(Sheet sheet, String nameName, List<String> datas) {
        if (datas == null || datas.isEmpty()) {
            return nameName;
        }
        Workbook workbook = sheet.getWorkbook();
        Sheet hiddenSheet = BoxGadget.getSheetForce(workbook, HIDDEN_SHEET_NAME);
        if (sheet.equals(hiddenSheet)) {
            throw new IllegalArgumentException("NameName Sheet can't to operate!");
        }
        int dataRowIndex = hiddenSheet.getLastRowNum() + 1;
        Row dataRow = hiddenSheet.createRow(dataRowIndex);

        //赋值到隐藏的字典sheet页
        for (int i = 0 ; i < datas.size(); i++){
            Cell cell = dataRow.createCell(i);
            String value = datas.get(i);
            cell.setCellValue(value);
        }

        //创建名称管理器
        Name nameManager = workbook.createName();
        nameManager.setNameName(nameName);

        //创建引用
        int dataRowSerial = dataRowIndex + 1;
        String endCellEnIndex = BoxGadget.transferExcelColumnIndex(dataRow.getLastCellNum());
        StringBuilder formulaExBulder = new StringBuilder();
        formulaExBulder
                .append(HIDDEN_SHEET_NAME)
                .append("!$A$")
                .append(dataRowSerial)
                .append(":$")
                .append(endCellEnIndex)
                .append("$")
                .append(dataRowSerial);
        nameManager.setRefersToFormula(formulaExBulder.toString());

        if (!workbook.isSheetHidden(workbook.getSheetIndex(hiddenSheet))) {
            workbook.setSheetHidden(workbook.getSheetIndex(hiddenSheet), true);
        }
        return nameName;
    }


}
