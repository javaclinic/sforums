
package com.marakana.sforums.dao;

import java.lang.reflect.ParameterizedType;
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
import org.springframework.transaction.annotation.Transactional;

import com.marakana.sforums.domain.IdentifiableEntity;

@Repository
public abstract class AbstractHibernateDao<E extends IdentifiableEntity> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unchecked")
    private final Class<E> domainClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() throws HibernateException {
        return this.sessionFactory.getCurrentSession();
    }

    @Transactional(readOnly = false)
    public void save(E entity) throws DataAccessException {
        this.logger.trace("Saving {}", entity);
        Session session = this.getSession();
        session.saveOrUpdate(entity);
        session.flush();
        this.logger.debug("Saved {}", entity);
    }

    @Transactional(readOnly = false)
    public void delete(E entity) throws DataAccessException {
        this.logger.trace("Deleting {}", entity);
        Session session = this.getSession();
        session.delete(entity);
        this.logger.debug("Deleted {}", entity);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public E getById(Long id) throws DataAccessException {
        this.logger.trace("Getting entity by id {}", id);
        Object result = this.getSession().get(this.domainClass, id);
        this.logger.debug("Got {} by id {}", result, id);
        return (E) result;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<E> getAll() {
        return this.getSession().createCriteria(this.domainClass).list();
    }

    @SuppressWarnings("unchecked")
    protected List<E> findAll(String hqlQuery, Object... params) throws DataAccessException {
        this.logger.trace("Finding all entities by query {}", hqlQuery);
        Query query = this.getSession().createQuery(hqlQuery);
        this.initQueryParams(query, params);
        List<?> result = query.list();
        logger.debug("Found {} entities by query {}", result.size(), hqlQuery);
        return (List<E>) result;
    }

    @SuppressWarnings("unchecked")
    protected Object findOne(String hqlQuery, Object... params) throws DataAccessException {
        this.logger.trace("Finding one entity by query {}", hqlQuery);
        Query query = this.getSession().createQuery(hqlQuery);
        this.initQueryParams(query, params);
        Object result = query.uniqueResult();
        logger.debug("Found {} by query {}", result, hqlQuery);
        return (E) result;
    }

    private void initQueryParams(Query query, Object... params) {
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
    }

}
