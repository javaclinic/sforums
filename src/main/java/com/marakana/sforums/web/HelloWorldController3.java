package com.marakana.sforums.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

@Controller
public class HelloWorldController3 extends ParameterizableViewController {

    @RequestMapping(value="/HelloWorld3.html", method=RequestMethod.GET)
    public ModelAndView hello() {
        String message = "Hello World by Annotation!!!";
        ModelAndView mv = new ModelAndView();
        mv.setViewName("helloWorld");
        mv.addObject("message", message);
        return mv;
    }

}
