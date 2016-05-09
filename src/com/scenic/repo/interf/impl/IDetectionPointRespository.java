package com.scenic.repo.interf.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scenic.repo.pojo.DetectionPoint;

public interface IDetectionPointRespository extends
		JpaRepository<DetectionPoint, Integer> {

	@Query("select dP from DetectionPoint dP where dP.scenicSpot.scenicSpotNo = ?1")
	public List<DetectionPoint> findByScenicSpotNo(Integer scenicSpotNo);
	
 	@Query("select dP from DetectionPoint dP where dP.scenicSpot.scenicSpotNo = ?1 and dP.detectionPointName = ?2")
	public DetectionPoint findByNameAndScenic(Integer scenicSpotNo,String detectionPointNames);
	
}
