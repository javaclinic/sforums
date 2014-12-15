
package com.marakana.sforums.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.marakana.sforums.domain.Category;

public class HsqlCategoryDao extends HsqlAbstractDatabaseDao implements CategoryDao {
	
	private static final String SQL_INSERT         = "INSERT INTO category (name, description) VALUES (?, ?)";
    private static final String SQL_UPDATE         = "UPDATE category SET name=?, description=? WHERE id=?";
    private static final String SQL_DELETE         = "DELETE FROM category WHERE id=?";
    private static final String SQL_SELECT_ALL     = "SELECT id, name, description FROM category ORDER BY name";
    private static final String SQL_SELECT_BY_ID   = "SELECT id, name, description FROM category WHERE id=?";
    private static final String SQL_SELECT_BY_NAME = "SELECT id, name, description FROM category WHERE name LIKE ?";

    public void save(Category category) throws DataAccessException {
        synchronized (category) {
            try (Connection connection = super.getConnection()) {
                if (category.isIdSet()) {
                    this.update(category, connection);
                } else {
                    this.create(category, connection);
                }
            } catch (SQLException e) {
                String msg = e.getMessage();
                if (msg != null && msg.contains("Duplicate")) {
                    throw new DuplicateIdException(category.getName(), e);
                }
                throw new DataAccessException("Failed to create: " + category, e);
            }
        }
    }

    private void create(Category category, Connection connection) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_INSERT,Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.executeUpdate();
            category.setId(super.getGeneratedKey(stmt));
        }
        super.logger.debug("Created {} with id {}", category, category.getId());

    }

    private void update(Category category, Connection connection) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE)) {
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.setLong(3, category.getId());
            stmt.executeUpdate();
            super.logger.debug("Updated {}", category);
        }
    }

    public void delete(Category category) throws DataAccessException {
        try (Connection connection = super.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(SQL_DELETE)) {
                stmt.setLong(1, category.getId());
                stmt.executeUpdate();
                super.logger.debug("Deleted {}", category);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to delete category: " + category, e);
        }
    }

    public List<Category> getAll() throws DataAccessException {
        try (Connection connection = super.getConnection()) {
            try (Statement stmt = connection.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL)) {
                    List<Category> categoryList = new ArrayList<Category>();
                    while (rs.next()) {
                        categoryList.add(this.loadFromRow(rs));
                    }
                    super.logger.debug("Got {} categories", categoryList.size());
                    return categoryList;
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to get all categories", e);
        }
    }

    public Category getById(Long id) throws DataAccessException {
        try (Connection connection = super.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_ID)) {
                stmt.setLong(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    Category category = rs.next() ? this.loadFromRow(rs) : null;
                    super.logger.debug("Got {} by id {}", category, id);
                    return category;
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to get category by id: " + id, e);
        }
    }

    public Category getByName(String name) throws DataAccessException {
        try (Connection connection = super.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_NAME)) {
                stmt.setString(1, name);
                try (ResultSet rs = stmt.executeQuery()) {
                    Category category = rs.next() ? this.loadFromRow(rs) : null;
                    super.logger.debug("Got {} by name {}", category, name);
                    return category;
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to get category by name: " + name, e);
        }
    }

    private Category loadFromRow(ResultSet row) throws SQLException {
        Category category = new Category();
        category.setId(row.getLong(1));
        category.setName(row.getString(2));
        category.setDescription(row.getString(3));
        return category;
    }
}
