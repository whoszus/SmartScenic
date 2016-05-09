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

import com.scenic.model.MScenicSpot;

/**
 * ScenicSpot entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "scenic_spot", catalog = "smartscenic1")
public class ScenicSpot implements java.io.Serializable {

	// Fields

	private Integer scenicSpotNo;
	private String scenicSpotName;
	private String scenicPhoto;
	private City city;
	private String scenicSpotShortInfo;
	private String scenicInfo;
	private Double scenicSpoMinLongitude;
	private Double scenicSpotMinLatitude;
	private Double scenicSpotMaxLongitude;
	private Double scenicSpotMaxLatitude;
	private Set<DetectionPoint> detectionPoints = new HashSet<DetectionPoint>(0);
	private Set<DayData> dayDatas = new HashSet<DayData>(0);
	private Set<Review> reviews = new HashSet<Review>(0);

	// Constructors

	public ScenicSpot(MScenicSpot mSpot, City city) {
		super();
		if (mSpot.getScenicSpotNo() != null)
			this.scenicSpotNo = mSpot.getScenicSpotNo();
		if (mSpot.getScenicSpotName() != null)
			this.scenicSpotName = mSpot.getScenicSpotName();

		if (city != null)
			this.city = city;
		if (mSpot.getScenicPhoto() != null)
			this.scenicPhoto = mSpot.getScenicPhoto();

		if (mSpot.getScenicSpotShortInfo() != null)
			this.scenicSpotShortInfo = mSpot.getScenicSpotShortInfo();

		if (mSpot.getScenicInfo() != null)
			this.scenicInfo = mSpot.getScenicInfo();
		if (mSpot.getScenicSpoMinLongitude() != null)
			this.scenicSpoMinLongitude = mSpot.getScenicSpoMinLongitude();
		
		if (mSpot.getScenicSpotMinLatitude() != null)
			this.scenicSpotMinLatitude = mSpot.getScenicSpotMinLatitude();
		
		if (mSpot.getScenicSpotMaxLongitude() != null)
			this.scenicSpotMaxLongitude = mSpot.getScenicSpotMaxLongitude();
		
		if (mSpot.getScenicSpotMaxLatitude() != null)
			this.scenicSpotMaxLatitude = mSpot.getScenicSpotMaxLatitude();
	}

	/** default constructor */
	public ScenicSpot() {
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "scenic_spot_no", unique = true, nullable = false)
	public Integer getScenicSpotNo() {
		return this.scenicSpotNo;
	}

	public void setScenicSpotNo(Integer scenicSpotNo) {
		this.scenicSpotNo = scenicSpotNo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Column(name = "scenic_spot_name", length = 32)
	public String getScenicSpotName() {
		return this.scenicSpotName;
	}

	public void setScenicSpotName(String scenicSpotName) {
		this.scenicSpotName = scenicSpotName;
	}

	@Column(name = "scenic_photo")
	public String getScenicPhoto() {
		return this.scenicPhoto;
	}

	public void setScenicPhoto(String scenicPhoto) {
		this.scenicPhoto = scenicPhoto;
	}

	@Column(name = "scenic_spot_short_info", length = 65535)
	public String getScenicSpotShortInfo() {
		return this.scenicSpotShortInfo;
	}

	public void setScenicSpotShortInfo(String scenicSpotShortInfo) {
		this.scenicSpotShortInfo = scenicSpotShortInfo;
	}

	@Column(name = "scenic_info", length = 65535)
	public String getScenicInfo() {
		return this.scenicInfo;
	}

	public void setScenicInfo(String scenicInfo) {
		this.scenicInfo = scenicInfo;
	}

	@Column(name = "scenic_spo_min_longitude", precision = 22, scale = 0)
	public Double getScenicSpoMinLongitude() {
		return this.scenicSpoMinLongitude;
	}

	public void setScenicSpoMinLongitude(Double scenicSpoMinLongitude) {
		this.scenicSpoMinLongitude = scenicSpoMinLongitude;
	}

	@Column(name = "scenic_spot_min_latitude", precision = 22, scale = 0)
	public Double getScenicSpotMinLatitude() {
		return this.scenicSpotMinLatitude;
	}

	public void setScenicSpotMinLatitude(Double scenicSpotMinLatitude) {
		this.scenicSpotMinLatitude = scenicSpotMinLatitude;
	}

	@Column(name = "scenic_spot_max_longitude", precision = 22, scale = 0)
	public Double getScenicSpotMaxLongitude() {
		return this.scenicSpotMaxLongitude;
	}

	public void setScenicSpotMaxLongitude(Double scenicSpotMaxLongitude) {
		this.scenicSpotMaxLongitude = scenicSpotMaxLongitude;
	}

	@Column(name = "scenic_spot_max_latitude", precision = 22, scale = 0)
	public Double getScenicSpotMaxLatitude() {
		return this.scenicSpotMaxLatitude;
	}

	public void setScenicSpotMaxLatitude(Double scenicSpotMaxLatitude) {
		this.scenicSpotMaxLatitude = scenicSpotMaxLatitude;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "scenicSpot")
	public Set<DetectionPoint> getDetectionPoints() {
		return this.detectionPoints;
	}

	public void setDetectionPoints(Set<DetectionPoint> detectionPoints) {
		this.detectionPoints = detectionPoints;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "scenicSpot")
	public Set<DayData> getDayDatas() {
		return this.dayDatas;
	}

	public void setDayDatas(Set<DayData> dayDatas) {
		this.dayDatas = dayDatas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "scenicSpot")
	public Set<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

}