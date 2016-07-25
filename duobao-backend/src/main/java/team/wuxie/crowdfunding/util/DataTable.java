package team.wuxie.crowdfunding.util;

import com.google.common.base.Strings;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * <p>
 * DataTable请求参数
 * </p>
 *
 * @author wushige
 * @date 2016-07-25 10:05
 */
public class DataTable implements Serializable {
    //请求次数
    private int draw;
    //数据库记录开始行数
    private int start;
    //请求的记录个数，也即是页面记录数
    private int length = 10;
    //排序的字段
    private String orderColumn;
    //排序方式 默认为asc
    private String orderDirection;
    //搜索参数
    private String searchValue;
    //前端显示的字段对应在数据库表中的字段
    private String[] cols;
    private HttpServletRequest request;

    //获取分页的请求页码
    public int getPageNum() {
        return start / length + 1;
    }

    //获取排序
    public String getOrderBy() {
        String orderBy = null;
        if (!Strings.isNullOrEmpty(getOrderColumn()) && !Strings.isNullOrEmpty(getOrderDirection())) {
            orderBy = getOrderColumn() + " " + getOrderDirection();
        }
        return orderBy;
    }

    //获取查询参数
    public String getSearchValue() {
        return getRequest() != null ? getRequest().getParameter("search[value]") : searchValue;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getOrderColumn() {
        if (getRequest() != null) {
            orderColumn = getRequest().getParameter("order[0][column]");
            if (!Strings.isNullOrEmpty(orderColumn)) orderColumn = getCols()[Integer.parseInt(orderColumn)];
        }
        return  orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderDirection() {
        return getRequest() != null ? getRequest().getParameter("order[0][dir]") : orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String[] getCols() {
        return cols;
    }

    public void setCols(String[] cols) {
        this.cols = cols;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
