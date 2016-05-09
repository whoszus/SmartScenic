package com.scenic.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.scenic.baseUitl.WriteJson;
import com.scenic.service.impl.MapServiceImp;

public class MapAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private MapServiceImp mImp;

	private Integer scenicSpotNo;

	/**
	 * http://luorong:8080/SmartScenic/map/map_ShowMapData.action?scenicSpotNo=1
	 */
	public void ShowMapData() {
		WriteJson.writeToClient(mImp.getPointAndAqi(scenicSpotNo));
	}

	public Integer getScenicSpotNo() {
		return scenicSpotNo;
	}

	public void setScenicSpotNo(Integer scenicSpotNo) {
		this.scenicSpotNo = scenicSpotNo;
	}

	/**
	 * http://luorong:8080/SmartScenic/map/map_showMap.action
	 */
	// public void showMap(){
	// double[][] x= {{100,150},{200,200},{300,320},{400,280}};
	// double []y={40,100,80,60};
	// String json =mImp.interpolation(1 , x, y).toString();
	// WriteJson.writeToClient(json);
	// }

}
