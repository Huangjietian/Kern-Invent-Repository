package cn.kerninventor.tools.fileformat;

import cn.kerninventor.tools.fileformat.enums.FileFormatEnum;

/**
 * @Title: CheckResult
 * @ProjectName fileformat
 * @Version 1.1.0.RELEASE
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/22 18:57
 */
public class CheckResult {

    private boolean pass;

    private FileFormatEnum targetFileFormat;

    private FileFormatEnum[] correctFormats;

    protected CheckResult() {
    }
    protected CheckResult(boolean pass, FileFormatEnum targetFileFormat, FileFormatEnum[] correctFormats) {
        this.pass = pass;
        this.targetFileFormat = targetFileFormat;
        this.correctFormats = correctFormats;
    }

    public boolean isPass() {
        return pass;
    }

    public boolean unPass() {
        return !pass;
    }

    public FileFormatEnum[] getCorrectFormats() {
        return correctFormats;
    }

    public FileFormatEnum getTargetFileFormat() {
        return targetFileFormat;
    }
}
