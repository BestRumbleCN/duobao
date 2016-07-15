package team.wuxie.crowdfunding.vo;

import java.util.List;
/**
 * dataTable需要的数据
 * @author fly
 * @version v1.0
 * @date 2016年4月12日下午6:26:28
 */
public class DataTableModel<T> {
	private int draw = 1;
	private int recordsTotal;
	private int recordsFiltered;
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public void setData(List<T> data) {
		this.data = data;
	}

	private List<T> data;

	public DataTableModel(List<T> aaData){
		this.data = aaData;
	}
	public List<T> getData() {
		return data;
	}
	public int getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
}
