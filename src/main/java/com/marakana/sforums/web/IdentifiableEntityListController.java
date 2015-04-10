package com.marakana.sforums.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.marakana.sforums.domain.IdentifiableEntity;

public class IdentifiableEntityListController extends AbstractIdentifiableEntityController {
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<IdentifiableEntity> result = super.dao.getAll();
        super.logger.debug("Got {} entities", result.size());
        ModelAndView mv = new ModelAndView(super.getViewName());
        mv.addObject(result);
        return mv;
    }
}
