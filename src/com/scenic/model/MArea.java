package com.scenic.model;

import java.util.ArrayList;
import java.util.List;

import com.scenic.repo.pojo.City;
import com.scenic.repo.pojo.Province;

public class MArea {

	private Integer provinceId;
	private String provinceName;

	private Integer cityId;
	private String cityName;

	private List<Integer> cityIds;
	private List<String> cityNames;

	public MArea() {

	}

	public MArea(String provinveName, List<City> cities) {
		super();
		this.provinceName = provinveName;
		cityIds = new ArrayList<Integer>();
		cityNames = new ArrayList<String>();
		for (City c : cities) {
			this.cityIds.add(c.getCityId());
			this.cityNames.add(c.getCityName());
		}
	}

	public void setProvince(Province province) {
		this.provinceId = province.getProvinceId();
		this.provinceName = province.getProvinceName();
	}

	public void setCity(City city) {
		this.cityId = city.getCityId();
		this.cityName = city.getCityName();
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public List<Integer> getCityIds() {
		return cityIds;
	}

	public void setCityIds(List<Integer> cityIds) {
		this.cityIds = cityIds;
	}

	public List<String> getCityNames() {
		return cityNames;
	}

	public void setCityNames(List<String> cityNames) {
		this.cityNames = cityNames;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

}
