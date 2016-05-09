package com.scenic.model;

import java.util.List;

import com.scenic.baseUitl.DateUtil;


/**
 * 获取五天的粗略天气
 * 
 * @author Jane
 * 
 */
public class MWeather {
	private String city;// 城市
	private String[] time = new String[5];// 时间
	private String[] weather = new String[5];// 天气
	private String[] temp = new String[5];// 温度
	



	public  MWeather(List<String> datas) {
		
		String day=DateUtil.getCurrentDate();
		for(int i=0;i<5;i++){
			day=DateUtil.getSpecifiedDayLater(day);
			time[i]=day;
		}
		for (int i = 0; i < datas.size(); i++) {

			String data = datas.get(i);

			if (data.equals("city")) {
				this.city = datas.get(i + 1);
				i++;
			}

			if (data.equals("weather2")) {
				int j = 0;
				while (j < 5) {
					this.weather[j] = datas.get(i + 2 * j + 1);
					j++;
				}
			}

			if (data.equals("temp2")) {
				int j = 0;
				while (j < 5) {
					this.temp[j] = datas.get(i + 4 * j + 1);
					j++;
				}
			}
		}

	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String[] getTime() {
		return time;
	}

	public void setTime(String[] time) {
		time = time;
	}

	public String[] getWeather() {
		return weather;
	}

	public void setWeather(String[] weather) {
		this.weather = weather;
	}

	public String[] getTemp() {
		return temp;
	}

	public void setTemp(String[] temp) {
		this.temp = temp;
	}

}
