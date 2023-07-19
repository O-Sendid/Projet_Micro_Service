package com.miage.alom.tp.battle_api.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Bad Request")  
public class ExceptionNotFound extends Exception {
	
	
}
