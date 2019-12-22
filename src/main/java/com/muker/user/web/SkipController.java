package com.muker.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName SkipController
 * @Description
 * @Author JavaQ
 * @Date 2019/12/18 17:00
 **/
@Controller
public class SkipController {

    @RequestMapping("/tologin")
    public String tologin() {
        return "login";
    }
}
