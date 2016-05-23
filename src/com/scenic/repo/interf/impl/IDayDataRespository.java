package com.scenic.repo.interf.impl;

import com.scenic.repo.pojo.DayData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface IDayDataRespository extends JpaRepository<DayData, Integer> {

	/*
	 * 通过景点获取某一天的数据
	 */
	@Query("select d from DayData d where d.scenicSpot.scenicSpotNo = ?1 and d.ddTime= ?2")
	public DayData findBySpotAndTime(Integer scenicSpotNo, Date time1);
}
