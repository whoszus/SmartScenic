package com.scenic.model;

public class MScenicSpotinfo {
	private Integer scenicSpotNo; // 景区编号
	private String scenicSpotName;// 景区名
	private String scenicPhoto;// 景区图片
	private Float ddAqi;// 景区aqi
	private String ddAirPollutionIndex;// 污染指数
	private String warmReminder;// 温馨提示

	public MScenicSpotinfo(Integer scenicSpotNo, String scenicSpotName,
			String scenicPhoto, Float ddAqi) {
		super();
		this.scenicSpotNo = scenicSpotNo;
		this.scenicSpotName = scenicSpotName;
		this.scenicPhoto = scenicPhoto;
		this.ddAqi = ddAqi;
		setPollution(ddAqi);
	}

	public void setPollution(float Aqi) {
		if (Aqi > 300) {
			this.ddAirPollutionIndex = "严重污染";
			this.warmReminder = "健康人运动耐受力降低,有明显强烈症状,提前出现某些疾病 儿童、老年人和病人应当留在室内,避免体力消耗,一般人群应避免户外活动";
		} else if (Aqi > 200) {
			this.ddAirPollutionIndex = "重度污染";
			this.warmReminder ="心脏病和肺病患者症状显著加剧,运动耐受力降低,健康人群普遍出现症状 儿童、老人和心脏病、肺病患者应停留在室内,停止户外运动,一般人群减少户外运动";
		} else if (Aqi > 150) {
			this.ddAirPollutionIndex = "中度污染";
			this.warmReminder = "进一步加剧易感人群症状,可能对健康人群心脏、呼吸系统有影响 儿童、老年人及心脏病、呼吸系统疾病患者避免长时间、高强度的户外锻炼,一般人群适量减少户外运动";
		} else if (Aqi > 100) {
			this.ddAirPollutionIndex = "轻度污染";
			this.warmReminder = "易感人群症状有轻度加剧,健康人群出现刺激症状 儿童、老年人及心脏病、呼吸系统疾病患者应减少长时间、高强度的户外锻炼";
		} else if (Aqi > 50) {
			this.ddAirPollutionIndex = "良";
			this.warmReminder = "空气质量可接受,但某些污染物可能对极少数异常敏感人群健康有较弱影响 极少数异常敏感人群应减少户外活动";
		} else if (Aqi > 0) {
			this.ddAirPollutionIndex = "优";
			this.warmReminder = "空气质量令人满意,基本无空气污染 各类人群可正常活动。";
		} else {
			this.ddAirPollutionIndex = null;
			this.warmReminder = null;
		}
	}

	public Integer getScenicSpotNo() {
		return scenicSpotNo;
	}

	public void setScenicSpotNo(Integer scenicSpotNo) {
		this.scenicSpotNo = scenicSpotNo;
	}

	public String getScenicSpotName() {
		return scenicSpotName;
	}

	public void setScenicSpotName(String scenicSpotName) {
		this.scenicSpotName = scenicSpotName;
	}

	public String getScenicPhoto() {
		return scenicPhoto;
	}

	public void setScenicPhoto(String scenicPhoto) {
		this.scenicPhoto = scenicPhoto;
	}

	public Float getDdAqi() {
		return ddAqi;
	}

	public void setDdAqi(Float ddAqi) {
		this.ddAqi = ddAqi;
	}

	public String getDdAirPollutionIndex() {
		return ddAirPollutionIndex;
	}

	public void setDdAirPollutionIndex(String ddAirPollutionIndex) {
		this.ddAirPollutionIndex = ddAirPollutionIndex;
	}

	public String getWarmReminder() {
		return warmReminder;
	}

	public void setWarmReminder(String warmReminder) {
		this.warmReminder = warmReminder;
	}

}
