package cn.kerninventor.tools.poibox.data.templated.initializer;

import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.data.templated.ExcelBanner;
import cn.kerninventor.tools.poibox.data.templated.ExcelColumn;
import cn.kerninventor.tools.poibox.data.templated.ExcelTabulation;
import cn.kerninventor.tools.poibox.data.templated.element.Style;
import cn.kerninventor.tools.poibox.data.templated.initializer.configuration.BannerDefinition;
import cn.kerninventor.tools.poibox.data.templated.initializer.configuration.TabConfiguration;
import cn.kerninventor.tools.poibox.exception.IllegalSourceClassOfTabulationException;
import cn.kerninventor.tools.poibox.exception.IllegalTabulationConfigureException;
import cn.kerninventor.tools.poibox.style.Styler;
import cn.kerninventor.tools.poibox.utils.BeanUtil;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Kern
 * @Date 2019/12/9 15:25
 * @Description: excel 数据表解析处理类
 */
public class ETabulationInitiator<T> extends BoxBracket implements TabConfiguration {

    private Class<T> tabulationClass;
    private Map<Integer, CellStyle> theadStyleMap;
    private Map<Integer, CellStyle> tbodyStyleMap;
    private volatile boolean initialized;
    private float theadRowHeight;
    private float tbodyRowHeight;
    private int startRowIndex;
    private int theadRowIndex;
    private int tbodyFirstRowIndex;
    private int effectiveRows;
    private int minimumColumnsWidth;
    private int maxmunColumnsWidth;
    private List<EBannerInitiator> bannerContainer = new ArrayList<>();
    private List<EColumnInitiator> columnsContainer = new ArrayList<>();

    public Class<T> getTabulationClass() {
        return tabulationClass;
    }

    public Map<Integer, CellStyle> getTheadStyleMap() {
        return theadStyleMap;
    }

    public Map<Integer, CellStyle> getTbodyStyleMap() {
        return tbodyStyleMap;
    }

    public float getTheadRowHeight() {
        return theadRowHeight;
    }

    public float getTbodyRowHeight() {
        return tbodyRowHeight;
    }

    public int getStartRowIndex() {
        return startRowIndex;
    }

    public int getEffectiveRows() {
        return effectiveRows;
    }

    public int getMinimumColumnsWidth() {
        return BoxGadget.adjustCellWidth(minimumColumnsWidth);
    }

    public int getMaxmunColumnsWidth() {
        return BoxGadget.adjustCellWidth(maxmunColumnsWidth);
    }

    public int getTheadRowIndex() {
        return theadRowIndex;
    }

    public int getTbodyFirstRowIndex(){
        return tbodyFirstRowIndex;
    }

    public List<EBannerInitiator> getBannerContainer() {
        return bannerContainer;
    }

    public List<EColumnInitiator> getColumnsContainer() {
        return columnsContainer;
    }

    public EColumnInitiator getColumnInitializerByTitleName(final String fieldName) {
        return columnsContainer.stream().filter(e -> e.getFieldName().equals(fieldName)).findFirst().get();
    }
    public ETabulationInitiator(Class<T> tableClass, POIBox poiBox) {
        super(poiBox);
        this.tabulationClass = tableClass;
    }

    public synchronized void init(){
        if (initialized) {
            return;
        }
        initialized = true;
        ExcelTabulation excelTabulation = dataTabulationSourceClassValidate(getTabulationClass());
        //初始化风格
        this.theadStyleMap = initStyles(excelTabulation.theadStyles(), getParent().styler());
        this.tbodyStyleMap = initStyles(excelTabulation.tbodyStyles(), getParent().styler());
        //行高
        this.theadRowHeight = excelTabulation.theadRowHeight();
        this.tbodyRowHeight = excelTabulation.tbodyRowHeight();
        //起始行和有效行数
        this.startRowIndex = startRowIndexNotMinus(excelTabulation.startRowIndex());
        this.effectiveRows = effectiveRowsNotLessThan1(excelTabulation.effectiveRows());
        //最小列宽和最大列宽
        this.minimumColumnsWidth = excelTabulation.minimumColumnsWidth();
        this.maxmunColumnsWidth = excelTabulation.maximumColumnsWidth();
        this.bannerContainer.addAll(initialzeBanners(excelTabulation.banners()));
        this.columnsContainer.addAll(initialzeColumns(getTabulationClass().getDeclaredFields(), getTheadStyleMap(), getTbodyStyleMap()));
        this.theadRowIndex = getRowIndexIncrementsByBanners(bannerContainer) + startRowIndex;
        this.tbodyFirstRowIndex = theadRowIndex + 1;
    }

    private Map<Integer, CellStyle> initStyles(Style[] styles, Styler styler) {
        Map<Integer, CellStyle> styleMap = new HashMap<>();
        for (Style style : styles) {
            styleMap.put(style.index(), styler.generate(style));
        }
        return styleMap;
    }

    private int startRowIndexNotMinus(int startRowIndex) {
        if (startRowIndex < 0)
            throw new IllegalTabulationConfigureException("StartRowIndex must be positive integer!");
        return startRowIndex;
    }

    private int effectiveRowsNotLessThan1(int effectiveRows) {
        if (effectiveRows < 1)
            throw new IllegalTabulationConfigureException("EffectiveRows must be great than 1!");
        return effectiveRows;
    }

    private List<EBannerInitiator> initialzeBanners(ExcelBanner... banners) {
        return Arrays.stream(banners).map(e -> new EBannerInitiator(this, e)).collect(Collectors.toList());
    }

    private int getRowIndexIncrementsByBanners(List<EBannerInitiator> bannerContainer) {
        if (BeanUtil.isEmpty(bannerContainer)) {
            return 0;
        }
        int incremental = 1;
        for (EBannerInitiator bannerInitializer : bannerContainer) {
            if (bannerInitializer.getLastRowIndex() >= incremental) {
                incremental = bannerInitializer.getLastRowIndex() + 1;
            }
        }
        return incremental;
    }

    private List<EColumnInitiator> initialzeColumns(Field[] fields, Map<Integer, CellStyle> theadStyleMap, Map<Integer, CellStyle> tbodyStyleMap){
        List<EColumnInitiator> columnsContainer = new ArrayList(fields.length);
        Set<String> columnNameSet = new HashSet<>(fields.length);
        ExcelColumn excelColumn;
        for (Field field : fields){
            if ((excelColumn = field.getDeclaredAnnotation(ExcelColumn.class)) != null) {
                CellStyle theadStyle = theadStyleMap.get(excelColumn.theadStyleIndex());
                CellStyle tbodyCellStyle = tbodyStyleMap.get(excelColumn.tbodyStyleIndex());
                EColumnInitiator columnInitializer = new EColumnInitiator(field, excelColumn, theadStyle, tbodyCellStyle);
                columnsContainer.add(columnInitializer);
                columnNameSet.add(columnInitializer.getTitleName());
            }
        }
        if (columnsContainer.size() == 0){
            throw new IllegalSourceClassOfTabulationException("Data table lack column definition, you should use @ExcelColumn to annotate object's fields!");
        }
        if (columnNameSet.size() != columnsContainer.size()) {
            throw new IllegalTabulationConfigureException("Column title name must be unique!");
        }
        Collections.sort(columnsContainer);
        setColumnsIndex(columnsContainer);
        return columnsContainer;
    }

    public void setColumnsIndex(List<EColumnInitiator> columnsContainer) {
        for (int i = 0 ; i < columnsContainer.size() ; i ++) {
            columnsContainer.get(i).setColumnIndex(i);
        }
    }

    public static ExcelTabulation dataTabulationSourceClassValidate(Class tabulationClass) throws IllegalSourceClassOfTabulationException {
        ExcelTabulation excelTabulation;
        if ( (excelTabulation = (ExcelTabulation) tabulationClass.getDeclaredAnnotation(ExcelTabulation.class)) == null){
            throw new IllegalSourceClassOfTabulationException("Data tabulation POJO need to annotated @ExcelTabulation annotation!");
        }
        return excelTabulation;
    }

    @Override
    public TabConfiguration addBanner(String value, CellStyle cellStyle, CellRangeAddress cellRangeAddress) {
        EBannerInitiator bannerInitiator = new EBannerInitiator(cellStyle, cellRangeAddress, value);
        bannerContainer.add(bannerInitiator);
        return this;
    }

    @Override
    public TabConfiguration addBanner(String value, CellStyle cellStyle, int row1, int row2, int col1, int col2) {
        CellRangeAddress cellRangeAddress = new CellRangeAddress(row1,row2,col1,col2);
        EBannerInitiator bannerInitiator = new EBannerInitiator(cellStyle, cellRangeAddress, value);
        bannerContainer.add(bannerInitiator);
        return this;
    }

    @Override
    public List<? extends BannerDefinition> getBanners() {
        return this.bannerContainer;
    }
}
