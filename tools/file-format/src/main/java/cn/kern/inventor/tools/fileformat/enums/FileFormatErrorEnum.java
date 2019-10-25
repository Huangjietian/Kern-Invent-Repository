package cn.kern.inventor.tools.fileformat.enums;

/**
 * @Title: FileFormatErrorEnum
 * @ProjectName fileformat
 * @Version 1.0.SNAPSHOT
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/22 18:48
 */
public enum FileFormatErrorEnum {

    FORMAT_ERROR_MSG(501,"文件格式错误!"),
    FILE_BLANK_MSG(502,"空文件错误!"),
    FILE_RESOLVE_ERROR_MSG(503,"文件解析错误"),
    ;
    private int code;
    private String msg;

    FileFormatErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
