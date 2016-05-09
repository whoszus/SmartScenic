package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.scenic.repo.interf.impl.IRealTimeDataRespository;
import com.scenic.repo.pojo.RealTimeData;
import com.scenic.service.IRealTimeDataService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "classpath:/applicationContext.xml" })
public class TestRealTimeData {
	@Autowired
	private IRealTimeDataService realTimeDataService;
	@Autowired
	private IRealTimeDataRespository realTimeDataRespository;

	@Test
	public void testFindByTime1() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date time1 = sdf.parse("2016-3-11 00:00:00");
			Date time2 = sdf.parse("2016-3-12 00:00:00");
			List<RealTimeData> datas = realTimeDataRespository.findByTime(1,
					time1, time2);
			System.out.println(datas.size());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testcalculateScenicSpotData() {
		realTimeDataService.calculateScenicSpotData(1);

	}

	@Test
	public void testFindByLastTime() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time1 = sdf.parse("2016-3-11 00:00:00");
		System.out.println(realTimeDataRespository.findByLastTime(1, time1)
				.getRtdTime());
		;

	}
}
