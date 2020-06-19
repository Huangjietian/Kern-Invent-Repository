package cn.kerninventor.tools.poibox.data.tabulation.context;

import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.Poibox;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelBanner;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelColumn;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelTabulation;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.Style;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.Textbox;
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
 * @author Kern
 * @date 2019/12/9 15:25
 */
public class TabulationBeanConfiguration<T> extends BoxBracket implements TabulationConfiguration {

    private Class<T> tabulationClass;
    private Map<Integer, CellStyle> theadStyleMap;
    private Map<Integer, CellStyle> tbodyStyleMap;
    private List<BannerDefinition> bannerDefinitions;
    private List<ColumnDefinition> columnDefinitions;
    private float theadRowHeight;
    private float tbodyRowHeight;
    private int maximumColumnsWidth;
    private int minimumColumnsWidth;
    private int startRowIndex;
    private int effectiveRows;
    private Textbox[] textnodes;

    public TabulationBeanConfiguration(Class<T> tableClass, Poibox poiBox) {
        super(poiBox);
        this.tabulationClass = tableClass;
        this.initialze();
    }

    private void initialze(){
        Class tabulationClass = getTabulationClass();
        ExcelTabulation excelTabulation = dataTabulationSourceClassValidate(tabulationClass);
        this.theadStyleMap = initialzeStyles(excelTabulation.theadStyles());
        this.tbodyStyleMap = initialzeStyles(excelTabulation.tbodyStyles());
        this.bannerDefinitions = initialzeBanners(excelTabulation.banners());
        this.columnDefinitions = initialzeColumns(tabulationClass);
        this.startRowIndex = excelTabulation.startRowIndex();
        this.theadRowHeight = excelTabulation.theadRowHeight();
        this.tbodyRowHeight = excelTabulation.tbodyRowHeight();
        this.effectiveRows = excelTabulation.effectiveRows();
        this.maximumColumnsWidth = excelTabulation.maximumColumnsWidth();
        this.minimumColumnsWidth = excelTabulation.minimumColumnsWidth();
        this.textnodes = excelTabulation.textboxes();
    }

    private Map<Integer, CellStyle> initialzeStyles(Style[] styles) {
        Styler styler = getParent().styler();
        Map<Integer, CellStyle> styleMap = new HashMap<>();
        for (Style style : styles) {
            styleMap.put(style.index(), styler.generate(style));
        }
        return styleMap;
    }

    private List<BannerDefinition> initialzeBanners(ExcelBanner... banners) {
        return Arrays.stream(banners).map(e -> new BannerDefinition(this, e)).collect(Collectors.toList());
    }

    private List<ColumnDefinition> initialzeColumns(Class tabulationClass){
        Field[] fields = tabulationClass.getDeclaredFields();
        List<ColumnDefinition> columnDefinitions = new ArrayList<>(fields.length);
        Set<String> columnNameSet = new HashSet<>(fields.length);
        ExcelColumn excelColumn;
        for (Field field : fields){
            if ((excelColumn = field.getDeclaredAnnotation(ExcelColumn.class)) != null) {
                ColumnDefinition columnInitializer = new ColumnDefinition(field, excelColumn, this);
                columnDefinitions.add(columnInitializer);
                columnNameSet.add(columnInitializer.getTitleName());
            }
        }
        if (columnDefinitions.size() == 0){
            throw new IllegalSourceClassOfTabulationException("Data table lack column definition, you should use @ExcelColumn to annotate object's field!");
        }
        if (columnNameSet.size() != columnDefinitions.size()) {
            throw new IllegalTabulationConfigureException("Column title name must be unique!");
        }
        TabulationConfiguration.setColumnsIndexBySort(columnDefinitions);
        return columnDefinitions;
    }

    private int getRowIndexIncrementsByBanners(List<BannerDefinition> bannerDefinitions) {
        if (BeanUtil.isEmpty(bannerDefinitions)) {
            return 0;
        }
        int maximum = bannerDefinitions.stream().mapToInt(BannerDefinition::getLastRowIndex).max().getAsInt();
        if (maximum > 0) {
            return ++maximum;
        }
        return 1;
    }

    private ExcelTabulation dataTabulationSourceClassValidate(Class tabulationClass) throws IllegalSourceClassOfTabulationException {
        ExcelTabulation excelTabulation;
        if ( (excelTabulation = (ExcelTabulation) tabulationClass.getDeclaredAnnotation(ExcelTabulation.class)) == null){
            throw new IllegalSourceClassOfTabulationException("Data tabulation POJO need to annotated @ExcelTabulation annotation!");
        }
        startRowIndexValidate(excelTabulation.startRowIndex());
        effectiveRowsValidate(excelTabulation.effectiveRows());
        rowHeightValidate(excelTabulation.tbodyRowHeight());
        rowHeightValidate(excelTabulation.theadRowHeight());
        minimumColumnsWidthValidate(excelTabulation.minimumColumnsWidth());
        maximumColumnsWidthValidate(excelTabulation.maximumColumnsWidth(), excelTabulation.minimumColumnsWidth());
        return excelTabulation;
    }

    private void startRowIndexValidate(int startRowIndex) {
        isIllegalTabulation(startRowIndex < 0, "StartRowIndex must be greater than or equal than 0!");
    }

    private void effectiveRowsValidate(int effectiveRows) {
        isIllegalTabulation(effectiveRows < 1, "EffectiveRows must be greater than or equal than 1!");
    }

    private void rowHeightValidate(float rowHeight) {
        isIllegalTabulation(rowHeight <= 0.0f, "Row height must be greater than 0.0!");
    }

    private void minimumColumnsWidthValidate(int minmunColumnsWidth) {
        isIllegalTabulation(minmunColumnsWidth < 0, "MinimumColumnsWidth must be greater than or equal than 0!");
    }

    private void maximumColumnsWidthValidate(int maxmunColumnsWidth, int minmunColumnsWidth) {
        isIllegalTabulation(maxmunColumnsWidth <= 0 || maxmunColumnsWidth < minmunColumnsWidth, "MaximumColumnsWidth must be greater than 0 and greater than minimumColumnsWidth!");
    }

    private void isIllegalTabulation(boolean isIllegal, String errorMsg) {
        if (isIllegal)
            throw new IllegalTabulationConfigureException(errorMsg);
    }

    @Override
    public Class<T> getTabulationClass() {
        return tabulationClass;
    }

    @Override
    public Map<Integer, CellStyle> getTheadStyleMap() {
        return theadStyleMap;
    }

    @Override
    public Map<Integer, CellStyle> getTbodyStyleMap() {
        return tbodyStyleMap;
    }

    @Override
    public float getTheadRowHeight() {
        return theadRowHeight;
    }

    @Override
    public float getTbodyRowHeight() {
        return tbodyRowHeight;
    }

    @Override
    public int getStartRowIndex() {
        return startRowIndex;
    }

    @Override
    public int getEffectiveRows() {
        return effectiveRows;
    }

    @Override
    public int getMaximumColumnsWidth() {
        return BoxGadget.adjustCellWidth(maximumColumnsWidth);
    }

    @Override
    public int getMinimumColumnsWidth() {
        return BoxGadget.adjustCellWidth(minimumColumnsWidth);
    }

    @Override
    public int getTheadRowIndex() {
        return getRowIndexIncrementsByBanners(getBannerDefinitions()) + getStartRowIndex();
    }

    @Override
    public int getTbodyFirstRowIndex(){
        return getTheadRowIndex() + 1;
    }

    @Override
    public List<BannerDefinition> getBannerDefinitions() {
        return bannerDefinitions;
    }

    @Override
    public List<ColumnDefinition> getColumnDefinitions() {
        return columnDefinitions;
    }

    @Override
    public Textbox[] getTextnodes() {
        return textnodes;
    }

    @Override
    public TabulationConfiguration addBanner(String value, CellStyle cellStyle, int row1, int row2) {
        return addBanner(value, cellStyle, row1, row2, -1, -1);
    }

    @Override
    public TabulationConfiguration addBanner(String value, CellStyle cellStyle, int row1, int row2, int col1, int col2) {
        CellRangeAddress cellRangeAddress = new CellRangeAddress(row1,row2,col1,col2);
        return addBanner(value, cellStyle, cellRangeAddress);
    }

    @Override
    public TabulationConfiguration addBanner(String value, CellStyle cellStyle, CellRangeAddress cellRangeAddress) {
        value = value == null ? "" : value;
        BannerDefinition bannerDefinition = new BannerDefinition(cellStyle, cellRangeAddress, value);
        bannerDefinitions.add(bannerDefinition);
        return this;
    }

    @Override
    public TabulationConfiguration alterStartRowIndex(int startRowIndex) {
        startRowIndexValidate(startRowIndex);
        this.startRowIndex = startRowIndex;
        return this;
    }

    @Override
    public TabulationConfiguration alterTheadRowHeight(float theadRowHeight) {
        rowHeightValidate(theadRowHeight);
        this.theadRowHeight = theadRowHeight;
        return this;
    }

    @Override
    public TabulationConfiguration alterTbodyRowHeight(float tbodyRowHeight) {
        rowHeightValidate(tbodyRowHeight);
        this.tbodyRowHeight = tbodyRowHeight;
        return this;
    }

    @Override
    public TabulationConfiguration alterEffectiveRows(int effectiveRows) {
        effectiveRowsValidate(effectiveRows);
        this.effectiveRows = effectiveRows;
        return this;
    }

    @Override
    public TabulationConfiguration alterMaximumColumnsWidth(int maximunColumnsWidth) {
        maximumColumnsWidthValidate(maximunColumnsWidth, minimumColumnsWidth);
        this.maximumColumnsWidth = maximunColumnsWidth;
        return this;
    }

    @Override
    public TabulationConfiguration alterMinimumColumnsWidth(int minmunColumnsWidth) {
        minimumColumnsWidthValidate(minmunColumnsWidth);
        this.minimumColumnsWidth = minmunColumnsWidth;
        return this;
    }


}
