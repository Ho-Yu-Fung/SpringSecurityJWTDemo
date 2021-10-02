package com.mp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('sys:k:add')")
public class AdminTestController {

    @RequestMapping("/home")
    public String productInfo(){

        return " admin home page ";
    }
}