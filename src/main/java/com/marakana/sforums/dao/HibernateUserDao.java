
package com.marakana.sforums.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.marakana.sforums.domain.User;

public class HibernateUserDao extends AbstractTimestampedEntityHibernateDao<User> implements UserDao {

    @Transactional(readOnly = true)
    public List<User> getAll() throws DataAccessException {
        return super.findAll("from User order by firstName, lastName");
    }

    @Override
    @Transactional(readOnly = true)
    public User getByEmail(String email) throws DataAccessException {
        return (User) super.findOne("from User where email=?", email);
    }

}
