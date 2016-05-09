package com.scenic.model;

public class MZhishu {
	private String name;
	private String value;
	private String detail;

	public MZhishu(String name, String value, String detail) {
		this.name = name;
		this.value = value;
		this.detail = detail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
