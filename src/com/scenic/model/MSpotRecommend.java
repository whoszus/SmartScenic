package com.scenic.model;

public class MSpotRecommend {// 景区推荐

	private Integer scenicSpotNo = null;// 景区编号
	private String scenicSpotName = null;// 景区名
	private String scenicPhoto = null;// 景区图片路径
	private String scenicSpotShortInfo = null;// 景区简介

	public Integer getScenicSpotNo() {
		return scenicSpotNo;
	}

	public void setScenicSpotNo(Integer scenicSpotNo) {
		this.scenicSpotNo = scenicSpotNo;
	}

	public String getScenicPhoto() {
		return scenicPhoto;
	}

	public void setScenicPhoto(String scenicPhoto) {
		this.scenicPhoto = scenicPhoto;
	}

	public String getScenicSpotShortInfo() {
		return scenicSpotShortInfo;
	}

	public void setScenicSpotShortInfo(String scenicSpotShortInfo) {
		this.scenicSpotShortInfo = scenicSpotShortInfo;
	}

	public String getScenicSpotName() {
		return scenicSpotName;
	}

	public void setScenicSpotName(String scenicSpotName) {
		this.scenicSpotName = scenicSpotName;
	}

	public MSpotRecommend(Integer scenicSpotNo, String scenicSpotName,
			String scenicPhoto, String scenicSpotShortInfo) {
		super();
		this.scenicSpotNo = scenicSpotNo;
		this.scenicSpotName = scenicSpotName;
		this.scenicPhoto = scenicPhoto;
		this.scenicSpotShortInfo = scenicSpotShortInfo;
	}

}
