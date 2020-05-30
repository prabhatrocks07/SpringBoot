package com.mindtree.springboot.ws.web.api;

import java.util.Map;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mindtree.springboot.ws.web.DefaultExceptionAttributes;
import com.mindtree.springboot.ws.web.ExceptionAttributes;

public class BaseController {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleException(Exception exception, HttpServletRequest httpRequest) {
		logger.error("<BaseController> - handleException() - start");
		logger.error("Exception: " + exception);
		
		ExceptionAttributes exceptionAttributes = new DefaultExceptionAttributes();
		
		Map<String, Object> responseBody = exceptionAttributes.getExceptionAttributes(exception, httpRequest, HttpStatus.INTERNAL_SERVER_ERROR);
		
		logger.error("<BaseController> - handleException() - end");
		
		return new ResponseEntity<Map<String,Object>>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(NoResultException.class)
	public ResponseEntity<Map<String, Object>> handleNoResultException(Exception exception, HttpServletRequest httpRequest) {
		logger.error("<BaseController> - handleNoResultException() - start");
		logger.error("Exception: " + exception);
		
		ExceptionAttributes exceptionAttributes = new DefaultExceptionAttributes();
		
		Map<String, Object> responseBody = exceptionAttributes.getExceptionAttributes(exception, httpRequest, HttpStatus.NOT_FOUND);
		
		logger.error("<BaseController> - handleNoResultException() - end");
		
		return new ResponseEntity<Map<String,Object>>(responseBody, HttpStatus.NOT_FOUND);
		
	}

}
