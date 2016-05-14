package com.scenic.repo.interf.impl;

import com.scenic.repo.pojo.RealTimeData;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.Date;

/**
 * Created by linyanying on 2016/5/13.
 * 暂不使用
 */
//@Service
public class RealTimeDataRespositoryimpl  {
    Configuration configuration = new Configuration().configure();
    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
            applySettings(configuration.getProperties());
    SessionFactory factory = configuration.buildSessionFactory(builder.build());

    public RealTimeData findByLastTime(Integer detectionPointNo, Date time1) {
        Session session = factory.getCurrentSession();
        Criteria criteria=session.createCriteria(RealTimeData.class);
        criteria.add(Restrictions.eq("scenic_spot_no",detectionPointNo));
        criteria.add(Restrictions.eq("rtd_time",time1));
        criteria.addOrder(Order.desc("rtd_no"));
        criteria.setMaxResults(1);

        RealTimeData realTimeData = (RealTimeData) criteria.uniqueResult();

        return realTimeData;
    }
}
