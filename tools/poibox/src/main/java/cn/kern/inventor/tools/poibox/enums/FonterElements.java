package cn.kern.inventor.tools.poibox.enums;

/**
 * @Title: FonterElements
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/29 20:03
 */
public class FonterElements {

    /**
     * 字体颜色
     */
    public enum FontColor {

        BLACK(32767, "黑色"),
        RED(10,"暗红色"),
        ;

        private int index;

        private String description;

        FontColor(int index, String description) {
            this.index = index;
            this.description = description;
        }

        public short getIndex() {
            return (short) index;
        }
        public String getDescription() {
            return description;
        }
    }

    /**
     * 字体编码格式
     */
    public enum Charset {

        ANSI_CHARSET(0,"ANSI美国标准编码"),
        DEFAULT_CHARSET(1,"默认的编码格式"),
        SYMBOL_CHARSET(3,"符号编码格式"),
        ;

        private byte index;

        private String description;

        Charset(int index, String description) {
            this.index = (byte) index;
            this.description = description;
        }

        public byte getIndex() {
            return index;
        }
        public String getDescription() {
            return description;
        }

    }

    /**
     * 字体样式：下划线
     */
    public enum UnderLine {

        NONE(0,"无下划线"),
        SINGLE(1,"单条下划线"),
        DOUBLE(2,"双条下划线"),
        SINGLE_ACCOUNTING(33,"会计风格的单条下划线"),
        DOUBLE_ACCOUNTING(34,"会计风格的双条下划线"),
        ;

        private byte index;

        private String description;

        UnderLine(int index, String description) {
            this.index = (byte) index;
            this.description = description;
        }

        public byte getIndex() {
            return index;
        }
        public String getDescription() {
            return description;
        }
    }

}
