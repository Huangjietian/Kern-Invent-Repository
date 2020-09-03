package cn.kerninventory.tools.excel.fluexcel.writer;

import org.apache.poi.ss.usermodel.CellStyle;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public final class WritingSupervisor {

    private Map<CellStyle, String[]> columnStyleMatchList;
    private List<String> ignoredColumnList;
    private Map<Predicate, List<String>> predicateIgnoredColumnList;



}
