package test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.scenic.model.MScenicSpot;
import com.scenic.repo.interf.impl.IScenicSpotRespository;
import com.scenic.repo.pojo.ScenicSpot;
import com.scenic.service.IScenicSpotService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "classpath:/applicationContext.xml" })
public class TestScenicSpot {
	@Autowired
	private IScenicSpotRespository sRespository;
	@Autowired
	private IScenicSpotService service;

	@Test
	public void findByCityNameR() {
		List<ScenicSpot> sList = sRespository.findByCityName("绵阳");
		System.out.println(sList.size());
	}

	@Test
	public void findByCityAndNameR() {
		ScenicSpot spot = sRespository.findByCityAndName("绵阳", "仙海湖");
		System.out.println(spot.getScenicSpotShortInfo());
	}

	@Test
	public void findByCityAndNameS() {
		MScenicSpot mSpot = new MScenicSpot();
		mSpot.setCityId(260);
		mSpot.setCityName("绵阳");
		mSpot.setScenicSpotName("人民公園3");
		mSpot.setScenicInfo("这是人民公园的详细介绍");
		MScenicSpot mScenicSpot = service.addScenicSpot(mSpot);
		String json = new Gson().toJson(mScenicSpot);
		System.out.println(mScenicSpot);

	}
	
	@Test
	public void deleteScenicSpotS(){
		service.deleteScenicSpot(28);
	}

}
