package com.scenic.service;

import java.io.IOException;

import net.sf.json.JSON;

import org.dom4j.DocumentException;

public interface IGetweatherService {

	public String getWeatherinfoStr(String cityName) throws IOException;

	public String fetchZhishu(String weatherInfo) throws DocumentException;

	public String fetchForecast(String weatherInfo) throws DocumentException;
}
