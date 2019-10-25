package cn.kern.inventor.tools.fileformat;

import cn.kern.inventor.tools.fileformat.enums.FileFormatEnum;

import java.io.InputStream;

/**
 * @Title: CheckResult
 * @ProjectName fileformat
 * @Version 1.1.0.RELEASE
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/22 18:57
 */
public class FormatChecker {

    private boolean pass;

    private FileFormatEnum targetFileFormat;

    private FileFormatEnum correctFormat;

    protected FormatChecker() {
    }
    protected FormatChecker(boolean pass, FileFormatEnum targetFileFormat, FileFormatEnum correctFormat) {
        this.pass = pass;
        this.targetFileFormat = targetFileFormat;
        this.correctFormat = correctFormat;
    }

    public boolean isPass() {
        return pass;
    }

    public boolean unPass() {
        return !pass;
    }

    public FileFormatEnum getCorrectFormat() {
        return correctFormat;
    }

    public FileFormatEnum getTargetFileFormat() {
        return targetFileFormat;
    }
}
