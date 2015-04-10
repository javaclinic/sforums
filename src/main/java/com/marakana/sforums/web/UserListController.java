package com.marakana.sforums.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.marakana.sforums.dao.DaoRepository;
import com.marakana.sforums.domain.User;

public class UserListController implements Controller {

    private final DaoRepository daoRepository;

    @Autowired
    public UserListController(DaoRepository daoRepository) {
        this.daoRepository = daoRepository;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<User> userList = this.daoRepository.getUserDao().getAll();
        ModelAndView mv = new ModelAndView("userList");
        mv.addObject("userList", userList);
        return mv;
    }

}
