package com.scenic.model;

import java.util.List;

public class MSpot {

	private String scenicSpotName;
	private String scenicSpoInfo;
	private List<MSpotData> datas;

	public String getScenicSpotName() {
		return scenicSpotName;
	}

	public void setScenicSpotName(String scenicSpotName) {
		this.scenicSpotName = scenicSpotName;
	}

	public String getScenicSpoInfo() {
		return scenicSpoInfo;
	}

	public void setScenicSpoInfo(String scenicSpoInfo) {
		this.scenicSpoInfo = scenicSpoInfo;
	}

	public List<MSpotData> getDatas() {
		return datas;
	}

	public void setDatas(List<MSpotData> datas) {
		this.datas = datas;
	}

}
