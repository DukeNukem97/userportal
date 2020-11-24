package com.user.login.userlogin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionController {
	@ExceptionHandler(value=UserAlreadyExists.class)
	public ResponseEntity<Object> exception(UserAlreadyExists e){
		return new ResponseEntity<Object>("User Exists",HttpStatus.FORBIDDEN);
	}

}
