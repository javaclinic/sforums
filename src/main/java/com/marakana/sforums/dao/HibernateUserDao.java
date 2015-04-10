
package com.marakana.sforums.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.marakana.sforums.domain.User;

public class HibernateUserDao extends AbstractHibernateDao<User> implements UserDao {

	@Transactional(readOnly = true)
    public List<User> getAll() throws DataAccessException {
        return super.findAll("from User order by firstName, lastName");
    }

    @Override
    @Transactional(readOnly = true)
    public User getByEmail(String email) throws DataAccessException {
        return (User) super.findOne("from User where email=?", email);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(User user) throws DataAccessException {
        if ( !user.isIdSet() ) {
            user.setCreated(new Date());
        }
        super.save(user);
    }

}
