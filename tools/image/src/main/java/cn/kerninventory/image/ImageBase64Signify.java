package cn.kerninventory.image;

import java.util.Base64;

/**
 * <p>
 *     图片的base64表示
 * </p>
 *
 * @author Kern
 */
public class ImageBase64Signify {

    public final static String PREFIX_PART_1 = "data:image/";
    private String prefixPart2;
    public final static String PREFIX_PART_3 = ";base64,";
    private String suffix;
    private String base64Data;

    public ImageBase64Signify(String base64String) {
        base64String = base64String.substring(11);
        String[] arrays1 = base64String.split(";");
        if (arrays1.length != 2)
            throw new IllegalArgumentException("Wrong format of base64String!");
        this.prefixPart2 = arrays1[0];
        this.suffix = prefixPart2;
        this.base64Data = arrays1[1].substring(7);
    }

    public Image toImage() {
        ImageType type = ImageType.getBySuffix(suffix);
        byte[] bytes = Base64.getDecoder().decode(base64Data);
        return null;
    }
}
