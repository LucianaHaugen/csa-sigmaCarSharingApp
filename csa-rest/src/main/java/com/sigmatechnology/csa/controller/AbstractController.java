package com.sigmatechnology.csa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lucianahaugen on 05/09/17.
 */
@RestController
@RequestMapping(value = "/rest")
public abstract class AbstractController {


    public final static Logger LOG = LoggerFactory.getLogger(AbstractController.class);
    public final static String INVALID_FORMAT = "Invalid format";
    public final static String SERVER_ERROR = "Server error";

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<String> unreadableMessage(HttpMessageNotReadableException e){
        LOG.error(INVALID_FORMAT, e);

        return  new ResponseEntity<String>(INVALID_FORMAT, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<String> unknownError(Throwable e){
        LOG.error(SERVER_ERROR, e);

        return new ResponseEntity<String>(SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
