package es.codeurjc.friends_padel_tour.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MatchController {

    @GetMapping(value="/team/{num}")
    public String getMethodName2(Model model, @PathVariable int num) {
        switch (num) {
            case 1: return "team";
            case 2: return "team2";
            case 3: return "team3";
            case 4: return "team4";
            case 5: return "team5";
            case 6: return "team6";
            default: return "team";
        }
    }

    @GetMapping(value="/friendlyMatch")
    public String getMethodName7(Model model) {
        return "friendlyMatch";
    }
    
}
