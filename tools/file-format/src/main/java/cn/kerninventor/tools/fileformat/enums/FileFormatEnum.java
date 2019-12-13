package cn.kerninventor.tools.fileformat.enums;

/**
 * @Title: FileFormatEnum
 * @ProjectName fileformat
 * @Version 1.0.SNAPSHOT
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/22 16:34
 */
public enum FileFormatEnum {

    //image
    JPG("FFD8FF", ".jpg"),
    JPEG("FFD8FF", ".jpeg"),
    PNG("89504E47", ".png"),
//    GIF("47494638", ".gif"),
//    TIF("49492A00", ".tif"),
//    BMP("424D", ".bmp"),
//    XML("3C3F786D6C", ".xml"),
//    HTML("68746D6C3E", ".html"),
    //doc
    XLS_EXCEL("D0CF11E0", ".xls"),
    XLSX_EXCEL("504B0304", ".xlsx"),
    DOC_WORD("D0CF11E0", ".doc"),
    DOCX_WORD("504B0304", ".docx"),
//    PDF("255044462D312E", ".pdf"),
//    ZIP("504B0304", ".zip"),
//    RAR("52617221", ".rar"),
//    EXE("4D5A9000", ".exe"),
//    DLL("4D5A9000", ".dll"),
//    TXT("75736167", ".txt"),
    ;

    private String header;
    private String suffix;

    FileFormatEnum(String header, String suffix) {
        this.header = header;
        this.suffix = suffix;
    }

    public String getHeader() {
        return header;
    }

    public String getSuffix() {
        return suffix;
    }

    public static FileFormatEnum getFormatByHeader(String header){
        FileFormatEnum[] enums = FileFormatEnum.values();
        for (FileFormatEnum formatEnum : enums){
            if (header.contains(formatEnum.header)){
                return formatEnum;
            }
        }
        return null;
    }

    public static boolean isCorrectSuffix(FileFormatEnum fileFormatEnum, String path) {
        if (path == null || "".equals(path.trim()) || !path.contains(".")){
            return false;
        }
        return path.substring(path.lastIndexOf(".")).equals(fileFormatEnum.suffix);
    }
}
