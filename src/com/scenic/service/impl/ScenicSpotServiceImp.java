package com.scenic.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scenic.baseUitl.DateUtil;
import com.scenic.model.MScenicSpot;
import com.scenic.model.MScenicSpotinfo;
import com.scenic.model.MSpotList;
import com.scenic.model.MSpot;
import com.scenic.model.MSpotData;
import com.scenic.model.MSpotRecommend;
import com.scenic.repo.interf.impl.ICityResponsitory;
import com.scenic.repo.interf.impl.IDayDataRespository;
import com.scenic.repo.interf.impl.IScenicSpotRespository;
import com.scenic.repo.pojo.City;
import com.scenic.repo.pojo.DayData;
import com.scenic.repo.pojo.ScenicSpot;
import com.scenic.service.IScenicSpotService;

@Service("scenicspotService")
public class ScenicSpotServiceImp implements IScenicSpotService {

	@Autowired
	private IScenicSpotRespository sRespository;

	@Autowired
	private IDayDataRespository dRespository;
	@Autowired
	private ICityResponsitory cityResponsitory;

	@Override
	/**
	 * 获取某一市下的所有景区
	 * 思想：通过城市，查找数据库，获取景区，在封装进model中，转为json字符串
	 */
	public String choiceSpotbyCity(String cityName) {
		List<ScenicSpot> spots = sRespository.findByCityName(cityName);

		if (spots.isEmpty()) {
			return null;
		}

		List<MSpotList> mScenicspots = new ArrayList<MSpotList>();

		for (int i = 0; i < spots.size(); i++) {
			MSpotList s = new MSpotList(spots.get(i).getScenicSpotNo(), spots
					.get(i).getScenicSpotName());
			mScenicspots.add(s);
		}

		return new Gson().toJson(mScenicspots);
	}

	@Override
	/**
	 * 选择某市下默认的景区
	 * 思想：通过市，找到所有景区，得到aqi最小的那个景区，封装进model中，在转为json字符串
	 */
	public String choiceSpotDefault(String cityName) {

		List<ScenicSpot> spots = sRespository.findByCityName(cityName);

		List<DayData> dayDatas = fetchDatas(spots);

		DayData dayData = dayDatas.get(0);

		System.out.println(dayData.getDdNo());

		ScenicSpot scenicSpot = sRespository.findBySpotNo(dayData
				.getScenicSpot().getScenicSpotNo());

		MScenicSpotinfo mSpotinfo = new MScenicSpotinfo(scenicSpot
				.getScenicSpotNo(), scenicSpot.getScenicSpotName(), scenicSpot
				.getScenicPhoto(), dayData.getDdAqi());

		return new Gson().toJson(mSpotinfo);
	}

	@Override
	/**
	 *根据传来的景区，得到该景区昨日的DayData数据，再根据aqi对所有的DayDa进行排序，返回第一个
	 */
	public List<DayData> fetchDatas(List<ScenicSpot> spots) {
		List<DayData> dayDataList = new ArrayList<DayData>();
		String Day = DateUtil.getSpecifiedDayBefore(DateUtil.getCurrentDate());// 昨天的日期

		for (int i = 0; i < spots.size(); i++) {
			DayData d = new DayData();
			d = dRespository.findBySpotAndTime(spots.get(i).getScenicSpotNo(),
					DateUtil.StringToDate(Day));
			if (d != null) {
				dayDataList.add(d);
			}
		}

		if (dayDataList.isEmpty()) {
			return null;
		}

		Collections.sort(dayDataList, new Comparator<DayData>() {
			public int compare(DayData arg0, DayData arg1) {
				return arg0.getDdAqi().compareTo(arg1.getDdAqi());

			}
		});
		System.out.println(dayDataList.size());

		return dayDataList;
	}

	@Override
	/**
	 * 用户自定义景区
	 * 根据用户所传的景区编号，查找数据库，得到相应的数据，封装进model，在转为json字符串返回
	 */
	public String userDefined(Integer scenicSpotNo) {

		String Day = DateUtil.getSpecifiedDayBefore(DateUtil.getCurrentDate());// 昨天的日期

		ScenicSpot scenicSpot = sRespository.findBySpotNo(scenicSpotNo);

		DayData dayData = dRespository.findBySpotAndTime(scenicSpotNo, DateUtil
				.StringToDate(Day));
		if (dayData == null) {
			return null;
		}

		MScenicSpotinfo mSpotinfo = new MScenicSpotinfo(scenicSpot
				.getScenicSpotNo(), scenicSpot.getScenicSpotName(), scenicSpot
				.getScenicPhoto(), dayData.getDdAqi());

		return new Gson().toJson(mSpotinfo);

	}

	@Override
	/**
	 * 通过市获取景区推荐的景点信息
	 */
	public String spotRecommend(String cityName) {

		List<ScenicSpot> spots = sRespository.findByCityName(cityName);

		List<DayData> dayDatas = fetchDatas(spots);

		List<MSpotRecommend> spotRecommends = new ArrayList<MSpotRecommend>();

		for (int i = 0; i < spots.size(); i++) {
			int no = dayDatas.get(i).getScenicSpot().getScenicSpotNo();
			ScenicSpot s = sRespository.findOne(no);

			MSpotRecommend r = new MSpotRecommend(no, s.getScenicSpotName(), s
					.getScenicPhoto(), s.getScenicSpotShortInfo());
			spotRecommends.add(r);
			System.out.println(new Gson().toJson(r));
		}

		return new Gson().toJson(spotRecommends);
	}

	@Override
	/**
	 * 获取景区的详细信息及5天的数据
	 */
	public String showSpotDatas(Integer scenicSpotNo) {
		ScenicSpot spot = sRespository.findBySpotNo(scenicSpotNo);
		if (spot == null)
			return null;
		MSpot mSpot = new MSpot();
		String day = DateUtil.getCurrentDate();
		List<MSpotData> mSpotDatas = new ArrayList<MSpotData>();

		for (int i = 0; i < 5; i++) {
			day = DateUtil.getSpecifiedDayBefore(day);
			DayData dayData = dRespository.findBySpotAndTime(scenicSpotNo,
					DateUtil.StringToDate(day));
			if (dayData == null)
				continue;
			MSpotData m = new MSpotData(dayData.getDdTime(),
					dayData.getDdAqi(), dayData.getDdComfortLevel(), dayData
							.getDdPm25());
			mSpotDatas.add(m);
		}

		mSpot.setScenicSpotName(spot.getScenicSpotName());
		mSpot.setScenicSpoInfo(spot.getScenicInfo());
		mSpot.setDatas(mSpotDatas);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(mSpot);

		return json;
	}

	@Override
	public MScenicSpot addScenicSpot(MScenicSpot mSpot) {
		Integer id = mSpot.getCityId();
		City city = cityResponsitory.findOne(id);// 找到
		ScenicSpot spot = new ScenicSpot(mSpot, city);
		sRespository.save(spot);

		ScenicSpot scenicSpot = sRespository.findByCityAndName(city
				.getCityName(), mSpot.getScenicSpotName());

		MScenicSpot mScenicSpot = new MScenicSpot(scenicSpot, city);
		return mScenicSpot;
	}

	@Override
	public Boolean IsSameSpot(Integer cityId, String scenicSpotName) {
		ScenicSpot spot = sRespository.findByCityAndId(cityId, scenicSpotName);
		if (spot != null) {
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public MScenicSpot updateScenicSpot(MScenicSpot mSpot) {
		if (mSpot.getScenicSpotNo() == null) {
			return null;
		}
		ScenicSpot spot = sRespository.findOne(mSpot.getScenicSpotNo());
		if(spot == null){
			return null;
		}

		if (mSpot.getScenicPhoto() != null)
			spot.setScenicPhoto(mSpot.getScenicPhoto());

		if (mSpot.getScenicSpotShortInfo() != null)
			spot.setScenicSpotShortInfo(mSpot.getScenicSpotShortInfo());

		if (mSpot.getScenicInfo() != null)
			spot.setScenicInfo(mSpot.getScenicInfo());

		if (mSpot.getScenicSpoMinLongitude() != null)
			spot.setScenicSpoMinLongitude(mSpot.getScenicSpoMinLongitude());

		if (mSpot.getScenicSpotMinLatitude() != null)
			spot.setScenicSpotMinLatitude(mSpot.getScenicSpotMinLatitude());

		if (mSpot.getScenicSpotMaxLongitude() != null)
			spot.setScenicSpotMaxLongitude(mSpot.getScenicSpotMaxLongitude());

		if (mSpot.getScenicSpotMaxLatitude() != null)
			spot.setScenicSpotMaxLatitude(mSpot.getScenicSpotMaxLatitude());
		sRespository.saveAndFlush(spot);
		
		return new MScenicSpot(spot,null);
	}

	@Override
	public void deleteScenicSpot(Integer scenicSpotNo) {
		sRespository.delete(scenicSpotNo);		
	}
}
