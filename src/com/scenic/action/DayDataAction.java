package com.scenic.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.scenic.baseUitl.WriteJson;
import com.scenic.service.IDayDataService;

public class DayDataAction extends ActionSupport {
	@Autowired
	private IDayDataService dayDataService;

	private Integer scenicSpotNo;

	// http://localhost:8080/SmartScenic/dayData/dayData_showAqiAndComf.action?scenicSpotNo=1
	public void showAqiAndComf() {
		String json = dayDataService.showAqiAndComf(scenicSpotNo);

		WriteJson.writeToClient(json);
	}

	// http://localhost:8080/SmartScenic/dayData/dayData_showOtherIndex.action?scenicSpotNo=1
	public void showOtherIndex() {
		String json = dayDataService.showOtherIndex(scenicSpotNo).toString();
		WriteJson.writeToClient(json);
	}

	public Integer getScenicSpotNo() {
		return scenicSpotNo;
	}

	public void setScenicSpotNo(Integer scenicSpotNo) {
		this.scenicSpotNo = scenicSpotNo;
	}

}
