package cn.kerninventor.tools.file.fileformat;

/**
 * <h1>中文注释</h1>
 * <p>
 *     文件格式类型枚举类<br/>
 *     注：保留了自定义COSTOM枚举，支持set方法。如果非COSTOM调用了set方法将得到一个异常。
 * </p>
 * @author Kern
 * @version 1.0
 */
public enum FileFormatType {

    JPG("FFD8FF", ".jpg"),
    JPEG("FFD8FF", ".jpeg"),
    PNG("89504E47", ".png"),
    GIF("47494638", ".gif"),
    TIF("49492A00", ".tif"),
    BMP("424D", ".bmp"),
    XML("3C3F786D6C", ".xml"),
    HTML("68746D6C3E", ".html"),
    XLS("D0CF11E0", ".xls"),
    XLSX("504B0304", ".xlsx"),
    DOC("D0CF11E0", ".doc"),
    DOCX("504B0304", ".docx"),
    PDF("255044462D312E", ".pdf"),
    ZIP("504B0304", ".zip"),
    RAR("52617221", ".rar"),
    EXE("4D5A9000", ".exe"),
    DLL("4D5A9000", ".dll"),
    TXT("75736167", ".txt"),
    CUSTOM("----------", "----");
    ;

    private String header;
    private String suffix;

    FileFormatType(String header, String suffix) {
        this.header = header;
        this.suffix = suffix;
    }

    public String getHeader() {
        return header;
    }

    public String getSuffix() {
        return suffix;
    }

    public FileFormatType setHeader(String header) {
        if (!"CUSTOM".equals(this.name())) {
            throw new IllegalArgumentException("Modifying enumeration values is not supported");
        }
        this.header = header;
        return this;
    }

    public FileFormatType setSuffix(String suffix) {
        if (!"CUSTOM".equals(this.name())) {
            throw new IllegalArgumentException("Modifying enumeration values is not supported");
        }
        this.suffix = suffix;
        return this;
    }

    /**
     * <p>
     *     匹配后缀
     * </p>
     * @param path
     * @param fileFormatTypes
     * @return
     */
    public static boolean isCorrectSuffix(String path, FileFormatType... fileFormatTypes) {
        int index;
        if (path == null || (index = path.lastIndexOf("."))== -1){
            return false;
        }
        for (FileFormatType fileFormatType : fileFormatTypes){
            if (path.substring(index).equals(fileFormatType.getSuffix())){
                return true;
            }
        }
        return false;
    }
}
