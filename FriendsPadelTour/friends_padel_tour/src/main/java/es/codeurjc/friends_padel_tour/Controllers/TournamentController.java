package es.codeurjc.friends_padel_tour.Controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.classmate.members.RawMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.friends_padel_tour.Entities.Bussiness;
import es.codeurjc.friends_padel_tour.Entities.Tournament;
import es.codeurjc.friends_padel_tour.Service.PlayersService;
import es.codeurjc.friends_padel_tour.Service.TournamentsService;


@Controller
public class TournamentController {
    @Autowired
    TournamentsService tournamentsService;
    @Autowired
    PlayersService playerService;
    @Autowired
    private PlayersService bussinessService;

    @ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if (principal != null) {

			model.addAttribute("logged", true);
			model.addAttribute("userName", principal.getName());
            if(request.isUserInRole("USER")){
                model.addAttribute("user", request.isUserInRole("USER"));
                model.addAttribute("userId", playerService.findByUsername(principal.getName()).getId());
            }
            if(request.isUserInRole("BUSSINESS")){
                model.addAttribute("bussiness", request.isUserInRole("BUSSINESS"));
                model.addAttribute("userId", bussinessService.findByUsername(principal.getName()).getId());
            }
			model.addAttribute("admin", request.isUserInRole("ADMIN"));
            
            

		} else {
			model.addAttribute("logged", false);
		}
	}

    @GetMapping(value="/tournaments")
    public String getMethodName3(Model model) {
        return "tournaments";
    }

    @GetMapping(value="/joinTournament/{id}")
    public String joinTournament(@PathVariable long id,Model model) {
        Tournament tournamentToJoin = tournamentsService.findById(id);
        String userWhoJoinsName = (String) model.getAttribute("userName");
        
        return new String();
    }
    
    @RequestMapping(value="/create/{id}/tournament", method=RequestMethod.GET)
    public String createFriendlyMatch(@PathVariable Bussiness id,@RequestParam String name,@RequestParam String description,@RequestParam int fPrize,@RequestParam int sPrize,@RequestParam int min,@RequestParam int max,@RequestParam int category,@RequestParam String d1,@RequestParam String d2, @RequestParam String d3,@RequestParam String locality,@RequestParam String province,@RequestParam String postalCode,@RequestParam String date1,@RequestParam String date2,@RequestParam String date3,@RequestParam String date4,  Model model) {
        
        Tournament tournament = new Tournament(id, name, description, date1, date2, date3, date4, min, max, category, fPrize, sPrize, locality);
        tournamentsService.save(tournament);
        return "successTournamentCreation";
    }

    @GetMapping(value="/create/{id}")
    public String requestTournament(@PathVariable Bussiness id, Model model) {
        Bussiness bussiness = id;
        model.addAttribute("id", bussiness);
        return "tournamentRequest";
    }
}
