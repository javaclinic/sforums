
package com.marakana.sforums.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractHibernateDao {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() throws HibernateException {
        return this.sessionFactory.getCurrentSession();
    }

    protected void save(Object entity) throws DataAccessException {
        this.logger.trace("Saving {}", entity);
        Session session = this.getSession();
        session.saveOrUpdate(entity);
        session.flush();
        this.logger.debug("Saved {}", entity);
    }

    protected void delete(Object entity) throws DataAccessException {
        this.logger.trace("Deleting {}", entity);
        Session session = this.getSession();
        session.delete(entity);
        this.logger.debug("Deleted {}", entity);
    }

    protected Object getById(Class<?> clazz, Serializable id) throws DataAccessException {
        this.logger.trace("Getting by {} id {}", clazz, id);
        Object result = this.getSession().get(clazz, id);
        this.logger.debug("Got {} by id {}", result, id);
        return result;
    }

    protected List<?> findAll(String hqlQuery, Object... params) throws DataAccessException {
        this.logger.trace("Finding all entities by query {}", hqlQuery);
        Query query = this.getSession().createQuery(hqlQuery);
        this.initQueryParams(query, params);
        List<?> result = query.list();
        logger.debug("Found {} entities by query {}", result.size(), hqlQuery);
        return result;
    }

    protected Object findOne(String hqlQuery, Object... params) throws DataAccessException {
        this.logger.trace("Finding one entity by query {}", hqlQuery);
        Query query = this.getSession().createQuery(hqlQuery);
        this.initQueryParams(query, params);
        Object result = query.uniqueResult();
        logger.debug("Found {} by query {}", result, hqlQuery);
        return result;
    }

    private void initQueryParams(Query query, Object... params) {
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
    }

}
