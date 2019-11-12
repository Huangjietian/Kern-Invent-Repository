package cn.kerninventor.tools.webimageuploader;

/**
 * @Title: ByteSize
 * @ProjectName swms
 * @Version 1.1.0.RELEASE
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/17 16:21
 */
public class ByteSize {

    private final static Integer MB_1 = 1048576;
    public static Double toMB(Integer b){
        return (double) b / MB_1;
    }
    public static Integer toB(Integer mb){
        return mb * MB_1;
    }
    public static int compareTo(Integer mb, Integer b){
        Integer firstB = toB(mb);
        if (firstB > b){
            return 1;
        }else if (firstB < b){
            return -1;
        }else {
            return 0;
        }
    }
}
