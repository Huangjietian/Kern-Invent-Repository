package cn.kerninventor.tools.poibox.elements;

import org.apache.poi.ss.usermodel.Font;

/**
 * @Title: FonterElements
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/29 20:03
 */
public final class FonterElements {

    /**
     * 字体颜色
     */
    public enum FontColor {

        BLACK(Font.COLOR_NORMAL, "黑色"),
        RED(Font.COLOR_RED,"暗红色"),
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

        ANSI_CHARSET(Font.ANSI_CHARSET,"ANSI美国标准编码"),
        DEFAULT_CHARSET(Font.DEFAULT_CHARSET,"默认的编码格式"),
        SYMBOL_CHARSET(Font.SYMBOL_CHARSET,"符号编码格式"),
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

        NONE(Font.U_NONE,"无下划线"),
        SINGLE(Font.U_SINGLE,"单条下划线"),
        DOUBLE(Font.U_DOUBLE,"双条下划线"),
        SINGLE_ACCOUNTING(Font.U_SINGLE_ACCOUNTING,"会计风格的单条下划线"),
        DOUBLE_ACCOUNTING(Font.U_DOUBLE_ACCOUNTING,"会计风格的双条下划线"),
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
