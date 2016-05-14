package test;

import com.scenic.service.IDayDataService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.annotations.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "classpath:/applicationContext.xml" })
public class TestDayData {

	// @Autowired
	// private IDayDataRespository dayDataRespository;
	@Autowired
	private IDayDataService dayDataService;

	// @Autowired
	// private IDetectionPointRespository dRespository;
	//	
	//
	//
	// @Test
	// public void testFindByTime() {
	// String json = dayDataService.findByTime(1, 1).toString();
	// System.out.println(json);
	// }

	@Test
	public void testShowAqiAndCom() {
		dayDataService.showAqiAndComf(1);
	}

	@Test
	public void testShowOtherIndex() {
		System.out.println(dayDataService.showOtherIndex(1));
		;
	}
}
