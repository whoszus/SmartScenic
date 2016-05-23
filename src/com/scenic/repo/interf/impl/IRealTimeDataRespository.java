package com.scenic.repo.interf.impl;

import com.scenic.repo.pojo.RealTimeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface IRealTimeDataRespository extends
		JpaRepository<RealTimeData, Integer> {

	/*
	 * 通过景点和时间获取实时数据
	 */
	@Query("select r from RealTimeData r where  r.detectionPoint.scenicSpot.scenicSpotNo= ?1 and r.rtdTime > ?2 and r.rtdTime < ?3 ")
	public List<RealTimeData> findByTime(Integer scenicSpotNo, Date time1,
			Date time2);

	@Query("select r from RealTimeData r where  r.detectionPoint.detectionPointNo = ?1 and r.rtdTime > ?2  order by r.rtdNo DESC ")
	public List<RealTimeData> findByLastTime(Integer detectionPointNo, Date time1);

//	public List<RealTimeData> getDataByid(int )
}