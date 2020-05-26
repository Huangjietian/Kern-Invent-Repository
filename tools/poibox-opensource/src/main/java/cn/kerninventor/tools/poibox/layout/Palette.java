package cn.kerninventor.tools.poibox.layout;

/**
 * @author Kern
 * @date 2020/5/7 12:36
 * @description
 */
public enum Palette {

    BLACK(0,0,0),
    WHITE(255,255,255),
    RED(255,0,0),
    GREEN(0,255,0),
    BLUE(0,0,255),
    YELLOW(255,255,0),
    PURPLE(255,0,255),
    CYAN(0,255,255),
    GRAY(127,127,127),
    ;

    Palette(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    private int red;

    private int green;

    private int blue;

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }
}
