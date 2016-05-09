package com.scenic.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.scenic.repo.pojo.DayData;

public class MAqiAndComf {
	private List<Date> Time = new ArrayList<Date>();
	private List<Float> aqi = new ArrayList<Float>();
	private List<Float> comf = new ArrayList<Float>();

	public MAqiAndComf(List<DayData> datas) {
		if (datas.isEmpty())
			return;
		for (DayData d : datas) {
			if (d.getDdTime() != null) {
				Time.add(d.getDdTime());

				if (d.getDdAqi() != null) {
					aqi.add(d.getDdAqi());
				} else {
					aqi.add(null);
				}

				if (d.getDdComfortLevel() != null) {
					comf.add(d.getDdComfortLevel());
				} else {
					comf.add(null);
				}
			}
		}
	}

	public List<Date> getTime() {
		return Time;
	}

	public void setTime(List<Date> time) {
		Time = time;
	}

	public List<Float> getAqi() {
		return aqi;
	}

	public void setAqi(List<Float> aqi) {
		this.aqi = aqi;
	}

	public List<Float> getComf() {
		return comf;
	}

	public void setComf(List<Float> comf) {
		this.comf = comf;
	}

	public MAqiAndComf(List<Date> time, List<Float> aqi, List<Float> comf) {
		super();
		Time = time;
		this.aqi = aqi;
		this.comf = comf;
	}

}
