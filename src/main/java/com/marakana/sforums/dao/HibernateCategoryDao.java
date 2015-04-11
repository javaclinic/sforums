
package com.marakana.sforums.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

import com.marakana.sforums.domain.Category;

public class HibernateCategoryDao extends AbstractHibernateDao<Category> implements CategoryDao {

    @Override
    @Transactional(readOnly = true)
    public Category getByName(String name) throws DataAccessException {
        return (Category) super.findOne("from Category where name=?", name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAll() throws DataAccessException {
        return super.findAll("from Category order by name");
    }

    @Override
    @Transactional(readOnly = false)
    @Secured("ROLE_ADMIN")
    public void save(Category entity) throws DataAccessException {
        super.save(entity);
    }

    @Override
    @Transactional(readOnly = false)
    @Secured("ROLE_ADMIN")
    public void delete(Category entity) throws DataAccessException {
        super.delete(entity);
    }
}
