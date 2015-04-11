package com.marakana.sforums.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.marakana.sforums.domain.TimestampedEntity;

public class AbstractTimestampedEntityHibernateDao<E extends TimestampedEntity> extends AbstractHibernateDao<E> {

    @Override
    @Transactional(readOnly = false)
    public void save(E entity) throws DataAccessException {
        entity.markCreated();
        super.save(entity);
    }

}
