package com.scenic.repo.interf.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scenic.repo.pojo.ScenicSpot;

public interface IScenicSpotRespository extends
		JpaRepository<ScenicSpot, Integer> {

	@Query("select s from ScenicSpot s where s.scenicSpotNo = ?1")
	public ScenicSpot findBySpotNo(Integer scenicSpotNo);

	@Query("select s from ScenicSpot s where s.city.cityName like  ?1%")
	public List<ScenicSpot> findByCityName(String cityName);

	// 通过景区的名字和景区所在的城市查找景区
	@Query("select s from ScenicSpot s where s.city.cityName like  ?1% and s.scenicSpotName = ?2 ")
	public ScenicSpot findByCityAndName(String cityName, String scenicSpotName);

	@Query("select s from ScenicSpot s where s.city.cityId =  ?1 and s.scenicSpotName = ?2 ")
	public ScenicSpot findByCityAndId(Integer cityId, String scenicSpotName);

}
