
package com.marakana.sforums.dao;

import java.util.List;

import com.marakana.sforums.domain.Category;

public interface CategoryDao {
    Category getById(Long id) throws DataAccessException;
    Category getByName(String name) throws DataAccessException;
    List<Category> getAll() throws DataAccessException;
    void save(Category category) throws DataAccessException;
    void delete(Category category) throws DataAccessException;
}
