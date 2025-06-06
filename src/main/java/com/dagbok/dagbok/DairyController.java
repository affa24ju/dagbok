package com.dagbok.dagbok;
import java.sql.Date;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class DairyController {

    @Autowired
    private DairyRepository dairyRepository; 

    //Handles Get request & adds data to the model
    @GetMapping
    public String getIndex(Model model){

        Date today = new Date(Calendar.getInstance().getTimeInMillis());
        //Visar bara de som inte är deletad och inte har framtida datum
        model.addAttribute("dairies", dairyRepository.findAllByIsDeletedFalseAndDateLessThanEqual(today));
        
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

        //För att spara i databas
        dairyRepository.save(dairy);

        //Dairy dairy = new Dairy();
        //dairy.setTitle("My first post");
        //dairy.setDate(new java.util.Date());
        //dairy.setText("Hello! This is my first post!");
        //dairyRepository.save(dairy);

        return "redirect:/";
    }  

    //Handles deleting a post from webpage
    @PostMapping("/delete-post/{id}")
    public String deletePost(@PathVariable("id")int id) {

        //Om id inte stämmer med databas, visar felmeddelande
        Dairy dairy = dairyRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("Invalid dairy id: " + id));
        
        //Markerar som deletad & döljar från sidan men finns kvar i databas, soft delete
        dairy.setDeleted(true);
        dairyRepository.save(dairy);

        System.out.println("Deleted from website");
        return "redirect:/";
    }

    //Handles permanent delete from database
    @PostMapping("/permanent-delete-post/{id}")
    public String permanentDeletePost(@PathVariable("id")int id){
        Dairy dairy = dairyRepository.findById(id).orElseThrow( ()-> new IllegalArgumentException("Invalid dairy id: " + id));
        
        //deletar från databas, hard delete
        dairyRepository.delete(dairy);
        return "redirect:/";
    }


    //Show edit form with the existing dairy data
    @GetMapping("/edit-post/{id}")
    public String editPost(@PathVariable("id")int id, Model model){
        Dairy dairy = dairyRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("Invalid dairy id: " + id));
        //Adding dairy to the model for editing
        model.addAttribute("dairy", dairy);
        //Tillbaka till edit-post
        return "edit-post";
    }

    //Save the updated dairy
    @PostMapping("/edit-post/{id}")
    public String saveEdit(@PathVariable("id")int id, @ModelAttribute Dairy dairy){
        
        Dairy existingDairy = dairyRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("Invalid id: " + id));

        //updatera existing dairy detalj
        existingDairy.setTitle(dairy.getTitle());
        existingDairy.setDate(dairy.getDate());
        existingDairy.setText(dairy.getText());

        //Sparar uppdaterad data
        dairyRepository.save(existingDairy);

        return "redirect:/";
    }
    
}
