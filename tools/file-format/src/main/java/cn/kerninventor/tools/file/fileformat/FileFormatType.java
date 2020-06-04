package cn.kerninventor.tools.file.fileformat;

/**
 * @author Kern
 * @date 2019/10/22 16:34
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
