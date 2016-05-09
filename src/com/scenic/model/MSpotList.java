package com.scenic.model;

public class MSpotList {

	private Integer scenicSpotNo;
	private String scenicSpotName;

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

	public MSpotList(Integer scenicSpotNo, String scenicSpotName) {
		super();
		this.scenicSpotNo = scenicSpotNo;
		this.scenicSpotName = scenicSpotName;
	}

}
