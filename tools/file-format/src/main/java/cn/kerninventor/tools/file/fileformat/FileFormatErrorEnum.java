package cn.kerninventor.tools.file.fileformat;

/**
 * @author Kern
 * @date 2019/10/22 18:48
 */
public enum FileFormatErrorEnum {

    FORMAT_ERROR_MSG(501,"File format error!"),
    FILE_BLANK_MSG(502,"Empty file error!"),
    FILE_RESOLVE_ERROR_MSG(503,"File resolution error"),
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
