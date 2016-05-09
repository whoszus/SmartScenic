package com.scenic.service;

import java.util.List;

import net.sf.json.JSON;

import com.scenic.repo.pojo.DayData;

public interface IDayDataService {

	// public JSON findByTime(Integer scenicSpotNo,int days);

	// public DayData calculate(List<DayData> datas);

	public String showAqiAndComf(int scenicSpotNo);

	public String showOtherIndex(int scenicSpotNo);
}
