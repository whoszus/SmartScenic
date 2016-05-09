package com.scenic.service;

import java.util.List;

import com.scenic.model.MDetectionPoint;

public interface IDetectionPoinSercive {
	/**
	 * 查看某一景点下的所有监测点
	 * @param scenicSpotNo
	 * @return
	 */
	public List<MDetectionPoint> findByScenicSpotNo(Integer scenicSpotNo); 
	
	/**
	 * 添加一个监测点
	 * @param mPoint
	 * @return
	 */
	public MDetectionPoint addDetectionPoint(MDetectionPoint mPoint);
	
	/**
	 * 判断同一景点下是否已经存在同名的监测点
	 * @param scenicSpotNo
	 * @param detectionPointNames
	 * @return
	 */
	public Boolean IsExisted(Integer scenicSpotNo,String detectionPointNames );
	
	/**
	 * 更新监测点信息
	 * @param mpoint
	 * @return
	 */
	public MDetectionPoint updateDetectionPoint(MDetectionPoint mpoint);
	
	public void deleteDetectionPoint(Integer detectionNo);

}
