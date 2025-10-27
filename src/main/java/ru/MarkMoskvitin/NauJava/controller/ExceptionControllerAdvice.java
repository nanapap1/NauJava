package ru.MarkMoskvitin.NauJava.controller;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView exception(ResourceNotFoundException e) {
        ModelAndView model = new ModelAndView("errUser");
        model.addObject("errorMessage", e.getMessage());
        return model;
    }

}