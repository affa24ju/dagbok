package com.dagbok.dagbok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class DairyController {

    @Autowired
    private DairyRepository dairyRepository; 

    //Handles Get request & adds data to the model
    @GetMapping
    public String getIndex(Model model){
        model.addAttribute("dairies", dairyRepository.findAll());
        //Adding an empty Dairy object for the form
        model.addAttribute("dairy", new Dairy());
        return "index";
    }

    //Handles Post requests from the form & save as a new dairy entry
    @PostMapping("/new-post")
    public String addNew(@ModelAttribute Dairy dairy) {
        //@ModelAttribute Dairy dairy binds the form data to a Dairy object

        //it shows error "duplicate primary id", så sätter 0 för säkerhetsskull & det funkar
        dairy.setId(0);

        //to save in database
        dairyRepository.save(dairy);

        //Dairy dairy = new Dairy();
        //dairy.setTitle("My first post");
        //dairy.setDate(new java.util.Date());
        //dairy.setText("Hello! This is my first post!");
        //dairyRepository.save(dairy);

        return "redirect:/";
    }  
    
}
