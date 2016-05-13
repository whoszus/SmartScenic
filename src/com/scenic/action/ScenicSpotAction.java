package com.scenic.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.scenic.baseUitl.CharacterConversion;
import com.scenic.baseUitl.WriteJson;
import com.scenic.model.MScenicSpot;
import com.scenic.service.impl.ScenicSpotServiceImp;

public class ScenicSpotAction extends ActionSupport implements
		ModelDriven<MScenicSpot> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ScenicSpotServiceImp sServiceImp;

	private MScenicSpot spot = new MScenicSpot();



	/**
	 *
	 * http://localhost:8080/SmartScenic/scenic/scenic_findByCityName.action?cityName=绵阳市
	 * 获取某个市下面的所有景区
	 * 
	 */
	public void findByCityName() {
		String json = sServiceImp.choiceSpotbyCity(spot.getCityName());
		WriteJson.writeToClient(json);
	}

	/**
	 * 默认选择获取某一市下aqi排行第一的景点
	 * http://localhost:8080/SmartScenic/scenic/scenic_choiceDefault.action?cityName=绵阳市
	 */
	public void choiceDefault() {

		String str = sServiceImp.choiceSpotDefault(spot.getCityName());
		WriteJson.writeToClient(str);
	}

	/**
	 * 显示用户自定义选择的景点信息
	 * http://localhost:8080/SmartScenic/scenic/scenic_userDefined.action?scenicSpotNo=2
	 */
	public void userDefined() {
		String str = sServiceImp.userDefined(spot.getScenicSpotNo());
		WriteJson.writeToClient(str);
	}

	/**
	 * 景点推荐
	 * http://localhost:8080/SmartScenic/scenic/scenic_spotRecommend.action?cityName=绵阳市
	 */
	public void spotRecommend() {
		String str = sServiceImp.spotRecommend(spot.getCityName());
		WriteJson.writeToClient(str);
	}

	/**
	 * 获取某景区的信息及数据
	 * http://localhost:8080/SmartScenic/scenic/scenic_showSpotDatas.action?scenicSpotNo=1
	 */
	public void showSpotDatas() {

		String str = sServiceImp.showSpotDatas(spot.getScenicSpotNo());
		WriteJson.writeToClient(str);
	}

	/**
	 * 添加景点
	 * http://localhost:8080/SmartScenic/scenic/scenic_addScenicSpot.action?scenicSpotName=仙女湖&cityId=2
	 */
	public void addScenicSpot() {
		String json;
		if (sServiceImp.IsSameSpot(spot.getCityId(), spot.getScenicSpotName())) {
			json = "{\"message\":\"existed\"}";
		} else {
			MScenicSpot mSpot = sServiceImp.addScenicSpot(spot);
			json = new Gson().toJson(mSpot);
		}
		WriteJson.writeToClient(json);
	}
	
	/**
	 * 更新景点信息
	 *  http://localhost:8080/SmartScenic/scenic/scenic_updateScenicSpot.action?scenicSpotNo=28
	 */
	public void updateScenicSpot(){
		MScenicSpot mScenicSpot = sServiceImp.updateScenicSpot(spot);
		if(mScenicSpot==null) return;
		String json= new Gson().toJson(mScenicSpot);
		WriteJson.writeToClient(json);
		
	}
	
	/**
	 * 删除景点及其相关信息
	 * http://localhost:8080/SmartScenic/scenic/scenic_deleteScenicSpot.action?scenicSpotNo=28
	 */
	public void deleteScenicSpot(){
		String json = null;
		
		try {
			sServiceImp.deleteScenicSpot(spot.getScenicSpotNo());
			 json = "{\"message\":\"successed\"}";
		} catch (Exception e) {
			json = "{\"message\":\"failed\"}";
		}finally{
			WriteJson.writeToClient(json);
		}
		
	}



	@Override
	public MScenicSpot getModel() {
		return spot;
	}

}
