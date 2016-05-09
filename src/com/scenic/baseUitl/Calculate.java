package com.scenic.baseUitl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.scenic.repo.interf.impl.IRealTimeDataRespository;
import com.scenic.repo.interf.impl.IScenicSpotRespository;
import com.scenic.repo.pojo.RealTimeData;
import com.scenic.repo.pojo.ScenicSpot;
import com.scenic.service.IRealTimeDataService;

public class Calculate {
	@Autowired
	private IScenicSpotRespository scenicSpotRespository;
	@Autowired
	private IRealTimeDataService realTimeDataService;

	public void test() {
		List<ScenicSpot> scenicSpots = scenicSpotRespository.findAll();
		if (scenicSpots != null) {
			for (ScenicSpot s : scenicSpots) {
				// realTimeDataService.calculateAvg(s.getScenicSpotNo());
			}
		}
	}
}
