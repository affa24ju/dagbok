package com.dagbok.dagbok;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DairyController {

    @GetMapping
    public String getIndex(){
        return "index";
    }
    
}
