package com.scenic.model;

import java.util.Date;

public class MSpotData {

	private Date time;
	private Float aqi;
	private Float comf;
	private Float pm25;

	public MSpotData(Date time, Float aqi, Float comf, Float pm25) {
		super();
		this.time = time;
		this.aqi = aqi;
		this.comf = comf;
		this.pm25 = pm25;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Float getAqi() {
		return aqi;
	}

	public void setAqi(Float aqi) {
		this.aqi = aqi;
	}

	public Float getComf() {
		return comf;
	}

	public void setComf(Float comf) {
		this.comf = comf;
	}

	public Float getPm25() {
		return pm25;
	}

	public void setPm25(Float pm25) {
		this.pm25 = pm25;
	}
}
