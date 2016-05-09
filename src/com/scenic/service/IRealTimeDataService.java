package com.scenic.service;

import com.scenic.repo.pojo.DayData;

public interface IRealTimeDataService {
	// public void calculateAvg(int scenicSpotNo);

	// 计算一个景区的数据
	public void calculateScenicSpotData(int scenicSpotNo);

	/**
	 * 计算舒适度
	 * 
	 * @param t
	 *            平均气温
	 * @param f
	 *            相对湿度
	 * @param v
	 *            风速
	 * @return
	 */
	public float calculateSSD(float t, float f, float v);

	// 计算
	public DayData calculateAqi(float[] aqi, DayData dayData);

	// 计算Aqi分指数pm10
	public float calculateAqiSubIndexPm10(float pm10);

	// 计算Aqi分指数pm25
	public float calculateAqiSubIndexPm25(float pm25);

	// 计算Aqi分指数No2
	public float calculateAqiSubIndexNo2(float no2);

	// 计算Aqi分指数so2
	public float calculateAqiSubIndexSo2(float so2);

	// 计算Aqi分指数Co
	public float calculateAqiSubIndexCo(float co);

	// 计算Aqi分指数o3
	public float calculateAqiSubIndex03(float o3);
}
