package com.scenic.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.engine.query.spi.ReturnMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.scenic.model.MDetectionPoint;
import com.scenic.repo.interf.impl.IDetectionPointRespository;
import com.scenic.repo.interf.impl.IScenicSpotRespository;
import com.scenic.repo.pojo.DetectionPoint;
import com.scenic.repo.pojo.ScenicSpot;
import com.scenic.service.IDetectionPoinSercive;

@Service("detectionService")
public class DetectionPointServiceImpl implements IDetectionPoinSercive {
	@Autowired
	private IDetectionPointRespository dRespository;

	@Autowired
	private IScenicSpotRespository sRespository;

	@Override
	public List<MDetectionPoint> findByScenicSpotNo(Integer scenicSpotNo) {
		List<DetectionPoint> spot = dRespository.findByScenicSpotNo(scenicSpotNo);
		if (spot.isEmpty() || spot.size() == 0) {
			return null;
		}

		List<MDetectionPoint> mDetectionPoints = new ArrayList<MDetectionPoint>();
		for (int i = 0; i < spot.size(); i++) {
			MDetectionPoint mPoint = new MDetectionPoint(spot.get(i));
			mDetectionPoints.add(mPoint);
		}
		return mDetectionPoints;
	}

	@Override
	public MDetectionPoint addDetectionPoint(MDetectionPoint mPoint) {
		if (mPoint.getScenicSpotNo() == null
				|| mPoint.getDetectionPointName() == null)
			return null;

		ScenicSpot spot = sRespository.findOne(mPoint.getScenicSpotNo());
		DetectionPoint dPoint = new DetectionPoint(mPoint, spot);
		dRespository.save(dPoint);

		DetectionPoint detectionPoint = dRespository.findByNameAndScenic(mPoint
				.getScenicSpotNo(), mPoint.getDetectionPointName());

		return new MDetectionPoint(detectionPoint);
	}

	@Override
	public Boolean IsExisted(Integer scenicSpotNo, String detectionPointNames) {

		DetectionPoint dPoint = dRespository.findByNameAndScenic(scenicSpotNo,
				detectionPointNames);
		if (dPoint == null)
			return false;
		else
			return true;
	}

	@Override
	public MDetectionPoint updateDetectionPoint(MDetectionPoint mpoint) {

		if (mpoint.getDetectionPointNo() == null) {
			return null;
		}
		DetectionPoint dPoint = dRespository.findOne(mpoint.getDetectionPointNo());
		if(dPoint == null) return null;

		if (mpoint.getDetectionPointName() != null && !dPoint.getDetectionPointName().equals(mpoint.getDetectionPointName())) {
			if(IsExisted(dPoint.getScenicSpot().getScenicSpotNo(),mpoint.getDetectionPointName()))
				return null;
			dPoint.setDetectionPointName(mpoint.getDetectionPointName());
		}

		if (mpoint.getDetectionPointLongitude() != null)
			dPoint.setDetectionPointLongitude(mpoint
					.getDetectionPointLongitude());

		if (mpoint.getDetectionPointLatitude() != null)
			dPoint
					.setDetectionPointLatitude(mpoint
							.getDetectionPointLatitude());
		dRespository.saveAndFlush(dPoint);
		
		MDetectionPoint mDetectionPoint = new MDetectionPoint(dPoint);

		return mDetectionPoint;
	}

	@Override
	public void deleteDetectionPoint(Integer detectionNo) {
		dRespository.delete(detectionNo);
	}

}
