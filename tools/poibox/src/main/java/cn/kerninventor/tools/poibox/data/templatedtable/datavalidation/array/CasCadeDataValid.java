package cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array;

/**
 * @author Kern
 * @date 2020/4/13 19:28
 * @description
 */
public @interface CasCadeDataValid {

    String CASCADE_FORMULA = "INDIRECT";

    String dependOn();


}