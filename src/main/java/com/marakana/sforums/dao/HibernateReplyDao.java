package com.marakana.sforums.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.marakana.sforums.domain.Reply;

public class HibernateReplyDao extends AbstractTimestampedEntityHibernateDao<Reply> implements ReplyDao {

    @Override
    @Transactional(readOnly = true)
    public List<Reply> getAll() throws DataAccessException {
        return super.findAll("from Reply order by created");
    }

}
