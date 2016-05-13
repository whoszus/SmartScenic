package com.scenic.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.scenic.baseUitl.DateUtil;
import com.scenic.model.MSpotSort;
import com.scenic.repo.interf.impl.ICityResponsitory;
import com.scenic.repo.interf.impl.IDayDataRespository;
import com.scenic.repo.interf.impl.IProvinceResponsitory;
import com.scenic.repo.interf.impl.IScenicSpotRespository;
import com.scenic.repo.pojo.City;
import com.scenic.repo.pojo.DayData;
import com.scenic.repo.pojo.Province;
import com.scenic.repo.pojo.ScenicSpot;
import com.scenic.service.ISortService;

@Service("sortService")
public class SortServiceImpl implements ISortService {
	@Autowired
	private IDayDataRespository dataRespository;
	@Autowired
	private IProvinceResponsitory provinceResponsitory;
	@Autowired
	private ICityResponsitory cityResponsitory;
	@Autowired
	private IScenicSpotRespository scenicSpotRespository;

	@Override
	public MSpotSort calcalculateAvg(List<DayData> datas) {
		if (datas == null)
			return null;

		float aqiSum = 0;
		float comfortLevelSum = 0;
		int n1 = 0, n2 = 0;
		float avgAqi = 0, avgComfortLevel = 0;

		for (DayData d : datas) {
			if (d.getDdAqi() != null) {

				aqiSum = aqiSum + d.getDdAqi();
				n1++;
			}

			if (d.getDdComfortLevel() != null) {
				comfortLevelSum = comfortLevelSum + d.getDdComfortLevel();
				n2++;
			}
		}
		if (n1 != 0) {
			avgAqi = aqiSum / n1;
			BigDecimal bg = new BigDecimal(avgAqi);// 保留兩位小数
			avgAqi = (float) bg.setScale(2, BigDecimal.ROUND_HALF_UP)
					.doubleValue();

		}
		if (n2 != 0) {
			avgComfortLevel = comfortLevelSum / n2;
			BigDecimal bg = new BigDecimal(avgComfortLevel);
			avgComfortLevel = (float) bg.setScale(2, BigDecimal.ROUND_HALF_UP)
					.doubleValue();
		}
		if (!datas.isEmpty()) {
			ScenicSpot scenicSpot = scenicSpotRespository.findOne(datas.get(0)
					.getScenicSpot().getScenicSpotNo());
			City city = cityResponsitory.findOne(scenicSpot.getCity()
					.getCityId());
			Province province = provinceResponsitory.findOne(city.getProvince()
					.getProvinceId());

			MSpotSort m = new MSpotSort(scenicSpot.getScenicSpotName(),
					province.getProvinceName(), city.getCityName(), avgAqi,
					avgComfortLevel, null);
			return m;
		}

		return null;
	}

	@Override
	/**
	 * 通过aqi排序
	 * 
	 * 思想：通过天数和市名，找到该市下所有的景区，并取到对应天数的dayDa数据，
	 * 然后对每个景区下的dayData数据求平均值，变为一条Daydata，再根据这个DayData数据中的aqi来进行排序,
	 * 最后封装进model中，转为json字符串返回
	 */
	public String sortByAqi(int dayNum, String cityName) {
		List<MSpotSort> sortsList = new ArrayList<MSpotSort>();
		List<ScenicSpot> spotList = new ArrayList<ScenicSpot>();

		spotList = scenicSpotRespository.findByCityName(cityName);
		if (spotList.isEmpty())
			return null;
		for (ScenicSpot s : spotList) {
			MSpotSort mSort = fetchDatas(s.getScenicSpotNo(), dayNum);
			if (mSort != null)
				sortsList.add(mSort);
		}
		if (sortsList.isEmpty())
			return null;
		Collections.sort(sortsList, new Comparator<MSpotSort>() {
			public int compare(MSpotSort arg0, MSpotSort arg1) {
				if (arg0.getAqi().compareTo(arg1.getAqi()) != 0) {
					return arg0.getAqi().compareTo(arg1.getAqi());
				} else {
					return arg0.getComfortLevel() - arg1.getComfortLevel();
				}

			}
		});
		return new Gson().toJson(sortsList);
	}

	@Override
	/**
	 * 通过舒适度排序
	 * 
	 * 思想：通过天数和市名，找到该市下所有的景区，并取到对应天数的dayDa数据，
	 * 然后对每个景区下的dayData数据求平均值，变为一条Daydata，再根据这个DayData数据中的舒适度来进行排序,
	 * 最后封装进model中，转为json字符串返回
	 */
	public String sortByComfortLevel(int dayNum, String cityName) {
		List<MSpotSort> sorts = new ArrayList<MSpotSort>();
		List<ScenicSpot> spotList = new ArrayList<ScenicSpot>();

		spotList = scenicSpotRespository.findByCityName(cityName);
		if (spotList.isEmpty())
			return null;
		for (ScenicSpot s : spotList) {
			MSpotSort mSort = fetchDatas(s.getScenicSpotNo(), dayNum);
			if (mSort != null)
				sorts.add(mSort);
		}
		if (sorts.isEmpty())
			return null;
		Collections.sort(sorts, new Comparator<MSpotSort>() {
			public int compare(MSpotSort arg0, MSpotSort arg1) {
				if (arg0.getComfortLevel() - arg1.getComfortLevel() != 0) {
					return arg0.getComfortLevel() - arg1.getComfortLevel();
				} else {
					return arg0.getAqi().compareTo(arg1.getAqi());
				}
			}
		});
		return new Gson().toJson(sorts);
	}

	@Override
	/**
	 * 获取某个景点的数据
	 * 思想，根据景点编号和天数，得到该景点某段时间的数据，再将这些数据求平均值，传回一条
	 */
	public MSpotSort fetchDatas(int ScenicSpotNo, int days) {
		if (days <= 0)
			return null;
		List<DayData> dayDatas = new ArrayList<DayData>();
		String day = DateUtil.getCurrentDate();// 今天的日期

		for (int i = 0; i < days; i++) {
			day = DateUtil.getSpecifiedDayBefore(day).toString();// 前一天的日期
			DayData data = new DayData();

			data = dataRespository.findBySpotAndTime(ScenicSpotNo, DateUtil
					.getDayTime(day));
			if (data != null)
				dayDatas.add(data);
		}
		MSpotSort mSort = calcalculateAvg(dayDatas);

		return mSort;
	}

	@Override
	/**
	 * 综合排名
	 * 思想：通过天数和市名，找到该市下所有的景区，并取到对应天数的dayDa数据，
	 * 然后对每个景区下的dayData数据求平均值，变为一条Daydata，再根据这个DayData数据中的综合指数来进行排序,
	 * 最后封装进model中，转为json字符串返回
	 */
	public String sortByComprehensive(int dayNum, String cityName) {

		List<MSpotSort> sorts = new ArrayList<MSpotSort>();
		List<ScenicSpot> spotList = new ArrayList<ScenicSpot>();

		spotList = scenicSpotRespository.findByCityName(cityName);

		if (spotList.isEmpty())
			return null;

		for (ScenicSpot s : spotList) {
			MSpotSort mSort = fetchDatas(s.getScenicSpotNo(), dayNum);
			mSort.setComprehensive();
			if (mSort != null)
				sorts.add(mSort);
		}
		System.out.println(sorts.size());
		if (sorts.isEmpty())
			return null;

		Collections.sort(sorts, new Comparator<MSpotSort>() {
			public int compare(MSpotSort arg0, MSpotSort arg1) {
				return arg0.getComprehensive().compareTo(
						arg1.getComprehensive());
			}
		});
		return new Gson().toJson(sorts);
	}

}
