package com.scenic.model;

import java.util.ArrayList;
import java.util.List;

import com.scenic.repo.pojo.City;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class MCity {
	
	private String provinceName;
	private List<Integer> cityId = new ArrayList<Integer>();
	
	private List<String>  cityName=new ArrayList<String>();
	
	public MCity(String provinveName, List<City> cities){
		this.provinceName=provinveName;
		for(City c:cities){
			this.cityId.add(c.getCityId());
			this.cityName.add(c.getCityName());
		}	
	}
	
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	
	public List<Integer> getCityId() {
		return cityId;
	}

	public void setCityId(List<Integer> cityId) {
		this.cityId = cityId;
	}

	public List<String> getCityName() {
		return cityName;
	}
	public void setCityName(List<String> cityName) {
		this.cityName = cityName;
	}

	
	@Override
	public String toString() {
		return "MCity [provinceName=" + provinceName+ ", cityName=" + cityName
				+ ", cityId=" +cityId  + "]";
	}
	
}
