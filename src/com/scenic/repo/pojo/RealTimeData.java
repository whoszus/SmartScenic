package com.scenic.repo.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * RealTimeData entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "real_time_data", catalog = "smartscenic1")
public class RealTimeData implements java.io.Serializable {

	// Fields

	private Integer rtdNo;
	private DetectionPoint detectionPoint;
	private Date rtdTime;
	private Float rtdTemperature;
	private Float rtdOxygenContent;
	private Float rtdAirSpeed;
	private Float rtdUltraviolet;
	private Float rtdHumidity;
	private Float rtdPm10;
	private Float rtdPm25;
	private Float rtdNo2;
	private Float rtdSo2;
	private Float rtdCo;
	private Float rtdO3;

	// Constructors

	/** default constructor */
	public RealTimeData() {
	}

	/** full constructor */
	public RealTimeData(DetectionPoint detectionPoint, Date rtdTime,
			Float rtdTemperature, Float rtdOxygenContent, Float rtdAirSpeed,
			Float rtdUltraviolet, Float rtdHumidity, Float rtdPm10,
			Float rtdPm25, Float rtdNo2, Float rtdSo2, Float rtdCo, Float rtdO3) {
		this.detectionPoint = detectionPoint;
		this.rtdTime = rtdTime;
		this.rtdTemperature = rtdTemperature;
		this.rtdOxygenContent = rtdOxygenContent;
		this.rtdAirSpeed = rtdAirSpeed;
		this.rtdUltraviolet = rtdUltraviolet;
		this.rtdHumidity = rtdHumidity;
		this.rtdPm10 = rtdPm10;
		this.rtdPm25 = rtdPm25;
		this.rtdNo2 = rtdNo2;
		this.rtdSo2 = rtdSo2;
		this.rtdCo = rtdCo;
		this.rtdO3 = rtdO3;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "rtd_no", unique = true, nullable = false)
	public Integer getRtdNo() {
		return this.rtdNo;
	}

	public void setRtdNo(Integer rtdNo) {
		this.rtdNo = rtdNo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "detection_point_no")
	public DetectionPoint getDetectionPoint() {
		return this.detectionPoint;
	}

	public void setDetectionPoint(DetectionPoint detectionPoint) {
		this.detectionPoint = detectionPoint;
	}

	@Column(name = "rtd_time", length = 19)
	public Date getRtdTime() {
		return this.rtdTime;
	}

	@Override
	public String toString() {
		return "RealTimeData{" +
				"rtdNo=" + rtdNo +
				", detectionPoint=" + detectionPoint +
				", rtdTime=" + rtdTime +
				", rtdTemperature=" + rtdTemperature +
				", rtdOxygenContent=" + rtdOxygenContent +
				", rtdAirSpeed=" + rtdAirSpeed +
				", rtdUltraviolet=" + rtdUltraviolet +
				", rtdHumidity=" + rtdHumidity +
				", rtdPm10=" + rtdPm10 +
				", rtdPm25=" + rtdPm25 +
				", rtdNo2=" + rtdNo2 +
				", rtdSo2=" + rtdSo2 +
				", rtdCo=" + rtdCo +
				", rtdO3=" + rtdO3 +
				'}';
	}

	public void setRtdTime(Date rtdTime) {
		this.rtdTime = rtdTime;
	}

	@Column(name = "rtd_temperature", precision = 12, scale = 0)
	public Float getRtdTemperature() {
		return this.rtdTemperature;
	}

	public void setRtdTemperature(Float rtdTemperature) {
		this.rtdTemperature = rtdTemperature;
	}

	@Column(name = "rtd_oxygen_content", precision = 12, scale = 0)
	public Float getRtdOxygenContent() {
		return this.rtdOxygenContent;
	}

	public void setRtdOxygenContent(Float rtdOxygenContent) {
		this.rtdOxygenContent = rtdOxygenContent;
	}

	@Column(name = "rtd_air_speed", precision = 12, scale = 0)
	public Float getRtdAirSpeed() {
		return this.rtdAirSpeed;
	}

	public void setRtdAirSpeed(Float rtdAirSpeed) {
		this.rtdAirSpeed = rtdAirSpeed;
	}

	@Column(name = "rtd_ultraviolet", precision = 12, scale = 0)
	public Float getRtdUltraviolet() {
		return this.rtdUltraviolet;
	}

	public void setRtdUltraviolet(Float rtdUltraviolet) {
		this.rtdUltraviolet = rtdUltraviolet;
	}

	@Column(name = "rtd_humidity", precision = 12, scale = 0)
	public Float getRtdHumidity() {
		return this.rtdHumidity;
	}

	public void setRtdHumidity(Float rtdHumidity) {
		this.rtdHumidity = rtdHumidity;
	}

	@Column(name = "rtd_pm10", precision = 12, scale = 0)
	public Float getRtdPm10() {
		return this.rtdPm10;
	}

	public void setRtdPm10(Float rtdPm10) {
		this.rtdPm10 = rtdPm10;
	}

	@Column(name = "rtd_pm25", precision = 12, scale = 0)
	public Float getRtdPm25() {
		return this.rtdPm25;
	}

	public void setRtdPm25(Float rtdPm25) {
		this.rtdPm25 = rtdPm25;
	}

	@Column(name = "rtd_no2", precision = 12, scale = 0)
	public Float getRtdNo2() {
		return this.rtdNo2;
	}

	public void setRtdNo2(Float rtdNo2) {
		this.rtdNo2 = rtdNo2;
	}

	@Column(name = "rtd_so2", precision = 12, scale = 0)
	public Float getRtdSo2() {
		return this.rtdSo2;
	}

	public void setRtdSo2(Float rtdSo2) {
		this.rtdSo2 = rtdSo2;
	}

	@Column(name = "rtd_co", precision = 12, scale = 0)
	public Float getRtdCo() {
		return this.rtdCo;
	}

	public void setRtdCo(Float rtdCo) {
		this.rtdCo = rtdCo;
	}

	@Column(name = "rtd_o3", precision = 12, scale = 0)
	public Float getRtdO3() {
		return this.rtdO3;
	}

	public void setRtdO3(Float rtdO3) {
		this.rtdO3 = rtdO3;
	}

}