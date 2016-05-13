package com.scenic.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.scenic.baseUitl.DateUtil;
import com.scenic.model.MMapDate;
import com.scenic.repo.interf.impl.IDetectionPointRespository;
import com.scenic.repo.interf.impl.IRealTimeDataRespository;

import com.scenic.repo.pojo.DetectionPoint;
import com.scenic.repo.pojo.RealTimeData;
import com.scenic.service.IMapService;
import com.scenic.service.IRealTimeDataService;

@Service("mapService")
public class MapServiceImp implements IMapService {

	@Autowired
	private IDetectionPointRespository dRespository;
	@Autowired
	private IRealTimeDataRespository respository;
	@Autowired
	private IRealTimeDataService rDataService;

	@Override
	/***
	 * 通过景区编号获取景点下的所有监测点，在通过对应的检测点编号获取realTimeData数据，
	 * 然后算Aqi，最后把经纬度和aqi封装成model，转为Json字符串
	 */
	public String getPointAndAqi(Integer scenicSpotNo) {
		List<DetectionPoint> detectionPoints = dRespository
				.findByScenicSpotNo(scenicSpotNo);
		List<MMapDate> mapDates = new ArrayList<MMapDate>();

		String Day = DateUtil.getCurrentDate();
		Day = DateUtil.getSpecifiedDayBefore(Day);

		for (int i = 0; i < detectionPoints.size(); i++) {
			int  detectionPointNo = detectionPoints.get(i).getDetectionPointNo();
			List<RealTimeData> rData = respository.findByLastTime(detectionPointNo, DateUtil.StringToDate(Day));

			if (rData != null) {
				MMapDate mapDate = new MMapDate(detectionPoints.get(i)
						.getDetectionPointLongitude(), detectionPoints.get(i)
						.getDetectionPointLatitude(), calculateAqi(rData.get(0)));

				mapDates.add(mapDate);
			}
		}
		System.out.println(mapDates.size());

		return new Gson().toJson(mapDates);
	}

	@Override
	/***
	 * 根据realtimeData的数据来算aqi
	 */
	public Float calculateAqi(RealTimeData data) {
		Float[] aqiSubIndex = new Float[6];

		aqiSubIndex[0] = rDataService.calculateAqiSubIndexPm10(data
				.getRtdPm10());
		aqiSubIndex[1] = rDataService.calculateAqiSubIndexPm25(data
				.getRtdPm25());
		aqiSubIndex[2] = rDataService.calculateAqiSubIndexNo2(data.getRtdNo2());
		aqiSubIndex[3] = rDataService.calculateAqiSubIndexSo2(data.getRtdSo2());
		aqiSubIndex[4] = rDataService.calculateAqiSubIndexCo(data.getRtdCo());
		aqiSubIndex[5] = rDataService.calculateAqiSubIndex03(data.getRtdO3());
		float maxAqi = -1f;
		for (int i = 0; i < aqiSubIndex.length; i++) {
			if (aqiSubIndex[i] != null && maxAqi < aqiSubIndex[i]) {
				maxAqi = aqiSubIndex[i];
			}
		}
		if (maxAqi == -1f) {
			return null;
		}
		return maxAqi;
	}

}
