package cn.kerninventor.tools.poibox.opensource.utils;

/**
 * @author Kern
 * @date 2020/5/6 16:34
 * @description
 */
public class ExcelLineStringJoiner {

    private final String line = "\n";

    private StringBuilder builder = new StringBuilder();

    public StringBuilder getBuilder() {
        return builder;
    }

    @Override
    public String toString() {
        return builder.toString();
    }

    public ExcelLineStringJoiner appendLine(StringBuilder builder) {
        builder.append(line);
        return this;
    }

    public ExcelLineStringJoiner append(Object obj) {
        return append(String.valueOf(obj));
    }

    public ExcelLineStringJoiner append(String str) {
        return appendLine(builder.append(str));
    }

    public ExcelLineStringJoiner append(StringBuffer sb) {
        return appendLine(builder.append(sb));
    }

    public ExcelLineStringJoiner append(CharSequence s) {
        return appendLine(builder.append(s));
    }

    public ExcelLineStringJoiner append(CharSequence s, int start, int end) {
        return appendLine(builder.append(s, start, end));
    }

    public ExcelLineStringJoiner append(char[] str) {
        return appendLine(builder.append(str));
    }

    public ExcelLineStringJoiner append(char[] str, int offset, int len) {
        return appendLine(builder.append(str, offset, len));
    }

    public ExcelLineStringJoiner append(boolean b) {
        return appendLine(builder.append(b));
    }

    public ExcelLineStringJoiner append(char c) {
        return appendLine(builder.append(c));
    }

    public ExcelLineStringJoiner append(int i) {
        return appendLine(builder.append(i));
    }

    public ExcelLineStringJoiner append(long lng) {
        return appendLine(builder.append(lng));
    }

    public ExcelLineStringJoiner append(float f) {
        return appendLine(builder.append(f));
    }

    public ExcelLineStringJoiner append(double d) {
        return appendLine(builder.append(d));
    }

}
