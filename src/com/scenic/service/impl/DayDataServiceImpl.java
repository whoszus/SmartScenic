package com.scenic.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scenic.baseUitl.DateUtil;
import com.scenic.model.MAqiAndComf;
import com.scenic.model.MOtherIndex;
import com.scenic.repo.interf.impl.IDayDataRespository;
import com.scenic.repo.pojo.DayData;
import com.scenic.service.IDayDataService;

@Service("dayDataService")
public class DayDataServiceImpl implements IDayDataService {

	@Autowired
	private IDayDataRespository dRespository;

	@Override
	/**
	 * 获取30天的aqi和舒适度
	 * 
	 * 思想：先传入景点编号，通过景点编号找到前30天的数据，然后取出数据中的时间，aqi，舒适度，
	 * 封装进model中，再转为String类型
	 * 
	 */
	public String showAqiAndComf(int scenicSpotNo) {
		List<DayData> dayDataList = new ArrayList<DayData>();

		String Day = DateUtil.getCurrentDate();
		int dayNum = 30;
		while (dayNum > 0) {
			Day = DateUtil.getSpecifiedDayBefore(Day);
			DayData d = new DayData();
			d = dRespository.findBySpotAndTime(scenicSpotNo, DateUtil
					.StringToDate(Day));
			if (d  != null)
				dayDataList.add(d);

			dayNum--;
		}
		if (dayDataList.size()==0 || dayDataList.isEmpty())
			return null;
		MAqiAndComf mAqiAndComf = new MAqiAndComf(dayDataList);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(mAqiAndComf);

		return json;

	}

	@Override
	/**
	 * 获取某景点7天中的其他数据
	 * 思想：通过景点编号，找到该景点最近7天的数据，然后取出时间，温度，湿度，紫外线，
	 * 含氧量等数据封装进model中，最后转为String类型
	 * 
	 */
	public String showOtherIndex(int scenicSpotNo) {
		List<DayData> dayDataList = new ArrayList<DayData>();
		String Day = DateUtil.getCurrentDate();
		int dayNum = 7;
		while (dayNum > 0) {
			Day = DateUtil.getSpecifiedDayBefore(Day);
			DayData d = new DayData();
			d = dRespository.findBySpotAndTime(scenicSpotNo, DateUtil
					.StringToDate(Day));
			if (d != null) {
				dayDataList.add(d);
			}
			dayNum--;
		}
		if (dayDataList.size()==0 || dayDataList.isEmpty())
			return null;

		MOtherIndex mIndex = new MOtherIndex(dayDataList);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(mIndex);

		return json;
	}

}
