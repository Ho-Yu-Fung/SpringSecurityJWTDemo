package com.mp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@PreAuthorize("hasAnyAuthority('user')")
public class ProductTestController {

    @RequestMapping("/info")
    public String productInfo(){
        return " some product info ";
    }
}