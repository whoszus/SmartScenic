package com.scenic.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.scenic.repo.pojo.DayData;

public class MOtherIndex {
	private List<Date> time = new ArrayList<Date>();
	private List<Float> temperature = new ArrayList<Float>();
	private List<Float> humidity = new ArrayList<Float>();
	private List<Float> pm25 = new ArrayList<Float>();
	private List<Float> pm10 =  new ArrayList<Float>();
	private List<Float> airSpeed = new ArrayList<Float>();

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

				if (d.getDdPm25()!= null) {
					pm25.add(d.getDdUlteraviolet());
				} else {
					pm25.add(null);
				}

				if (d.getDdPm10() != null) {
					pm10.add(d.getDdOxygenContent());
				} else {
					pm10.add(null);
				}

				if (d.getDdAirSpeed()!= null) {
					airSpeed.add(d.getDdOxygenContent());
				} else {
					airSpeed.add(null);
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

	public List<Float> getPm10() {
		return pm10;
	}

	public void setPm10(List<Float> pm10) {
		this.pm10 = pm10;
	}

	public List<Float> getPm25() {
		return pm25;
	}

	public void setPm25(List<Float> pm25) {
		this.pm25 = pm25;
	}

	public List<Float> getAirSpeed() {
		return airSpeed;
	}

	public void setAirSpeed(List<Float> airSpeed) {
		this.airSpeed = airSpeed;
	}
}
