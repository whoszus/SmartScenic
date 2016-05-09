package com.scenic.service;

import java.util.List;

import com.scenic.model.MSpotSort;
import com.scenic.repo.pojo.DayData;

public interface ISortService {
	/**
	 * 计算多个DayData的值
	 * 
	 * @param datas
	 * @return
	 */
	public MSpotSort calcalculateAvg(List<DayData> datas);// 处理数据

	public MSpotSort fetchDatas(int ScenicSpotNo, int days);// 获取某个景点的数据

	public String sortByAqi(int dayNum, String cityName);

	public String sortByComfortLevel(int dayNum, String cityName);

	public String sortByComprehensive(int dayNum, String cityName);

}
