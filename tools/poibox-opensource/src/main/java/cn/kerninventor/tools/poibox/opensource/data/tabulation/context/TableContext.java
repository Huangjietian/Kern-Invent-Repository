package cn.kerninventor.tools.poibox.opensource.data.tabulation.context;

import cn.kerninventor.tools.poibox.opensource.BoxBracket;
import cn.kerninventor.tools.poibox.opensource.BoxGadget;
import cn.kerninventor.tools.poibox.opensource.Poibox;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.ExcelBanner;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.ExcelColumn;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.ExcelTabulation;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.element.Style;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.element.Textbox;
import cn.kerninventor.tools.poibox.opensource.exception.IllegalSourceClassOfTabulationException;
import cn.kerninventor.tools.poibox.opensource.exception.IllegalTabulationConfigureException;
import cn.kerninventor.tools.poibox.opensource.style.Styler;
import cn.kerninventor.tools.poibox.opensource.utils.BeanUtil;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Kern
 * @date 2019/12/9 15:25
 */
public class TableContext<T> extends BoxBracket implements TabContextModifier {

    private Class<T> tabulationClass;
    private Map<Integer, CellStyle> theadStyleMap;
    private Map<Integer, CellStyle> tbodyStyleMap;
    private int theadRowIndex;
    private float theadRowHeight;
    private float tbodyRowHeight;
    private int maximunColumnsWidth;
    private int minimunColumnsWidth;
    private int startRowIndex;
    private int effectiveRows;
    private Textbox[] textboxes;
    private List<BannerDefinition> bannerDefinitions;
    private List<ColumnDefinition> columnDefinitions;

    public TableContext(Class<T> tableClass, Poibox poiBox) {
        super(poiBox);
        this.tabulationClass = tableClass;
        init();
    }

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

    public int getMaximunColumnsWidth() {
        return BoxGadget.adjustCellWidth(maximunColumnsWidth);
    }

    public int getMinimumColumnsWidth() {
        return BoxGadget.adjustCellWidth(minimunColumnsWidth);
    }

    public int getTheadRowIndex() {
        return theadRowIndex;
    }

    public int getTbodyFirstRowIndex(){
        return theadRowIndex + 1;
    }

    public List<BannerDefinition> getBannerDefinitions() {
        return bannerDefinitions;
    }

    public List<ColumnDefinition> getColumnDefinitions() {
        return columnDefinitions;
    }

    public Textbox[] getTextboxes() {
        return textboxes;
    }

    private void init(){
        Class tabulationClass = getTabulationClass();
        //element object init
        ExcelTabulation excelTabulation = dataTabulationSourceClassValidate(tabulationClass);
        this.theadStyleMap = initStyles(excelTabulation.theadStyles());
        this.tbodyStyleMap = initStyles(excelTabulation.tbodyStyles());
        this.bannerDefinitions = initialzeBanners(excelTabulation.banners());
        this.columnDefinitions = initialzeColumns(tabulationClass);
        //mumerical value init
        this.startRowIndex = excelTabulation.startRowIndex();
        this.theadRowIndex = getRowIndexIncrementsByBanners(bannerDefinitions) + startRowIndex;
        this.theadRowHeight = excelTabulation.theadRowHeight();
        this.tbodyRowHeight = excelTabulation.tbodyRowHeight();
        this.effectiveRows = excelTabulation.effectiveRows();
        this.maximunColumnsWidth = excelTabulation.maximumColumnsWidth();
        this.minimunColumnsWidth = excelTabulation.minimumColumnsWidth();

        this.textboxes = excelTabulation.textboxes();
    }

    private Map<Integer, CellStyle> initStyles(Style[] styles) {
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
        List<ColumnDefinition> columnDefinitions = new ArrayList(fields.length);
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
        setColumnsIndex(columnDefinitions);
        return columnDefinitions;
    }

    private int getRowIndexIncrementsByBanners(List<BannerDefinition> bannerDefinitions) {
        if (BeanUtil.isEmpty(bannerDefinitions)) {
            return 0;
        }
        int maximum = bannerDefinitions.stream().mapToInt(e -> e.getLastRowIndex()).max().getAsInt();
        if (maximum > 1) {
            maximum++;
        }
        return maximum;
    }

    public void setColumnsIndex(List<ColumnDefinition> columnDefinitions) {
        Collections.sort(columnDefinitions);
        for (int i = 0 ; i < columnDefinitions.size() ; i ++) {
            columnDefinitions.get(i).setColumnIndex(i);
        }
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
    public TabContextModifier addBanner(String value, CellStyle cellStyle, int row1, int row2) {
        return addBanner(value, cellStyle, row1, row2, -1, -1);
    }

    @Override
    public TabContextModifier addBanner(String value, CellStyle cellStyle, int row1, int row2, int col1, int col2) {
        CellRangeAddress cellRangeAddress = new CellRangeAddress(row1,row2,col1,col2);
        return addBanner(value, cellStyle, cellRangeAddress);
    }

    @Override
    public TabContextModifier addBanner(String value, CellStyle cellStyle, CellRangeAddress cellRangeAddress) {
        value = value == null ? "" : value;
        BannerDefinition bannerDefinition = new BannerDefinition(cellStyle, cellRangeAddress, value);
        bannerDefinitions.add(bannerDefinition);
        return this;
    }

    @Override
    public List<? extends BannerDefinitionModifier> getBanners() {
        return bannerDefinitions;
    }

    @Override
    public BannerDefinitionModifier getBannerAt(int index) {
        return bannerDefinitions.get(index);
    }

    @Override
    public List<? extends ColumnDefinitionModifier> getColumns() {
        return columnDefinitions;
    }

    @Override
    public ColumnDefinitionModifier getColumnByTitileName(String titleName) {
        return columnDefinitions.stream().filter(e -> e.getTitleName().equals(titleName)).findFirst().get();
    }

    @Override
    public ColumnDefinitionModifier getColumnByFieldName(String fieldName) {
        return columnDefinitions.stream().filter(e -> e.getFieldName().equals(fieldName)).findFirst().get();
    }

    @Override
    public TabContextModifier alterStartRowIndex(int startRowIndex) {
        startRowIndexValidate(startRowIndex);
        this.startRowIndex = startRowIndex;
        this.theadRowIndex = getRowIndexIncrementsByBanners(getBannerDefinitions()) + startRowIndex;
        return this;
    }

    @Override
    public TabContextModifier alterTheadRowHeight(float theadRowHeight) {
        rowHeightValidate(theadRowHeight);
        this.theadRowHeight = theadRowHeight;
        return this;
    }

    @Override
    public TabContextModifier alterTbodyRowHeight(float tbodyRowHeight) {
        rowHeightValidate(tbodyRowHeight);
        this.tbodyRowHeight = tbodyRowHeight;
        return this;
    }

    @Override
    public TabContextModifier alterEffectiveRows(int effectiveRows) {
        effectiveRowsValidate(effectiveRows);
        this.effectiveRows = effectiveRows;
        return this;
    }

    @Override
    public TabContextModifier alterMaxmunColumnsWidth(int maximunColumnsWidth) {
        maximumColumnsWidthValidate(maximunColumnsWidth, minimunColumnsWidth);
        this.maximunColumnsWidth = maximunColumnsWidth;
        return this;
    }

    @Override
    public TabContextModifier alterMinmunColumnsWidth(int minmunColumnsWidth) {
        minimumColumnsWidthValidate(minmunColumnsWidth);
        this.minimunColumnsWidth = minmunColumnsWidth;
        return this;
    }


}
