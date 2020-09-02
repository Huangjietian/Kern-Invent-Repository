package cn.kerninventory.image;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.function.Function;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public class ImageGenerator {

    public static Image generate(MultipartFile multipartFile) throws IOException {
        Objects.requireNonNull(multipartFile, "Multipart File is null!");
        String fileName = multipartFile.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        ImageType type = ImageType.getBySuffix(suffix);
        Objects.requireNonNull(type, "Invalid file type, file's suffix is unsupported!");
        return generate(multipartFile.getInputStream(), type);
    }

    public static Image generate(File file) throws IOException {
        Objects.requireNonNull(file, "File is null!");
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        ImageType type = ImageType.getBySuffix(suffix);
        Objects.requireNonNull(type, "Invalid file type, file's suffix is unsupported!");
        return generate(new FileInputStream(file), type);
    }

    public static Image generate(String base64String) {
        ImageBase64Signify base64Signify = new ImageBase64Signify(base64String);
        return base64Signify.toImage();
    }

    public static Image generate(InputStream inputStream, ImageType type) throws IOException {
        Objects.requireNonNull(inputStream, "InputStream is null!");
        int available = inputStream.available();
        byte[] bytes = new byte[available];
        inputStream.read(bytes, 0, available);
        return generate(bytes, type);
    }

    public static Image generate(byte[] bytes, ImageType type) {
        if (bytes.length < 4) {
            throw new IllegalArgumentException("Bytes is empty!");
        }
        byte[] headerBytes = new byte[4];
        for (int i = 0; i < 4; i++) {
            headerBytes[i] = bytes[i];
        }
        String header = ((Function<byte[], String>)e -> {
            StringBuilder builder = new StringBuilder();
            String hv;
            for (int i = 0; i < e.length; i++) {
                hv = Integer.toHexString(e[i] & 0xFF).toUpperCase();
                if (hv.length() < 2) {
                    builder.append(0);
                }
                builder.append(hv);
            }
            return builder.toString();
        }).apply(headerBytes);
        if (!Objects.requireNonNull(header, "Source bytes header wrong!").contains(type.getHeader())) {
            throw new IllegalArgumentException("Illegal image, the bytes header and filename suffix do not matched!");
        }
        //TODO 返回图片对象
        return null;
    }

}
