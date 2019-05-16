package com.wenqiuhan.taotao.pojo;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class EasyUIDataGridResult implements Serializable {

	private long total;
	@SuppressWarnings("rawtypes")
	private List rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	@SuppressWarnings("rawtypes")
	public List getRows() {
		return rows;
	}

	public void setRows(@SuppressWarnings("rawtypes") List rows) {
		this.rows = rows;
	}

}
