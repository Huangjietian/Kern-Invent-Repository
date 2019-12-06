package cn.kerninventor.tools.web.gadget;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kern
 * @Title: SqlSatementPacker
 * @ProjectName swms
 * @Description: TODO
 * @date 2019-6-2416:24
 */
public class SqlPackager {

    public final static String PERCENT = "%";

    public static String SQL_LIKE(String sqlCondition){
        return PERCENT + StringUtils.trimToEmpty(sqlCondition) + PERCENT;
    }

    public static String SQL_LIKE_NULLABLE(String sqlCondition){
        if (StringUtils.isBlank(sqlCondition)){
            return null;
        }
        return SQL_LIKE(sqlCondition);
    }

}


