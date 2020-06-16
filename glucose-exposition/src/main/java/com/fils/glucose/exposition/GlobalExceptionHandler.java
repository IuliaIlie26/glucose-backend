package com.fils.glucose.exposition;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fils.glucose.application.exception.TechnicalException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(TechnicalException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleBusinessException(HttpServletRequest request, TechnicalException exception) {
		return exceptionToErrorResponse(exception);
	}

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse defaultErrorHandler(HttpServletRequest request, Exception exception) {
		return exceptionToErrorResponse(exception);
	}

	private ErrorResponse exceptionToErrorResponse(Exception exception) {
		String messageKey = exception.getMessage();
		exception.printStackTrace();
		return new ErrorResponse(messageKey);
	}

}
