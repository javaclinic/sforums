package com.marakana.sforums.dao;

public class HsqlDaoRepository implements DaoRepository {

    private CategoryDao categoryDao = new HsqlCategoryDao();
    private UserDao userDao = new HsqlUserDao();

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

}
