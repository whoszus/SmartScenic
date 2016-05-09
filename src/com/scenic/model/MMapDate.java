package com.scenic.model;

public class MMapDate {
	private double lng; // 经度
	private double lat; // 纬度
	private double count; // aqi

	public MMapDate(double lng, double lat, double count) {
		super();
		this.lng = lng;
		this.lat = lat;
		this.count = count;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}
}
