package com.scenic.model;

import com.scenic.repo.pojo.DetectionPoint;
import com.scenic.repo.pojo.ScenicSpot;

public class MDetectionPoint {
	private Integer detectionPointNo;
	private Integer scenicSpotNo;
	private String detectionPointName;
	private Double detectionPointLongitude;
	private Double detectionPointLatitude;
	public MDetectionPoint(){
		
	}

	public MDetectionPoint(DetectionPoint dPoint) {
		if (dPoint.getDetectionPointNo() != null)
			this.detectionPointNo = dPoint.getDetectionPointNo();

		if (dPoint.getScenicSpot().getScenicSpotNo() != null)
			this.scenicSpotNo = dPoint.getScenicSpot().getScenicSpotNo();

		if (dPoint.getDetectionPointName() != null)
			this.detectionPointName = dPoint.getDetectionPointName();

		if (dPoint.getDetectionPointLongitude() != null)
			this.detectionPointLongitude = dPoint.getDetectionPointLongitude();

		if (dPoint.getDetectionPointLatitude() != null)
			this.detectionPointLatitude = dPoint.getDetectionPointLatitude();

	}

	public Integer getDetectionPointNo() {
		return detectionPointNo;
	}

	public void setDetectionPointNo(Integer detectionPointNo) {
		this.detectionPointNo = detectionPointNo;
	}

	public Integer getScenicSpotNo() {
		return scenicSpotNo;
	}

	public void setScenicSpotNo(Integer scenicSpotNo) {
		this.scenicSpotNo = scenicSpotNo;
	}

	public String getDetectionPointName() {
		return detectionPointName;
	}

	public void setDetectionPointName(String detectionPointName) {
		this.detectionPointName = detectionPointName;
	}

	public Double getDetectionPointLongitude() {
		return detectionPointLongitude;
	}

	public void setDetectionPointLongitude(Double detectionPointLongitude) {
		this.detectionPointLongitude = detectionPointLongitude;
	}

	public Double getDetectionPointLatitude() {
		return detectionPointLatitude;
	}

	public void setDetectionPointLatitude(Double detectionPointLatitude) {
		this.detectionPointLatitude = detectionPointLatitude;
	}

}
