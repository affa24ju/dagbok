package com.dagbok.dagbok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DairyController {

    @Autowired
    private DairyRepository dairyRepository; 

    @GetMapping
    public String getIndex(Model model){
        model.addAttribute("dairies", dairyRepository.findAll());
        return "index";
    }

    @GetMapping("/new-post")
    public String addNew() {

        //Dairy dairy = new Dairy();
        //dairy.setTitle("My first post");
        //dairy.setDate(new java.util.Date());
        //dairy.setText("Hello! This is my first post!");
        //dairyRepository.save(dairy);



        return "redirect:/";
    }  
    
}
