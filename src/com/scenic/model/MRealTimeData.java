package com.scenic.model;

import java.util.Date;
import java.util.List;

import com.scenic.repo.pojo.DetectionPoint;
import com.scenic.repo.pojo.RealTimeData;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class MRealTimeData {
//	private Integer[] rtdNo;
//	private DetectionPoint[] detectionPoint;
	private Float[] rtdTemperature = new Float[7];
//	private Date rtdTime;
	private Float[] rtdOxygenContent= new Float[7];
	private Float[] rtdPm10= new Float[7];
	private Float[] rtdPm25= new Float[7];
	private Float[] rtdAirSpeed= new Float[7];
	private Float[] rtdUltraviolet= new Float[7];
	private Float[] rtdAirPollutionIndex= new Float[7];
	
	public MRealTimeData () {
		
	}

	
	public MRealTimeData (List<Float>datas) {
		for(int i = 0;i<49;i++){
			int type = i%7;
			int order = i/7;
			float data = datas.get(i);
			switch (type) {
			case 0:
				rtdTemperature[order] = data;
				break;
			case 1:
				rtdOxygenContent[order] = data;
				break;
			case 2:
				rtdPm10[order] = data;
				break;
			case 3:
				rtdPm25[order] = data;
				break;
			case 4:
				rtdAirSpeed[order] = data;
				break;
			case 5:
				rtdUltraviolet[order] = data;
				break;
			case 6:
				rtdAirPollutionIndex[order] = data;
				break;
			default:
				break;
			}
		}
	}
	
	public Float[] getRtdTemperature() {
		return rtdTemperature;
	}

	public void setRtdTemperature(Float[] rtdTemperature) {
		this.rtdTemperature = rtdTemperature;
	}

	public Float[] getRtdOxygenContent() {
		return rtdOxygenContent;
	}

	public void setRtdOxygenContent(Float[] rtdOxygenContent) {
		this.rtdOxygenContent = rtdOxygenContent;
	}

	public Float[] getRtdPm10() {
		return rtdPm10;
	}

	public void setRtdPm10(Float[] rtdPm10) {
		this.rtdPm10 = rtdPm10;
	}

	public Float[] getRtdPm25() {
		return rtdPm25;
	}

	public void setRtdPm25(Float[] rtdPm25) {
		this.rtdPm25 = rtdPm25;
	}

	public Float[] getRtdAirSpeed() {
		return rtdAirSpeed;
	}

	public void setRtdAirSpeed(Float[] rtdAirSpeed) {
		this.rtdAirSpeed = rtdAirSpeed;
	}

	public Float[] getRtdUltraviolet() {
		return rtdUltraviolet;
	}

	public void setRtdUltraviolet(Float[] rtdUltraviolet) {
		this.rtdUltraviolet = rtdUltraviolet;
	}

	public Float[] getRtdAirPollutionIndex() {
		return rtdAirPollutionIndex;
	}

	public void setRtdAirPollutionIndex(Float[] rtdAirPollutionIndex) {
		this.rtdAirPollutionIndex = rtdAirPollutionIndex;
	}
	

}
