package com.marakana.sforums.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.marakana.sforums.domain.Category;
import com.marakana.sforums.domain.Forum;

public class HibernateForumDao extends AbstractHibernateDao<Forum> implements ForumDao {

    @Override
    @Transactional(readOnly = true)
    public Forum getByCategoryAndName(Category category, String name) throws DataAccessException {
        return super.findOne(
                super
                    .getNamedQuery("forum-by-category-and-name")
                    .setParameter("category", category)
                    .setParameter("name", name)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Forum> getAll() throws DataAccessException {
        return super.findAll(super.getNamedQuery("all-forums"));
    }

    @Override
    @Transactional(readOnly = true)
    public Forum getById(Long id) throws DataAccessException {
        return super.findOne(
                super
                    .getNamedQuery("forum-by-id-fetch-all")
                    .setParameter("id", id)
                );
    }
}
