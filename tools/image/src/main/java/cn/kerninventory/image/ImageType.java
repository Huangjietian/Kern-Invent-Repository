package cn.kerninventory.image;

/**
 * <p>
 *     Enumeration of common picture formats.
 * </p>
 *
 * @author Kern
 */
public enum  ImageType {

    JPEG("Joint Photographic Experts Group.", "FFD8FF","jpeg"),
    JPG("Joint Photographic Experts Group","FFD8FF","jpg"),
    PNG("Portable Network Graphics", "89504E47","png"),
    GIF("Graphics Interchange Format", "47494638","gif"),
    TIFF("Tag Image File Format","49492A00","tif"),
    BMP("Bitmap","424D","bmp"),

    ;

    private String desc;
    private String header;
    private String suffix;

    ImageType(String desc, String header, String suffix) {
        this.desc = desc;
        this.header = header;
        this.suffix = suffix;
    }

    public String getDesc() {
        return desc;
    }

    public String getHeader() {
        return header;
    }

    public String getSuffix() {
        return suffix;
    }

    public static ImageType getBySuffix(String suffix) {
        ImageType[] types = ImageType.values();
        for (ImageType type : types) {
            if (type.getSuffix().equalsIgnoreCase(suffix)) {
                return type;
            }
        }
        return null;
    }

}
