package com.minazg.controller;

import com.minazg.exception.TaskNotFoundException;
import com.minazg.exception.UserNotFoundException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandlingController {

    public static final String DEFAULT_ERROR_VIEW = "error";


    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView noUserException(HttpServletRequest req, UserNotFoundException e){

        ModelAndView mav = new ModelAndView();

        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURI());
        mav.setViewName(DEFAULT_ERROR_VIEW);

        return mav;

    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ModelAndView noTaskException(HttpServletRequest req, TaskNotFoundException e){

        ModelAndView mav = new ModelAndView();

        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURI());
        mav.setViewName(DEFAULT_ERROR_VIEW);

        return mav;

    }


    @ExceptionHandler(value = Exception.class)
    public ModelAndView
    defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
        if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null)
            throw e;

        // Otherwise setup and send the user to a default error-view.
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}
