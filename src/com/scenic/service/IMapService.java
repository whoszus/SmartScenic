package com.scenic.service;

import com.scenic.repo.pojo.RealTimeData;

public interface IMapService {

	public String getPointAndAqi(Integer scenicSpotNo);

	public Float calculateAqi(RealTimeData data);
}
