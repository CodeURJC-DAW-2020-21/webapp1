package es.codeurjc.friends_padel_tour;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class PruebaController {

    @GetMapping(value="/Index")
    public String getMethodName(Model model) {
        return  "Index";
    }

    @GetMapping(value="/team")
    public String getMethodName2(Model model) {
        return "team";
    }
    
    
}
