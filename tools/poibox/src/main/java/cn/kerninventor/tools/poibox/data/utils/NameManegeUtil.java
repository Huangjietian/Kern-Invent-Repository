package cn.kerninventor.tools.poibox.data.utils;

import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @Title: NameNameManegeUtil
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.nameName
 * @Author Kern
 * @Date 2019/12/13 17:08
 * @Description: TODO
 */
public class NameManegeUtil {

    public static String addNameManage(Sheet sheet, String hiddenSheetName, String nameName, String nameNameUKEY, String[] datas) {
        String UKEY = nameNameUKEY == null ? "" : nameNameUKEY;
        //创建放置名称管理器数据的页签
        Workbook wb = sheet.getWorkbook();
        Sheet hiddenSheet = wb.getSheet(hiddenSheetName) == null ? wb.createSheet(hiddenSheetName) : wb.getSheet(hiddenSheetName);
        if (datas == null || datas.length == 0){
            datas[0] = "NO DATA";
        }
        int dataRowNum = hiddenSheet.getLastRowNum()+1;
        Row dataRow = hiddenSheet.createRow(dataRowNum);
        for (int i = 0 ; i < datas.length; i++){
            dataRow.createCell(i).setCellValue(datas[i]);
        }
        //创建名称管理器
        Name nameManager = wb.createName();
        nameManager.setNameName(UKEY + nameName);
        //创建引用
        String endCellEnIndex = CellValueUtil.TransferExcelColumnIndex(dataRow.getLastCellNum());
        nameManager.setRefersToFormula(hiddenSheetName+"!$A$"+(dataRowNum+1)+":$"+endCellEnIndex+"$"+(dataRowNum+1));
        return nameName;
    }
}
