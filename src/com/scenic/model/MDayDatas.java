package com.scenic.model;

import java.util.ArrayList;
import java.util.List;

public class MDayDatas {

	private List<String> ddTime = new ArrayList<String>();
	private List<String> ddTemperature = new ArrayList<String>();
	private List<String> ddHumidity = new ArrayList<String>();
	private List<String> ddAqi = new ArrayList<String>();
	private List<String> ddComfortLevel = new ArrayList<String>();
	private List<String> ddMainPollution = new ArrayList<String>();
	private List<String> ddAirPollutionIndex = new ArrayList<String>();

	public MDayDatas(List<String> datas, int days) {

		for (int i = 0; i < datas.size(); i++) {

			if (datas.get(i) != null) {
				String data = datas.get(i);
				int order = i % 7;
				switch (order) {
				case 0:
					ddTime.add(data);
					break;
				case 1:
					ddTemperature.add(data);
					break;
				case 2:
					ddHumidity.add(data);
					break;
				case 3:
					ddAqi.add(data);
					break;
				case 4:
					ddComfortLevel.add(data);
					break;
				case 5:
					ddMainPollution.add(data);
					break;
				case 6:
					ddAirPollutionIndex.add(data);
					break;
				default:
					break;
				}
			}
		}
	}

	public List<String> getDdTime() {
		return ddTime;
	}

	public void setDdTime(List<String> ddTime) {
		this.ddTime = ddTime;
	}

	public List<String> getDdTemperature() {
		return ddTemperature;
	}

	public void setDdTemperature(List<String> ddTemperature) {
		this.ddTemperature = ddTemperature;
	}

	public List<String> getDdHumidity() {
		return ddHumidity;
	}

	public void setDdHumidity(List<String> ddHumidity) {
		this.ddHumidity = ddHumidity;
	}

	public List<String> getDdAqi() {
		return ddAqi;
	}

	public void setDdAqi(List<String> ddAqi) {
		this.ddAqi = ddAqi;
	}

	public List<String> getDdComfortLevel() {
		return ddComfortLevel;
	}

	public void setDdComfortLevel(List<String> ddComfortLevel) {
		this.ddComfortLevel = ddComfortLevel;
	}

	public List<String> getDdMainPollution() {
		return ddMainPollution;
	}

	public void setDdMainPollution(List<String> ddMainPollution) {
		this.ddMainPollution = ddMainPollution;
	}

	public List<String> getDdAirPollutionIndex() {
		return ddAirPollutionIndex;
	}

	public void setDdAirPollutionIndex(List<String> ddAirPollutionIndex) {
		this.ddAirPollutionIndex = ddAirPollutionIndex;
	}

}
