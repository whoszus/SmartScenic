package com.scenic.service;

import java.util.List;

import net.sf.json.JSON;

import com.scenic.model.MScenicSpot;
import com.scenic.model.MSpotList;
import com.scenic.repo.pojo.DayData;
import com.scenic.repo.pojo.ScenicSpot;

public interface IScenicSpotService {

	public String choiceSpotbyCity(String cityName);

	public String choiceSpotDefault(String cityName);

	public List<DayData> fetchDatas(List<ScenicSpot> spots);

	public String userDefined(Integer scenicSpotNo);

	public String spotRecommend(String cityName);

	public String showSpotDatas(Integer scenicSpotNo);

	/**
	 * 添加景点并回显
	 * 
	 * @param mSpot
	 * @return
	 */
	public MScenicSpot addScenicSpot(MScenicSpot mSpot);

	/**
	 * 判断同一个城市下是否已经存在景点名相同的景点
	 * @param cityId
	 * @param scenicSpotName
	 * @return
	 */
	public Boolean IsSameSpot(Integer cityId, String scenicSpotName);
	
	/**
	 * 更改景点信息
	 * @param mSpot
	 * @return
	 */
	public MScenicSpot updateScenicSpot(MScenicSpot mSpot);
	
	public void deleteScenicSpot(Integer scenicSpotNo);

}
