package com.scenic.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.scenic.repo.pojo.ScenicSpot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scenic.baseUitl.DateUtil;
import com.scenic.repo.interf.impl.IDayDataRespository;
import com.scenic.repo.interf.impl.IRealTimeDataRespository;
import com.scenic.repo.interf.impl.IScenicSpotRespository;
import com.scenic.repo.pojo.DayData;
import com.scenic.repo.pojo.RealTimeData;
import com.scenic.service.IRealTimeDataService;

@Service("realTimeDataService")
public class RealTimeDataServiceImpl implements IRealTimeDataService {

	@Autowired
	private IRealTimeDataRespository realTimeDataRespository;

	@Autowired
	private IScenicSpotRespository scenicSpotRespository;

	@Autowired
	private IDayDataRespository dayDataRespository;

	@Override
	/**
	 * 计算某个景区的一天的综合数据，并存入数据库中
	 * 
	 * 思想：通过景区编号取出该景区下前一天所有的实时数据，对实时数据进行平均得，然后将值封装进DayData中，存入数据库。
	 */
	public void calculateScenicSpotData(int scenicSpotNo) {

		List<RealTimeData> datas = new ArrayList<RealTimeData>();
		String day = DateUtil.getCurrentDate();
		String daybefore = DateUtil.getSpecifiedDayBefore(day).toString();
		datas = realTimeDataRespository.findByTime(scenicSpotNo, DateUtil
				.getDayTime(daybefore), DateUtil.getDayTime(day));
		if(datas.size()==0){
			return;
		}

		if (datas.size() != 0) {
			float[] sums = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			int[] count = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			float[] aqiSubIndex = { -1, -1, -1, -1, -1, -1 };
			for (RealTimeData data : datas) {
				if (data.getRtdTemperature() != null) {// 温度
					sums[0] = sums[0] + data.getRtdTemperature();
					count[0]++;
				}
				if (data.getRtdOxygenContent() != null) {// 含氧量
					sums[1] = sums[1] + data.getRtdOxygenContent();
					count[1]++;
				}
				if (data.getRtdAirSpeed() != null) {// 风速
					sums[2] = sums[2] + data.getRtdAirSpeed();
					count[2]++;
				}
				if (data.getRtdUltraviolet() != null) {// 紫外线
					sums[3] = sums[3] + data.getRtdUltraviolet();
					count[3]++;
				}
				if (data.getRtdHumidity() != null) {// 湿度
					sums[4] = sums[4] + data.getRtdHumidity();
					count[4]++;
				}
				if (data.getRtdPm10() != null) {// pm10
					sums[5] = sums[5] + data.getRtdPm10();
					count[5]++;
				}
				if (data.getRtdPm25() != null) {// pm2.5
					sums[6] = sums[6] + data.getRtdPm25();
					count[6]++;
				}

				if (data.getRtdNo2() != null) {// no2
					sums[7] = sums[7] + data.getRtdNo2();
					count[7]++;
				}
				if (data.getRtdSo2() != null) {// so2
					sums[8] = sums[8] + data.getRtdSo2();
					count[8]++;
				}
				if (data.getRtdCo() != null) {// co
					sums[9] = sums[9] + data.getRtdCo();
					count[9]++;
				}
				if (data.getRtdO3() != null) {// o3
					sums[10] = sums[10] + data.getRtdO3();
					count[10]++;
				}
			}

			DayData dayData = new DayData();
			dayData.setDdTime(DateUtil.StringToDate(daybefore));// 时间
			dayData.setScenicSpot(scenicSpotRespository.findOne(scenicSpotNo));

			if (count[0] != 0)
				dayData.setDdTemperature(sums[0] / count[0]);// 温度
			if (count[1] != 0)
				dayData.setDdOxygenContent(sums[1] / count[1]);// 含氧量
			if (count[2] != 0)
				dayData.setDdAirSpeed(sums[2] / count[2]);// 风速
			if (count[3] != 0)
				dayData.setDdUlteraviolet(sums[3] / count[3]);// 紫外线
			if (count[4] != 0)
				dayData.setDdHumidity(sums[4] / count[4]);// 湿度
			if (count[5] != 0) {
				dayData.setDdPm10(sums[5] / count[5]);// pm10
				aqiSubIndex[0] = sums[5] / count[5];
			}
			if (count[6] != 0) {
				dayData.setDdPm25(sums[6] / count[6]);// pm25
				aqiSubIndex[1] = sums[6] / count[6];
			}
			if (count[7] != 0) {
				aqiSubIndex[2] = sums[7] / count[7];
			}
			if (count[8] != 0) {
				aqiSubIndex[3] = sums[8] / count[8];
			}
			if (count[9] != 0) {
				aqiSubIndex[4] = sums[9] / count[9];
			}
			if (count[10] != 0) {
				aqiSubIndex[5] = sums[10] / count[10];
			}
			calculateAqi(aqiSubIndex, dayData);
			dayData.setDdComfortLevel(calculateSSD(dayData.getDdTemperature(),
					dayData.getDdHumidity(), dayData.getDdAirSpeed()));
			dayDataRespository.save(dayData);
			System.out.println(dayData.getDdAirSpeed() + ".............................................");
		}
	}

	@Override
	/**
	 * 计算aqi
	 * 思想：先传入aqi的的指标数组，通过该数组求得aqi的分指数，
	 * 比较分指数，最大的为aqi，该污染源设为主要污染源，最后根据aqi划分等级，存到对应的DayDate中
	 */
	public DayData calculateAqi(float[] aqi, DayData dayData) {
		Float[] aqiSubIndex = new Float[6];
		aqiSubIndex[0] = calculateAqiSubIndexPm10(aqi[0]);
		aqiSubIndex[1] = calculateAqiSubIndexPm25(aqi[1]);
		aqiSubIndex[2] = calculateAqiSubIndexNo2(aqi[2]);
		aqiSubIndex[3] = calculateAqiSubIndexSo2(aqi[3]);
		aqiSubIndex[4] = calculateAqiSubIndexCo(aqi[4]);
		aqiSubIndex[5] = calculateAqiSubIndex03(aqi[5]);
		float maxAqi = 0f;// aqi
		String mainPollution = null;// 主要污染物
		String grade;// 等级

		if (aqiSubIndex[0] != null) {
			maxAqi = aqiSubIndex[0];
			mainPollution = "Pm10";
		}
		if (aqiSubIndex[1] != null) {
			if (maxAqi < aqiSubIndex[1]) {
				maxAqi = aqiSubIndex[1];
				mainPollution = "Pm2.5";
			}
		}
		if (aqiSubIndex[2] != null) {
			if (maxAqi < aqiSubIndex[2]) {
				maxAqi = aqiSubIndex[2];
				mainPollution = "No2";
			}
		}
		if (aqiSubIndex[3] != null) {
			if (maxAqi < aqiSubIndex[3]) {
				maxAqi = aqiSubIndex[3];
				mainPollution = "So2";
			}
		}
		if (aqiSubIndex[4] != null) {
			if (maxAqi < aqiSubIndex[4]) {
				maxAqi = aqiSubIndex[4];
				mainPollution = "Co";
			}
		}
		if (aqiSubIndex[5] != null) {
			if (maxAqi < aqiSubIndex[5]) {
				maxAqi = aqiSubIndex[5];
				mainPollution = "O3";
			}
		}
		if (maxAqi <= 50)
			mainPollution = "无";

		if (maxAqi > 300) {
			grade = "六级";
		} else if (maxAqi > 200) {
			grade = "五级";
		} else if (maxAqi > 150) {
			grade = "四级";
		} else if (maxAqi > 100) {
			grade = "三级";
		} else if (maxAqi > 50) {
			grade = "二级";
		} else if (maxAqi > 0) {
			grade = "一级";
		} else {
			grade = null;
		}
		dayData.setDdAqi(maxAqi);
		dayData.setDdMainPollution(mainPollution);
		dayData.setDdAirPollutionIndex(grade);
		return dayData;
	}

	@Override
	/**
	 * 思想：计算aqi分指数o3
	 */
	public float calculateAqiSubIndex03(float o3) {
		float subIndex;
		if (o3 >= 265) {
			subIndex = (300 - 200) / (800 - 265) * (o3 - 265) + 200;
		} else if (o3 >= 215) {
			subIndex = (200 - 150) * (o3 - 215) / (265 - 215) + 150;
		} else if (o3 >= 160) {
			subIndex = (150 - 100) * (o3 - 160) / (215 - 160) + 100;
		} else if (o3 >= 100) {
			subIndex = (100 - 50) * (o3 - 100) / (160 - 100) + 50;
		} else if (o3 >= 0) {
			subIndex = (50 - 0) * (o3 - 0) / (100 - 0);
		} else {
			return -1;
		}
		return subIndex;
	}

	@Override
	/**
	 * 计算aqi分指数Co
	 */
	public float calculateAqiSubIndexCo(float co) {
		float subIndex;

		if (co >= 48) {
			subIndex = (500 - 400) * (co - 48) / (60 - 48) + 400;
		} else if (co >= 36) {
			subIndex = (400 - 300) / (48 - 36) * (co - 36) + 300;
		} else if (co >= 24) {
			subIndex = (300 - 200) / (36 - 24) * (co - 24) + 200;
		} else if (co >= 14) {
			subIndex = (200 - 150) * (co - 14) / (24 - 14) + 150;
		} else if (co >= 4) {
			subIndex = (150 - 100) * (co - 4) / (14 - 4) + 100;
		} else if (co >= 2) {
			subIndex = (100 - 50) * (co - 2) / (4 - 2) + 50;
		} else if (co >= 0) {
			subIndex = (50 - 0) * (co - 0) / (2 - 0);
		} else {
			return -1;
		}
		return subIndex;
	}

	@Override
	/**
	 * 思想：计算aqi的分指数NO2
	 */
	public float calculateAqiSubIndexNo2(float no2) {
		float subIndex;

		if (no2 >= 750) {
			subIndex = (500 - 400) * (no2 - 750) / (940 - 750) + 400;
		} else if (no2 >= 565) {
			subIndex = (400 - 300) * (no2 - 565) / (750 - 565) + 300;
		} else if (no2 >= 280) {
			subIndex = (300 - 200) * (no2 - 280) / (565 - 280) + 200;
		} else if (no2 >= 180) {
			subIndex = (200 - 150) * (no2 - 180) / (280 - 180) + 150;
		} else if (no2 >= 80) {
			subIndex = (150 - 100) * (no2 - 80) / (180 - 80) + 100;
		} else if (no2 >= 40) {
			subIndex = (100 - 50) * (no2 - 40) / (80 - 40) + 50;
		} else if (no2 >= 0) {
			subIndex = (50 - 0) * (no2 - 0) / (40 - 0);
		} else {
			return -1;
		}
		return subIndex;
	}

	@Override
	/**
	 * 思想：计算aqi的分指数pm10
	 */
	public float calculateAqiSubIndexPm10(float pm10) {
		float subIndex;

		if (pm10 >= 500) {
			subIndex = (500 - 400) * (pm10 - 500) / (600 - 500) + 400;
		} else if (pm10 >= 420) {
			subIndex = (400 - 300) / (500 - 420) * (pm10 - 420) + 300;
		} else if (pm10 >= 350) {
			subIndex = (300 - 200) / (420 - 350) * (pm10 - 350) + 200;
		} else if (pm10 >= 250) {
			subIndex = (200 - 150) * (pm10 - 250) / (350 - 250) + 150;
		} else if (pm10 >= 150) {
			subIndex = (150 - 100) * (pm10 - 150) / (250 - 150) + 100;
		} else if (pm10 >= 50) {
			subIndex = (100 - 50) * (pm10 - 50) / (150 - 50) + 50;
		} else if (pm10 >= 0) {
			subIndex = (50 - 0) * (pm10 - 0) / (50 - 0);
		} else {
			return -1;
		}
		return subIndex;
	}

	@Override
	/**
	 * 思想：计算aqi分指数pm2.5
	 */
	public float calculateAqiSubIndexPm25(float pm25) {

		float subIndex;

		if (pm25 >= 350) {
			subIndex = (500 - 400) * (pm25 - 350) / (500 - 350) + 400;
		} else if (pm25 >= 250) {
			subIndex = (400 - 300) / (350 - 250) * (pm25 - 250) + 300;
		} else if (pm25 >= 150) {
			subIndex = (300 - 200) / (420 - 350) * (pm25 - 350) + 200;
		} else if (pm25 >= 115) {
			subIndex = (200 - 150) * (pm25 - 115) / (150 - 115) + 150;
		} else if (pm25 >= 75) {
			subIndex = (150 - 100) * (pm25 - 75) / (115 - 75) + 100;
		} else if (pm25 >= 35) {
			subIndex = (100 - 50) * (pm25 - 35) / (75 - 35) + 50;
		} else if (pm25 >= 0) {
			subIndex = (50 - 0) * (pm25 - 0) / (35 - 0);
		} else {
			return -1;
		}
		return subIndex;
	}

	@Override
	/**
	 *思想：计算aqi分指数so2
	 */
	public float calculateAqiSubIndexSo2(float so2) {

		float subIndex;
		if (so2 >= 2100) {
			subIndex = (500 - 400) * (so2 - 2100) / (2620 - 2100) + 400;
		} else if (so2 >= 1600) {
			subIndex = (400 - 300) / (2100 - 1600) * (so2 - 1600) + 300;
		} else if (so2 >= 800) {
			subIndex = (300 - 200) / (1600 - 800) * (so2 - 800) + 200;
		} else if (so2 >= 475) {
			subIndex = (200 - 150) * (so2 - 475) / (800 - 475) + 150;
		} else if (so2 >= 150) {
			subIndex = (150 - 100) * (so2 - 150) / (475 - 150) + 100;
		} else if (so2 >= 50) {
			subIndex = (100 - 50) * (so2 - 50) / (150 - 50) + 50;
		} else if (so2 >= 0) {
			subIndex = (50 - 0) * (so2 - 0) / (50 - 0);
		} else {
			return -1;
		}
		return subIndex;
	}

	@Override
	/**
	 * 思想：计算人体舒适度，根据公式：ssd=(1.818t+ 18.18)(0.88 + 0.002f)+(t- 32) / (45 -t)- 3.2v+ 18.2，
	 * ssd为人体舒适度指数,t为平均气温,f为相对湿度,v为风速
	 */
	public float calculateSSD(float t, float f, float v) {
		double ssd = (1.818 * t + 18.18) * (0.88 + 0.002 * f) + (t - 32)
				/ (45 - t) - 3.2 * v + 18.2;
		return (float) ssd;
	}

	public void caculatAllSpot(){
		List<ScenicSpot> scenicSpots = scenicSpotRespository.findAll();
		if (scenicSpots != null) {
			for (ScenicSpot s : scenicSpots) {
				calculateScenicSpotData(s.getScenicSpotNo());
			}
		}
	}

}
