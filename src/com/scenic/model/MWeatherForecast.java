package com.scenic.model;

public class MWeatherForecast {
	private String date;// 时间
	private String temp;// 温度
	private String weather;// 天气
	private String wind_force;// 风力

	public MWeatherForecast(String date, String temp, String weather,
			String windForce) {
		super();
		this.date = date;
		this.temp = temp;
		this.weather = weather;
		wind_force = windForce;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getWind_force() {
		return wind_force;
	}

	public void setWind_force(String windForce) {
		wind_force = windForce;
	}

}
