package test;

import java.io.IOException;

import org.dom4j.DocumentException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.scenic.service.impl.GetWeatherService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "classpath:/applicationContext.xml" })
public class TestWeather {

	@Autowired
	private GetWeatherService getWeatherService;

	@Test
	public void test() throws Exception {
		String str = getWeatherService.getWeatherinfoStr("北京");
		getWeatherService.fetchZhishu(str);
	}

	@Test
	public void testFetchForecast() throws IOException, DocumentException {
		String str = getWeatherService.getWeatherinfoStr("北京");
		getWeatherService.fetchForecast(str);
	}
}
