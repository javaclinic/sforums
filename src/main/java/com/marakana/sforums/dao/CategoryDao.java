
package com.marakana.sforums.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.marakana.sforums.domain.Category;

public interface CategoryDao {
    public Category getById(Long id) throws DataAccessException;

    public Category getByName(String name) throws DataAccessException;

    public List<Category> getAll() throws DataAccessException;

    public void save(Category category) throws DataAccessException;

    public void delete(Category category) throws DataAccessException;
}
