package edu.cuny.brooklyn.cisc3120.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import edu.cuny.brooklyn.cisc3120.web.exception.StudentNotFoundException;

@ControllerAdvice
public class ExceptionHandlingController {
    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlingController.class);
            
    @ExceptionHandler(StudentNotFoundException.class)
    public ModelAndView handleError(HttpServletRequest req, StudentNotFoundException e) {
      LOGGER.error("Request: " + req.getRequestURL() + " raised " + e);

      ModelAndView mav = new ModelAndView();
      mav.addObject("exception", e);
      mav.addObject("url", req.getRequestURL());
      mav.setViewName("erroronstudentnotfound");
      return mav;
    }
}
