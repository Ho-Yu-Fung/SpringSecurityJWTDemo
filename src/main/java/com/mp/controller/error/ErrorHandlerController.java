package com.mp.controller.error;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class ErrorHandlerController {
    @RequestMapping("/403")
    public String error403(){
        return "权限不足";
    }
    @RequestMapping("/404")
    public String error404(){
        return "NO FOUND";
    }
    @RequestMapping("/500")
    public String error500(){
        return "服务器出错拉";
    }
}
