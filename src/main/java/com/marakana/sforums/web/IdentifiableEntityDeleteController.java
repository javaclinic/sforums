package com.marakana.sforums.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.marakana.sforums.domain.IdentifiableEntity;

public class IdentifiableEntityDeleteController extends AbstractIdentifiableEntityController {

    public IdentifiableEntityDeleteController() {
        super.setSupportedMethods(new String[] { "DELETE" });
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long id = ServletRequestUtils.getLongParameter(request, "id");
        IdentifiableEntity result = super.dao.getById(id);
        if ( result == null ) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            super.dao.delete(result);
            super.logger.debug("Deleted entity by id {}", id);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
        return null;
    }

}
