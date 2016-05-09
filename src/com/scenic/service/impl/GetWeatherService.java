package com.scenic.service.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.scenic.model.MWeatherForecast;
import com.scenic.model.MZhishu;
import com.scenic.service.IGetweatherService;

@Service("getWeatherService")
public class GetWeatherService implements IGetweatherService {

	private int getShort(byte[] data) {
		return (int) ((data[0] << 8) | data[1] & 0xFF);
	}

	@Override
	/**
	 * 思想：通过url连接到该api，然后得到他传过来的数据，在解压，转化成string类型
	 */
	public String getWeatherinfoStr(String cityName) throws IOException {
		URL urlObj = new URL("http://wthrcdn.etouch.cn/WeatherApi?city="
				+ URLEncoder.encode(cityName, "utf-8"));
		String weatherInfo = null;
		URLConnection urlConn = urlObj.openConnection();// 打开链接
		// urlConn.setConnectTimeout(2000);
		try {
			InputStream is = urlConn.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			bis.mark(2);
			// 取前两个字节
			byte[] header = new byte[2];
			int result = bis.read(header);
			// reset输入流到开始位置
			bis.reset();
			// 判断是否是GZIP格式
			int headerData = getShort(header);
			if (result != -1 && headerData == 0x1f8b) {
				is = new GZIPInputStream(bis);
			} else {
				is = bis;
			}
			InputStreamReader reader = new InputStreamReader(is, "utf-8");
			char[] data = new char[100];
			int readSize;
			StringBuffer sb = new StringBuffer();
			while ((readSize = reader.read(data)) > 0) {
				sb.append(data, 0, readSize);
			}
			weatherInfo = sb.toString();
			bis.close();
			reader.close();
		} catch (Exception e) {

		}
		return weatherInfo;
	}

	@Override
	/**
	 * 思想：解析得到的天气字符串，从中得到需要的指数
	 */
	public String fetchZhishu(String weatherInfo) throws DocumentException {

		// 产生一个解析器对象
		SAXReader reader = new SAXReader();
		reader.setEncoding("gbk");

		// 将xml文档转换为Document的对象
		Document document = reader.read(new ByteArrayInputStream(weatherInfo
				.getBytes()));

		// 获取文档的根元素
		Element root = document.getRootElement();
		String city = root.elementText("city");
		List<MZhishu> zhishusList = new ArrayList<MZhishu>();
		Element zhishus = root.element("zhishus");

		// 获取指数
		for (Iterator i_zs = zhishus.elementIterator(); i_zs.hasNext();) {
			Element e_zs = (Element) i_zs.next();
			String name = e_zs.element("name").getText();
			String value = e_zs.elementText("value");
			String detail = e_zs.elementText("detail");
			MZhishu zs = new MZhishu(name, value, detail);
			zhishusList.add(zs);
		}
		return new Gson().toJson(zhishusList);
	}

	@Override
	/**
	 * 思想：解析得到的天气字符串，然后取出需要的天气预报的信息，最后封装进model中，转为String
	 */
	public String fetchForecast(String weatherInfo) throws DocumentException {

		// 产生一个解析器对象
		SAXReader reader = new SAXReader();
		reader.setEncoding("gbk");
		// 将xml文档转换为Document的对象
		Document document = reader.read(new ByteArrayInputStream(weatherInfo
				.getBytes()));
		// 获取文档的根元素
		Element root = document.getRootElement();
		List<MWeatherForecast> forecastsList = new ArrayList<MWeatherForecast>();
		Element forecast = root.element("forecast");

		// 获取未来的天气
		for (Iterator i_fc = forecast.elementIterator(); i_fc.hasNext();) {
			Element e_fc = (Element) i_fc.next();
			String date = e_fc.element("date").getText();
			String low = e_fc.element("low").getText();
			low = low.replaceAll("低温 ", "");
			String high = e_fc.element("high").getText();
			high = high.replaceAll("高温 ", "");
			String temp = low + "~" + high;
			Element day = e_fc.element("day");
			String weather = day.elementText("type");
			String windForce = day.elementText("fengli");
			MWeatherForecast f = new MWeatherForecast(date, temp, weather,
					windForce);
			forecastsList.add(f);
		}
		return new Gson().toJson(forecastsList);
	}
}