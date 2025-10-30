package ru.MarkMoskvitin.NauJava.controller;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import ru.MarkMoskvitin.NauJava.exception.Exception;

@ControllerAdvice
public class ExceptionControllerAdvice
  {

      @ExceptionHandler(java.lang.Exception.class)
      @ResponseBody
      @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
      public Exception exception(java.lang.Exception e) {
          return Exception.create(e);
      }

      @ExceptionHandler(ResourceNotFoundException.class)
      @ResponseBody
      @ResponseStatus(HttpStatus.NOT_FOUND)
      public Exception exception(ResourceNotFoundException e)
      {
          return Exception.create(e);
      }

      @ExceptionHandler(HttpClientErrorException.class)
      @ResponseBody
      @ResponseStatus(HttpStatus.BAD_REQUEST)
      public Exception exception(HttpClientErrorException e)
      {
          return Exception.create(e);
      }

      @ExceptionHandler(HttpServerErrorException.class)
      @ResponseBody
      @ResponseStatus(HttpStatus.BAD_GATEWAY)
      public Exception exception(HttpServerErrorException e)
      {
          return Exception.create(e);
      }

      @ExceptionHandler(AccessException.class)
      @ResponseBody
      @ResponseStatus(HttpStatus.FORBIDDEN)
      public Exception exception(AccessException e) {
          return Exception.create(e);
      }

}