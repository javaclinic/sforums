
package com.marakana.sforums.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class HsqlAbstractDatabaseDao {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String jndiName = "java:comp/env/jdbc/sforums";

    public void setJndiName(String jndiName) {
        this.jndiName = jndiName;
    }

    protected Connection getConnection() throws DataAccessException {
        try {
            Context ctx = new InitialContext();
            try {
                DataSource ds = (DataSource) ctx.lookup(this.jndiName);
                Connection connection = ds.getConnection();
                logger.trace("Got connection");
                return connection;
            } finally {
                ctx.close();
            }
        } catch (NamingException e) {
            throw new DataAccessException("Failed to lookup connection from JNDI by name: " + this.jndiName, e);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to get connection from DataSource: " + this.jndiName, e);
        }
    }

    protected Long getGeneratedKey(PreparedStatement ps)  throws DataAccessException, SQLException {
        ResultSet generatedKeys = ps.getGeneratedKeys();
        if ( generatedKeys.next() ) {
            return generatedKeys.getLong(1);
        } else {
            throw new DataAccessException("Could not get database-generated key.");
        }
    }
}
