package com.scenic.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.scenic.baseUitl.WriteJson;
import com.scenic.model.MDetectionPoint;
import com.scenic.service.IDetectionPoinSercive;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class DetectionPointAction extends ActionSupport implements
		ModelDriven<MDetectionPoint> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MDetectionPoint mPoint = new MDetectionPoint();
	@Autowired
	private IDetectionPoinSercive dSercive;

	/**
	 * 显示某个景点下的所有检测点 
	 * http://luorong:8080/SmartScenic/detection/detection_findByScenicSpotNo.action?scenicSpotNo=1
	 */
	public void findByScenicSpotNo() {
		List<MDetectionPoint> mDetectionPoints = dSercive
				.findByScenicSpotNo(mPoint.getScenicSpotNo());
		String json = new Gson().toJson(mDetectionPoints);
		WriteJson.writeToClient(json);
	}
	
	/**
	 * 增加一个监测点
	 * http://luorong:8080/SmartScenic/detection/detection_addDetectionPoint.action?scenicSpotNo=2&detectionPointName=测试点
	 */
	public void  addDetectionPoint() {
		String json= null;
		if(dSercive.IsExisted(mPoint.getScenicSpotNo(), mPoint.getDetectionPointName())){		
			json = "{\"message\":\"existed\"}";
		}
		else{
			MDetectionPoint mDetectionPoint = dSercive.addDetectionPoint(mPoint);
			json = new Gson().toJson(mDetectionPoint);
		}
		WriteJson.writeToClient(json);
		
	}
	
	/**
	 * 更新监测点信息
	 * http://luorong:8080/SmartScenic/detection/detection_updateDetectionPoint.action?detectionPointNo=7&detectionPointName=测试点2
	 */
	public void updateDetectionPoint(){	
		MDetectionPoint mDetectionPoint = dSercive.updateDetectionPoint(mPoint);
		String json=null;
		if(mDetectionPoint==null){
			json = "{\"message\":\"existed\"}";
		}
		else {
			 json =  new Gson().toJson(mDetectionPoint);
		}  
		WriteJson.writeToClient(json);
	}

	/**
	 * 删除监测点
	 * http://luorong:8080/SmartScenic/detection/detection_delete.action?detectionPointNo=7
	 */
	public void delete(){
		String str = null;
		try {
			dSercive.deleteDetectionPoint(mPoint.getDetectionPointNo());
			str = "{\"message\":\"success\"}";
		} catch (Exception e) {
			str = "{\"message\":\"failed\"}";
		} finally {
			WriteJson.writeToClient(str);
		}
	}
	@Override
	public MDetectionPoint getModel() {
		return mPoint;
	}

}
