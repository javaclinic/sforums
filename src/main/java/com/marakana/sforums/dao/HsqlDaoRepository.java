package com.marakana.sforums.dao;

public class HsqlDaoRepository implements DaoRepository {

    private CategoryDao categoryDao = new HsqlCategoryDao();

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

}
