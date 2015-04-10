package com.marakana.sforums.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.marakana.sforums.dao.DaoRepository;
import com.marakana.sforums.domain.Category;

public class CategoryListController implements Controller {

    private final DaoRepository daoRepository;

    @Autowired
    public CategoryListController(DaoRepository daoRepository) {
        this.daoRepository = daoRepository;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Category> categoryList = this.daoRepository.getCategoryDao().getAll();
        ModelAndView mv = new ModelAndView("categoryList");
        mv.addObject("categoryList", categoryList);
        return mv;
    }

}
