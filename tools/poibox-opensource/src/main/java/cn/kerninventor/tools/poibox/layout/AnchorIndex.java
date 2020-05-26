package cn.kerninventor.tools.poibox.layout;

/**
 * @author Kern
 * @date 2020/4/14 11:08
 */
public class AnchorIndex {

    private int left;

    private int top;

    private int right;

    private int bottom;

    private int col1;

    private int row1;

    private int col2;

    private int row2;

    public AnchorIndex(int col1, int row1, int col2, int row2) {
        this.col1 = col1;
        this.row1 = row1;
        this.col2 = col2;
        this.row2 = row2;
    }

    public AnchorIndex(int anchorLeft, int anchorTop, int anchorRight, int anchorBottom, int col1, int row1, int col2, int row2) {
        this.left = anchorLeft;
        this.top = anchorTop;
        this.right = anchorRight;
        this.bottom = anchorBottom;
        this.col1 = col1;
        this.row1 = row1;
        this.col2 = col2;
        this.row2 = row2;
    }

    public int getLeft() {
        return left;
    }

    public AnchorIndex setLeft(int anchorLeft) {
        this.left = anchorLeft;
        return this;
    }

    public int getTop() {
        return top;
    }

    public AnchorIndex setTop(int anchorTop) {
        this.top = anchorTop;
        return this;
    }

    public int getRight() {
        return right;
    }

    public AnchorIndex setRight(int anchorRight) {
        this.right = anchorRight;
        return this;
    }

    public int getBottom() {
        return bottom;
    }

    public AnchorIndex setBottom(int anchorBottom) {
        this.bottom = anchorBottom;
        return this;
    }

    public int getCol1() {
        return col1;
    }

    public AnchorIndex setCol1(int col1) {
        this.col1 = col1;
        return this;
    }

    public int getRow1() {
        return row1;
    }

    public AnchorIndex setRow1(int row1) {
        this.row1 = row1;
        return this;
    }

    public int getCol2() {
        return col2;
    }

    public AnchorIndex setCol2(int col2) {
        this.col2 = col2;
        return this;
    }

    public int getRow2() {
        return row2;
    }

    public AnchorIndex setRow2(int row2) {
        this.row2 = row2;
        return this;
    }
}
