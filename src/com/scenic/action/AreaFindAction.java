package com.scenic.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.scenic.baseUitl.WriteJson;
import com.scenic.model.MArea;
import com.scenic.service.impl.AreaFindServiceImpl;

public class AreaFindAction extends ActionSupport {
	@Autowired
	private AreaFindServiceImpl areaFindService;

	private Integer provinceId;

	/**
	 * 显示所有的城市
	 * http://luorong:8080/SmartScenic/areaFind/areaFind_showAllCity.action
	 */
	public void showAllCity() {
		String str = areaFindService.showCity();
		WriteJson.writeToClient(str);
	}

	/**
	 * 显示所有的省
	 * http://luorong:8080/SmartScenic/areaFind/areaFind_showProvinces.action
	 */
	public void showProvinces() {
		List<MArea> provinces = areaFindService.showProvinces();
		String gson = new Gson().toJson(provinces);
		WriteJson.writeToClient(gson);
	}

	/**
	 * 显示某一个省份下的城市
	 * http://luorong:8080/SmartScenic/areaFind/areaFind_showCity.action
	 * ?provinceId=2
	 */
	public void showCity() {
		List<MArea> provinces = areaFindService
				.showCityUnderProvince(provinceId);
		String gson = new Gson().toJson(provinces);
		WriteJson.writeToClient(gson);
	}

	public AreaFindServiceImpl getAreaFindService() {
		return areaFindService;
	}

	public void setAreaFindService(AreaFindServiceImpl areaFindService) {
		this.areaFindService = areaFindService;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

}
