package com.scenic.model;

public class MSpotSort {

	private String scenicSpotName;// 景点名
	private Float Aqi;// Aqi
	private int ComfortLevel;// 舒适度
	private String provinceName;// 省名
	private String cityName;// 城市名
	private String airQuality;// 空气质量等级
	private Float comprehensive;// 综合排名

	public MSpotSort(String scenicSportName, String provinceName,
			String cityName, Float Aqi, Float ComfortLevel, Float comprehensive) {
		this.scenicSpotName = scenicSportName;
		this.provinceName = provinceName;
		this.cityName = cityName;
		this.Aqi = Aqi;

		if (Aqi > 300) {
			this.airQuality = "严重污染";
		} else if (Aqi > 200) {
			this.airQuality = "重度污染";
		} else if (Aqi > 150) {
			this.airQuality = "中度污染";
		} else if (Aqi > 100) {
			this.airQuality = "轻度污染";
		} else if (Aqi > 50) {
			this.airQuality = "良";
		} else if (Aqi > 0) {
			this.airQuality = "优";
		} else {
			this.airQuality = null;
		}

		if (ComfortLevel != null) {
			if (ComfortLevel > 0 && ComfortLevel < 25) {
				this.ComfortLevel = 8;
			} else if (ComfortLevel < 40) {
				this.ComfortLevel = 6;
			} else if (ComfortLevel < 50) {
				this.ComfortLevel = 4;
			} else if (ComfortLevel < 60) {
				this.ComfortLevel = 2;
			} else if (ComfortLevel < 70) {
				this.ComfortLevel = 1;
			} else if (ComfortLevel < 79) {
				this.ComfortLevel = 3;
			} else if (ComfortLevel < 85) {
				this.ComfortLevel = 5;
			} else if (ComfortLevel < 90) {
				this.ComfortLevel = 7;
			} else {
				this.ComfortLevel = 9;
			}
		}

		this.comprehensive = comprehensive;
	}

	public MSpotSort() {

	}

	public String getScenicSpotName() {
		return scenicSpotName;
	}

	public void setScenicSpotName(String scenicSpotName) {
		this.scenicSpotName = scenicSpotName;
	}

	public Float getAqi() {
		return Aqi;
	}

	public void setAqi(Float aqi) {
		Aqi = aqi;
	}

	public int getComfortLevel() {
		return ComfortLevel;
	}

	public void setComfortLevel(int comfortLevel) {
		ComfortLevel = comfortLevel;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAirQuality() {
		return airQuality;
	}

	public void setAirQuality(String airQuality) {
		this.airQuality = airQuality;
	}

	public Float getComprehensive() {
		return comprehensive;
	}

	public void setComprehensive() {
		this.comprehensive = this.Aqi + this.ComfortLevel * 10;
	}

}
