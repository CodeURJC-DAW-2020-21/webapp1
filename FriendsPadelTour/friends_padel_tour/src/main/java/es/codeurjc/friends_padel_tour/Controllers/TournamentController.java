package es.codeurjc.friends_padel_tour.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TournamentController {

    @GetMapping(value="/tournaments")
    public String getMethodName3(Model model) {
        return "tournaments";
    }
    
}
