package com.scenic.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.scenic.model.MArea;
import com.scenic.repo.interf.impl.ICityResponsitory;
import com.scenic.repo.interf.impl.IProvinceResponsitory;
import com.scenic.repo.pojo.City;
import com.scenic.repo.pojo.Province;
import com.scenic.service.IAreaFindService;

@Service("areaFindService")
public class AreaFindServiceImpl implements IAreaFindService {

	@Autowired
	private IProvinceResponsitory provinceResponsitory;
	@Autowired
	private ICityResponsitory cityResponsitory;

	@Override
	/**
	 * 思想：获取所有的省，再根据省找到省下面的市，最后封装进model后转为字符串
	 * 
	 */
	public String showCity() {
		List<Province> provinces = new ArrayList<Province>();
		List<MArea> mCities = new ArrayList<MArea>();

		provinces = provinceResponsitory.findAll();

		for (Province p : provinces) {
			List<City> cities = new ArrayList<City>();
			cities = cityResponsitory.finfByProvinceId(p.getProvinceId());
			MArea mCity = new MArea(p.getProvinceName(), cities);
			mCities.add(mCity);
		}
		return new Gson().toJson(mCities);
	}

	@Override
	public List<MArea> showCityUnderProvince(Integer provinceId) {
		List<City> cities = cityResponsitory.finfByProvinceId(provinceId);
		List<MArea> mcities = new ArrayList<MArea>();

		for (City c : cities) {
			MArea city = new MArea();
			city.setCity(c);
			mcities.add(city);
		}

		return mcities;
	}

	@Override
	public List<MArea> showProvinces() {

		List<Province> pList = provinceResponsitory.findAll();
		List<MArea> cities = new ArrayList<MArea>();
		if (pList.isEmpty() || pList.size() == 0) {
			return null;
		}
		for (Province p : pList) {
			MArea c = new MArea();
			c.setProvince(p);
			cities.add(c);
		}

		return cities;
	}

	/*
	 * @Override public String showProvinces() {
	 * 
	 * List<String> provinces = new ArrayList<String>();
	 * 
	 * List<Province> pList = new ArrayList<Province>();
	 * 
	 * pList = provinceResponsitory.findAll(); if(pList.isEmpty()){ return null;
	 * }
	 * 
	 * for (int i=0;i<pList.size();i++){
	 * provinces.add(pList.get(i).getProvinceName()); } return new
	 * Gson().toJson(provinces); }
	 * 
	 * 
	 * @Override public String showCityUnderProvince() {
	 * 
	 * 
	 * return null; }
	 */

}
