package team.wuxie.crowdfunding.util;

/**
 * @author WuGang
 * @since 1.0
 */
public class DtOrder {
    private int column;
    private String dir;

    public DtOrder() {
    }

    public DtOrder(int column, String dir) {
        this.column = column;
        this.dir = dir;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
