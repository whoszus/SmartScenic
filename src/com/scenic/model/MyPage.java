package com.scenic.model;

import java.util.List;

public class MyPage {
	private long total;
	private List<MUserinfo> datas;

	public long getTotal() {
		return total;
	}

	public List<MUserinfo> getDatas() {
		return datas;
	}

	public void setDatas(List<MUserinfo> datas) {
		this.datas = datas;
	}

	public void setTotal(long totalElements) {
		this.total = totalElements;
	}
}
