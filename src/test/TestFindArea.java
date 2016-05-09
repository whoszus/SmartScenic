package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.scenic.repo.interf.impl.ICityResponsitory;
import com.scenic.service.impl.AreaFindServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "classpath:/applicationContext.xml" })
public class TestFindArea {
	@Autowired
	private AreaFindServiceImpl areaFindServiceImpl;
	@Autowired
	private ICityResponsitory iCityResponsitory;

	@Test
	public void test() {
		try {
			iCityResponsitory.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testShowCity() {
		String str = areaFindServiceImpl.showCity().toString();
		System.out.println(str);

	}

	@Test
	public void testShowProvinces() {
		System.out.println(areaFindServiceImpl.showProvinces());
	}
}
