package com.marakana.sforums.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

public class HibernateStatisticsFactoryBean implements FactoryBean<Statistics>{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Statistics getObject() throws Exception {
        return this.sessionFactory.getStatistics();
    }

    @Override
    public Class<?> getObjectType() {
        return Statistics.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
