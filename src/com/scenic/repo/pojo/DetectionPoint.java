package com.scenic.repo.pojo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.scenic.model.MDetectionPoint;

/**
 * DetectionPoint entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "detection_point", catalog = "smartscenic1")
public class DetectionPoint implements java.io.Serializable {

	// Fields

	private Integer detectionPointNo;
	private ScenicSpot scenicSpot;
	private String detectionPointName;
	private Double detectionPointLongitude;
	private Double detectionPointLatitude;
	private Set<RealTimeData> realTimeDatas = new HashSet<RealTimeData>(0);

	public DetectionPoint(MDetectionPoint mPoint, ScenicSpot scenicSpot) {
		if (mPoint.getDetectionPointNo() != null)
			this.detectionPointNo = mPoint.getDetectionPointNo();

		if (scenicSpot != null)
			this.scenicSpot = scenicSpot;

		if (mPoint.getDetectionPointName() != null)
			this.detectionPointName = mPoint.getDetectionPointName();

		if (mPoint.getDetectionPointLongitude() != null)
			this.detectionPointLongitude = mPoint.getDetectionPointLongitude();

		if (mPoint.getDetectionPointLatitude() != null)
			this.detectionPointLatitude = mPoint.getDetectionPointLatitude();
	}

	// Constructors

	/** default constructor */
	public DetectionPoint() {
	}

	/** full constructor */
	public DetectionPoint(ScenicSpot scenicSpot, String detectionPointName,
			Double detectionPointLongitude, Double detectionPointLatitude,
			Set<RealTimeData> realTimeDatas) {
		this.scenicSpot = scenicSpot;
		this.detectionPointName = detectionPointName;
		this.detectionPointLongitude = detectionPointLongitude;
		this.detectionPointLatitude = detectionPointLatitude;
		this.realTimeDatas = realTimeDatas;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "detection_point_no", unique = true, nullable = false)
	public Integer getDetectionPointNo() {
		return this.detectionPointNo;
	}

	public void setDetectionPointNo(Integer detectionPointNo) {
		this.detectionPointNo = detectionPointNo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "scenic_spot_no")
	public ScenicSpot getScenicSpot() {
		return this.scenicSpot;
	}

	public void setScenicSpot(ScenicSpot scenicSpot) {
		this.scenicSpot = scenicSpot;
	}

	@Column(name = "detection_point_name", length = 30)
	public String getDetectionPointName() {
		return this.detectionPointName;
	}

	public void setDetectionPointName(String detectionPointName) {
		this.detectionPointName = detectionPointName;
	}

	@Column(name = "detection_point_longitude", precision = 22, scale = 0)
	public Double getDetectionPointLongitude() {
		return this.detectionPointLongitude;
	}

	public void setDetectionPointLongitude(Double detectionPointLongitude) {
		this.detectionPointLongitude = detectionPointLongitude;
	}

	@Column(name = "detection_point_latitude", precision = 22, scale = 0)
	public Double getDetectionPointLatitude() {
		return this.detectionPointLatitude;
	}

	public void setDetectionPointLatitude(Double detectionPointLatitude) {
		this.detectionPointLatitude = detectionPointLatitude;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "detectionPoint")
	public Set<RealTimeData> getRealTimeDatas() {
		return this.realTimeDatas;
	}

	public void setRealTimeDatas(Set<RealTimeData> realTimeDatas) {
		this.realTimeDatas = realTimeDatas;
	}

}