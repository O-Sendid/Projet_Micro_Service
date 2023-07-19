package com.miage.alom.game_ui.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;




@ControllerAdvice
public class SecurityControllerAdvice {

	
    @ModelAttribute(value = "user")
    Object principal(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) auth.getPrincipal();
        return principal;
    }
    
    
    
}
