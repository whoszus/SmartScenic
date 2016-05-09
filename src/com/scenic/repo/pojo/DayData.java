package com.scenic.repo.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * DayData entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "day_data", catalog = "smartscenic1")
public class DayData implements java.io.Serializable {

	// Fields

	private Integer ddNo;
	private ScenicSpot scenicSpot;
	private Date ddTime;
	private Float ddTemperature;
	private Float ddOxygenContent;
	private Float ddPm10;
	private Float ddPm25;
	private Float ddAirSpeed;
	private Float ddUlteraviolet;
	private Float ddHumidity;
	private Float ddAqi;
	private Float ddComfortLevel;
	private String ddMainPollution;
	private String ddAirPollutionIndex;

	// Constructors

	/** default constructor */
	public DayData() {
	}

	/** full constructor */
	public DayData(ScenicSpot scenicSpot, Date ddTime, Float ddTemperature,
			Float ddOxygenContent, Float ddPm10, Float ddPm25,
			Float ddAirSpeed, Float ddUlteraviolet, Float ddHumidity,
			Float ddAqi, Float ddComfortLevel, String ddMainPollution,
			String ddAirPollutionIndex) {
		this.scenicSpot = scenicSpot;
		this.ddTime = ddTime;
		this.ddTemperature = ddTemperature;
		this.ddOxygenContent = ddOxygenContent;
		this.ddPm10 = ddPm10;
		this.ddPm25 = ddPm25;
		this.ddAirSpeed = ddAirSpeed;
		this.ddUlteraviolet = ddUlteraviolet;
		this.ddHumidity = ddHumidity;
		this.ddAqi = ddAqi;
		this.ddComfortLevel = ddComfortLevel;
		this.ddMainPollution = ddMainPollution;
		this.ddAirPollutionIndex = ddAirPollutionIndex;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "dd_no", unique = true, nullable = false)
	public Integer getDdNo() {
		return this.ddNo;
	}

	public void setDdNo(Integer ddNo) {
		this.ddNo = ddNo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "scenic_spot_no")
	public ScenicSpot getScenicSpot() {
		return this.scenicSpot;
	}

	public void setScenicSpot(ScenicSpot scenicSpot) {
		this.scenicSpot = scenicSpot;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dd_time", length = 10)
	public Date getDdTime() {
		return this.ddTime;
	}

	public void setDdTime(Date ddTime) {
		this.ddTime = ddTime;
	}

	@Column(name = "dd_temperature", precision = 12, scale = 0)
	public Float getDdTemperature() {
		return this.ddTemperature;
	}

	public void setDdTemperature(Float ddTemperature) {
		this.ddTemperature = ddTemperature;
	}

	@Column(name = "dd_oxygen_content", precision = 12, scale = 0)
	public Float getDdOxygenContent() {
		return this.ddOxygenContent;
	}

	public void setDdOxygenContent(Float ddOxygenContent) {
		this.ddOxygenContent = ddOxygenContent;
	}

	@Column(name = "dd_pm10", precision = 12, scale = 0)
	public Float getDdPm10() {
		return this.ddPm10;
	}

	public void setDdPm10(Float ddPm10) {
		this.ddPm10 = ddPm10;
	}

	@Column(name = "dd_pm25", precision = 12, scale = 0)
	public Float getDdPm25() {
		return this.ddPm25;
	}

	public void setDdPm25(Float ddPm25) {
		this.ddPm25 = ddPm25;
	}

	@Column(name = "dd_air_speed", precision = 12, scale = 0)
	public Float getDdAirSpeed() {
		return this.ddAirSpeed;
	}

	public void setDdAirSpeed(Float ddAirSpeed) {
		this.ddAirSpeed = ddAirSpeed;
	}

	@Column(name = "dd_ulteraviolet", precision = 12, scale = 0)
	public Float getDdUlteraviolet() {
		return this.ddUlteraviolet;
	}

	public void setDdUlteraviolet(Float ddUlteraviolet) {
		this.ddUlteraviolet = ddUlteraviolet;
	}

	@Column(name = "dd_humidity", precision = 12, scale = 0)
	public Float getDdHumidity() {
		return this.ddHumidity;
	}

	public void setDdHumidity(Float ddHumidity) {
		this.ddHumidity = ddHumidity;
	}

	@Column(name = "dd_aqi", precision = 12, scale = 0)
	public Float getDdAqi() {
		return this.ddAqi;
	}

	public void setDdAqi(Float ddAqi) {
		this.ddAqi = ddAqi;
	}

	@Column(name = "dd_comfort_level", precision = 12, scale = 0)
	public Float getDdComfortLevel() {
		return this.ddComfortLevel;
	}

	public void setDdComfortLevel(Float ddComfortLevel) {
		this.ddComfortLevel = ddComfortLevel;
	}

	@Column(name = "dd_main_pollution", length = 15)
	public String getDdMainPollution() {
		return this.ddMainPollution;
	}

	public void setDdMainPollution(String ddMainPollution) {
		this.ddMainPollution = ddMainPollution;
	}

	@Column(name = "dd_air_pollution_index", length = 10)
	public String getDdAirPollutionIndex() {
		return this.ddAirPollutionIndex;
	}

	public void setDdAirPollutionIndex(String ddAirPollutionIndex) {
		this.ddAirPollutionIndex = ddAirPollutionIndex;
	}

}