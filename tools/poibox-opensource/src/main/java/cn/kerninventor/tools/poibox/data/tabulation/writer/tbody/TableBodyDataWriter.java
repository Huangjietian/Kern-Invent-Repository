package cn.kerninventor.tools.poibox.data.tabulation.writer.tbody;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.tabulation.context.ClassFileColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.context.ClassFileTableContext;
import cn.kerninventor.tools.poibox.data.tabulation.translator.AbstractColumnDataTranslator;
import cn.kerninventor.tools.poibox.data.tabulation.writer.tbody.col.ColWriter;
import cn.kerninventor.tools.poibox.utils.ReflectUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @author Kern
 * @date 2020/5/25 14:19
 * @description
 */
public final class TableBodyDataWriter<T> implements TbodyWriter<T> {

    private List<T> datas;
    private AbstractColumnDataTranslator translator;

    public TableBodyDataWriter(List<T> datas, AbstractColumnDataTranslator AbstractColumnDataTranslator) {
        this.datas = datas;
        this.translator = AbstractColumnDataTranslator;
    }

    @Override
    public void templateTbody(ClassFileColumnDefinition<T> classFileColumnDefinition, Sheet sheet) {
        ClassFileTableContext classFileTableContext = classFileColumnDefinition.getClassFileTableContext();
        ColWriter colWriter = classFileColumnDefinition.getColWriter();
        colWriter.pre();
        for (int datasIndex = 0, rowIndex = classFileTableContext.getTbodyFirstRowIndex(); datasIndex < datas.size() ; datasIndex ++ , rowIndex++){
            Row bodyRow = BoxGadget.getRowForce(sheet, rowIndex);
            bodyRow.setHeightInPoints(classFileTableContext.getTbodyRowHeight());
            Cell bodyCell = bodyRow.createCell(classFileColumnDefinition.getColumnIndex());
            //设置风格
            bodyCell.setCellStyle(classFileColumnDefinition.getTbodyStyle());
            Object value = null;
            try {
                value = ReflectUtil.getFieldValue(classFileColumnDefinition.getField(), datas.get(datasIndex));
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("Failed to get field value. Field name: " + classFileColumnDefinition.getFieldName());
            }
            value = this.translator.translate(classFileColumnDefinition.getColumnDataTranslate(), value);
            colWriter.setCellValue(bodyCell, value);

        }
        colWriter.flush();
    }
//
//    private void preNumberStatistics() {
//        //大致思路， 在获得字段值后 收集到 数组中
//    }
//
//    private void writeNumberStatistics() {
//        //大致思路， 对收集的结果进行汇总计算。
//    }
//    private class StatisticsTask {
//
//        private Map<String, List<Number>> columnsNumberValue;
//        private NumberStatisticsLogicHandler handler;
//        private NumberStatisticsMode mode;
//
//        public StatisticsTask(String[] fieldsName, NumberStatisticsMode mode) {
//            columnsNumberValue = new HashMap<>(fieldsName.length);
//            for (String fieldName : fieldsName) {
//                columnsNumberValue.put(fieldName, new ArrayList<>(16));
//            }
//            handler = new NumberStatisticsLogicHandler();
//        }
//
//        public void collect(String fieldName, Number number) {
//            columnsNumberValue.get(fieldName).add(number);
//        }
//
//        public Number doStatistics(String fieldName){
//            List<Number> numberList = columnsNumberValue.get(fieldName);
//            if (BeanUtil.isEmpty(numberList)) {
//                return 0;
//            }
//            return handler.toStatistics(mode, numberList.toArray(new Number[numberList.size()]));
//        }
//    }
}
