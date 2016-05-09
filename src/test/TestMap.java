package test;

import net.sf.json.JSON;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.scenic.repo.interf.impl.IScenicSpotRespository;
import com.scenic.repo.pojo.ScenicSpot;
import com.scenic.service.impl.MapServiceImp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "classpath:/applicationContext.xml" })
public class TestMap {
	@Autowired
	private MapServiceImp mImp;

	@Autowired
	private IScenicSpotRespository sRespository;

	@Test
	public void testMap() {
		System.out.println(mImp.getPointAndAqi(1));
	}
	//	
	// @Test
	// public void testconnection(){
	// ScenicSpot s=sRespository.findBySpotNo(1);
	// System.out.println(s.getScenicSpoMinLongitude());
	// System.out.println("!!!!!!!!!!!!");
	// }
	//	
	// @Test
	// public void testinterpolation(){
	// double[][] x= {{100,150},{200,200},{300,320},{400,280}};
	// double []y={40,100,80,60};
	// JSON json =mImp.interpolation(1 , x, y);
	// System.out.println(json);
	// System.out.println(json.size());
	// }
	//	
	// @Test
	// public void testpreparePoints(){
	// double[][] points = mImp.preparePoints(1, 31.522012, 104.864603);
	// for (int i = 0; i < points.length; i++) {
	// System.out.println("X:"+points[i][0]);
	// System.out.println("Y:"+points[i][1]);
	// }
	// }

}
