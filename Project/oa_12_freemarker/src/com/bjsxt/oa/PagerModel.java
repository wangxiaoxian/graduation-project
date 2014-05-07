package com.bjsxt.oa;

import java.util.List;
public class PagerModel<T> {

	private List<T> datas;	// 当前页的结果集
	private int offset;	// 取得第一条记录的位置
	private int total;	// 总记录数
	private int pagesize; // 每页显示多少条
	
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

}
