package com.scenic.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.scenic.repo.pojo.DayData;

public class MOtherIndex {
	private List<Date> time = new ArrayList<Date>();
	private List<Float> temperature = new ArrayList<Float>();
	private List<Float> humidity = new ArrayList<Float>();
	private List<Float> ultraviolet = new ArrayList<Float>();
	private List<Float> oxygenContent = new ArrayList<Float>();

	public MOtherIndex(List<DayData> datas) {
		if (datas.isEmpty())
			return;
		for (DayData d : datas) {

			if (d.getDdTime() != null) {
				time.add(d.getDdTime());

				if (d.getDdTemperature() != null) {
					temperature.add(d.getDdTemperature());
				} else {
					temperature.add(null);
				}

				if (d.getDdHumidity() != null) {

					humidity.add(d.getDdHumidity());
				} else {
					humidity.add(null);
				}

				if (d.getDdUlteraviolet() != null) {
					System.out.println(d.getDdUlteraviolet());
					ultraviolet.add(d.getDdUlteraviolet());
				} else {
					ultraviolet.add(null);
				}

				if (d.getDdOxygenContent() != null) {
					oxygenContent.add(d.getDdOxygenContent());
				} else {
					oxygenContent.add(null);
				}

			}
		}

	}

	public List<Date> getTime() {
		return time;
	}

	public void setTime(List<Date> time) {
		this.time = time;
	}

	public List<Float> getTemperature() {
		return temperature;
	}

	public void setTemperature(List<Float> temperature) {
		this.temperature = temperature;
	}

	public List<Float> getHumidity() {
		return humidity;
	}

	public void setHumidity(List<Float> humidity) {
		this.humidity = humidity;
	}

	public List<Float> getUltraviolet() {
		return ultraviolet;
	}

	public void setUltraviolet(List<Float> ultraviolet) {
		this.ultraviolet = ultraviolet;
	}

	public List<Float> getOxygenContent() {
		return oxygenContent;
	}

	public void setOxygenContent(List<Float> oxygenContent) {
		this.oxygenContent = oxygenContent;
	}

}
