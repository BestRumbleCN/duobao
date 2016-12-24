package team.wuxie.crowdfunding.util.page;

import com.github.pagehelper.PageInfo;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * jQuery dataTable Page
 * </p>
 *
 * @author wushige
 * @date 2016-07-13 18:57
 */
public class Page<T> implements Serializable {

    //请求次数
    private long draw;
    //总记录数
    private long recordsTotal;
    //过滤后记录数
    private long recordsFiltered;
    //返回数据
    private List<T> data = Lists.newArrayList();

    public Page() {
    }

    public Page(long draw, long recordsTotal, long recordsFiltered, List<T> data) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }

    public Page(PageInfo<T> pageInfo, long draw) {
        this.draw = draw;
        this.recordsTotal = pageInfo.getTotal();
        this.recordsFiltered = pageInfo.getTotal();
        this.data = pageInfo.getList();
    }

    public long getDraw() {
        return draw;
    }

    public void setDraw(long draw) {
        this.draw = draw;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("draw", draw)
                .add("recordsTotal", recordsTotal)
                .add("recordsFiltered", recordsFiltered)
                .add("data", data)
                .toString();
    }
}
