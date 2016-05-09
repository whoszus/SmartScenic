package com.scenic.service;

import java.util.List;

import com.scenic.model.MArea;

public interface IAreaFindService {
	/**
	 * 显示所有的城市
	 * 
	 * @return
	 */
	public String showCity();

	/**
	 * 显示所有的省份
	 * 
	 * @return
	 */
	public List<MArea> showProvinces();

	/**
	 * 显示一个省份下的城市
	 * 
	 * @return
	 */
	public List<MArea> showCityUnderProvince(Integer provinceId);

}
