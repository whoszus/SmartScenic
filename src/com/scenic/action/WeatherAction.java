package com.scenic.action;

import java.io.IOException;

import net.sf.json.JSON;

import org.apache.struts2.ServletActionContext;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.scenic.baseUitl.CharacterConversion;
import com.scenic.baseUitl.WriteJson;
import com.scenic.service.impl.GetWeatherService;

public class WeatherAction extends ActionSupport {

	@Autowired
	private GetWeatherService getWeatherService;

	private String cityName;

	// http://localhost:8080/SmartScenic/weather/weather_showZhishu.action?cityName=绵阳
	public void showZhishu() throws IOException, DocumentException {
		String str = getWeatherService.getWeatherinfoStr(cityName);
		String json = getWeatherService.fetchZhishu(str).toString();
		WriteJson.writeToClient(json);
	}

	// http://localhost:8080/SmartScenic/weather/weather_showForecast.action?cityName=绵阳
	public void showForecast() throws IOException, DocumentException {
		String str = getWeatherService.getWeatherinfoStr(cityName);
		String json = getWeatherService.fetchForecast(str).toString();
		WriteJson.writeToClient(json);
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
