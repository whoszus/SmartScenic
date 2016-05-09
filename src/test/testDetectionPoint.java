package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.scenic.model.MDetectionPoint;
import com.scenic.repo.interf.impl.IDetectionPointRespository;
import com.scenic.repo.pojo.DetectionPoint;
import com.scenic.repo.pojo.ScenicSpot;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "classpath:/applicationContext.xml" })
public class testDetectionPoint {
	@Autowired
	private IDetectionPointRespository detectionPointRespository;

	@Test
	public void findByScenicSpotNoR() {
		List<DetectionPoint> spot = detectionPointRespository
				.findByScenicSpotNo(1);
		List<MDetectionPoint> mDetectionPoints = new ArrayList<MDetectionPoint>();
		for(int i=0;i<spot.size();i++){
			MDetectionPoint mPoint = new MDetectionPoint(spot.get(i));
			mDetectionPoints.add(mPoint);		
		}
		String json = new Gson().toJson(mDetectionPoints);
		System.out.println(json);
	}
	
	@Test
	public void  findByNameAndScenicR(){
		DetectionPoint detectionPoint = detectionPointRespository.findByNameAndScenic(1,"仙海湖东");
		System.out.println(detectionPoint.getDetectionPointName());
	}
}
