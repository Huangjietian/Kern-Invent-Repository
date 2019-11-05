package cn.kern.inventor.tools.poibox.enums;

/**
 * @Title: CreateElements
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/29 16:57
 */
public class StylerElements {

    /**
     * 填充样式
     */
    public enum FillPattern {

        NO_FILL(0,"不填充"),
        SOLID_FOREGROUND(1,"完全填充"),
        FINE_DOTS(2,"细点"),
        ALT_BARS(3,""),
        SPARSE_DOTS(4,"稀疏的点"),
        THICK_HORZ_BANDS(5,""),
        THICK_VERT_BANDS(6,""),
        THICK_BACKWARD_DIAG(7,""),
        THICK_FORWARD_DIAG(8,""),
        BIG_SPOTS(9,"大的点"),
        BRICKS(10,"砖块状"),
        THIN_HORZ_BANDS(11,""),
        THIN_VERT_BANDS(12,""),
        THIN_BACKWARD_DIAG(13,""),
        THIN_FORWARD_DIAG(14,""),
        SQUARES(15,""),
        DIAMONDS(16,"砖石"),
        LESS_DOTS(17,"较少的点"),
        LEAST_DOTS(18,"最少的点"),
        ;

        private int index;

        private String description;

        FillPattern(int index, String description) {
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
     * 单元格方位
     */
    public enum CellDirection {
        SURROUND(0,"环绕"),
        TOP(1,"上"),
        BOTTOM(2,"下"),
        LEFT(3,"左"),
        RIGHT(4,"右"),
        ;

        private int index;

        private String description;

        CellDirection(int index, String description) {
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
     * 边框线条
     */
    public enum BorderLine {

        BORDER_NONE(0,"无边框"),
        BORDER_THIN(1,"细线"),
        BORDER_MEDIUM(2,"间隔线"),
        BORDER_DASHED(3,"虚线"),
        BORDER_HAIR(4,"细微的线"),
        BORDER_THICK(5,"粗线"),
        BORDER_DOUBLE(6,"两条线"),
        BORDER_DOTTED(7,"斑点线"),
        BORDER_MEDIUM_DASHED(8,"间隔虚线"),
        BORDER_DASH_DOT(9,"点划线"),
        BORDER_MEDIUM_DASH_DOT(10,"间隔的点划线"),
        BORDER_DASH_DOT_DOT(11,"点线"),
        BORDER_MEDIUM_DASH_DOT_DOT(12,"间隔的点线"),
        BORDER_SLANTED_DASH_DOT(13,"斜点划线"),
        ;

        private int index;

        private String description;

        BorderLine(int index, String description) {
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
     * 左右居中样式
     */
    public enum Align {
        ALIGN_GENERAL(0,"默认的"),
        ALIGN_LEFT(1,"左居中"),
        ALIGN_CENTER(2,"居中"),
        ALIGN_RIGHT(3,"右居中"),
        ALIGN_FILL(4,"对齐居中"),
        ALIGN_JUSTIFY(5,"两端对齐"),
        ALIGN_CENTER_SELECTION(6,"居中选择"),
        ;
        private int index;

        private String description;

        Align(int index, String description) {
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
     * 垂直居中样式
     */
    public enum Vertical {
        VERTICAL_TOP(0,"上居中"),
        VERTICAL_CENTER(1,"居中"),
        VERTICAL_BOTTOM(2,"下居中"),
        VERTICAL_JUSTIFY(3,"上下对齐"),
        ;
        private int index;

        private String description;

        Vertical(int index, String description) {
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

}
