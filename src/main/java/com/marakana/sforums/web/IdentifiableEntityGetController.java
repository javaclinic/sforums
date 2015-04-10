package com.marakana.sforums.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.marakana.sforums.domain.IdentifiableEntity;

public class IdentifiableEntityGetController extends AbstractIdentifiableEntityController {
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long id = ServletRequestUtils.getLongParameter(request, "id");
        IdentifiableEntity result = super.dao.getById(id);
        super.logger.debug("Got {} by id {}", result, id);
        ModelAndView mv = new ModelAndView(super.getViewName());
        mv.addObject(result);
        return mv;
    }
}
