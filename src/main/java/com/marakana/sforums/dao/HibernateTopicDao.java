package com.marakana.sforums.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.marakana.sforums.domain.Forum;
import com.marakana.sforums.domain.Topic;

public class HibernateTopicDao extends AbstractHibernateDao<Topic> implements TopicDao {

    @Override
    @Transactional(readOnly = true)
    public Topic getByForumAndTitle(Forum forum, String title) throws DataAccessException {
        return super.findOne(
                super
                    .getNamedQuery("topic-by-forum-and-title")
                    .setParameter("forum", forum)
                    .setParameter("title", title)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Topic> getAll() throws DataAccessException {
        return super.findAll(super.getNamedQuery("all-topics"));
    }
}
