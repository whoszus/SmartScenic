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

/**
 * City entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "city", catalog = "smartscenic1")
public class City implements java.io.Serializable {

	// Fields

	private Integer cityId;
	private Province province;
	private String cityName;
	private Set<ScenicSpot> scenicSpots = new HashSet<ScenicSpot>(0);

	// Constructors

	/** default constructor */
	public City() {
	}

	/** minimal constructor */
	public City(String cityName) {
		this.cityName = cityName;
	}

	/** full constructor */
	public City(Province province, String cityName, Set<ScenicSpot> scenicSpots) {
		this.province = province;
		this.cityName = cityName;
		this.scenicSpots = scenicSpots;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "city_id", unique = true, nullable = false)
	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "province_id")
	public Province getProvince() {
		return this.province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	@Column(name = "city_name", nullable = false, length = 30)
	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "city")
	public Set<ScenicSpot> getScenicSpots() {
		return this.scenicSpots;
	}

	public void setScenicSpots(Set<ScenicSpot> scenicSpots) {
		this.scenicSpots = scenicSpots;
	}

}