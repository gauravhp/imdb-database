package com.ohalo.moviedb.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: Gaurav Parmar
 * Date: 19-07-2021
 * Time: 20:00
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String home() {
        return "index";
    }
}