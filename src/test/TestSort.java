package test;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONArray;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.scenic.baseUitl.CharacterConversion;
import com.scenic.model.MSpotSort;
import com.scenic.repo.interf.impl.IDayDataRespository;
import com.scenic.repo.pojo.DayData;
import com.scenic.service.impl.SortServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "classpath:/applicationContext.xml" })
public class TestSort {

	@Autowired
	private SortServiceImpl sImpl;
	@Autowired
	private IDayDataRespository tDataRespository;

	@Test
	public void testcalculateAvg() {
		List<DayData> daydatas = new ArrayList<DayData>();
		for (int i = 1; i < 5; i++) {
			DayData data = tDataRespository.findOne(i);
			daydatas.add(data);
		}

		MSpotSort sort = sImpl.calcalculateAvg(daydatas);
		JSON json = JSONArray.fromObject(sort);
		System.out.println(json.toString());
	}

	@Test
	public void testfetchDatas() {
		MSpotSort mSort = sImpl.fetchDatas(2, 1);
		JSON json = JSONArray.fromObject(mSort);
		System.out.println(json);
	}

	@Test
	public void testweeksortByAqi() {
		String json = sImpl.sortByAqi(7, "绵阳市");
		System.out.println(json);
	}

	@Test
	public void testweekSortByComf() {
		String json = sImpl.sortByComfortLevel(7, "绵阳市");
		System.out.println(json);
	}

	@Test
	public void testyestersortBycomphenve() {
		String str = sImpl.sortByComprehensive(1, "绵阳市");
		System.out.println(str);
	}
}
