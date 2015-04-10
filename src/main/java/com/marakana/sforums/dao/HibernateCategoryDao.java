
package com.marakana.sforums.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
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

}
