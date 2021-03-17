package es.codeurjc.friends_padel_tour.Controllers;

import java.security.Principal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import es.codeurjc.friends_padel_tour.Entities.PadelMatch;
import es.codeurjc.friends_padel_tour.Entities.Player;
import es.codeurjc.friends_padel_tour.Service.DoubleService;
import es.codeurjc.friends_padel_tour.Service.MatchesService;
import es.codeurjc.friends_padel_tour.Service.PlayersService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MatchController {

    @Autowired
    private MatchesService matchesService;
    @Autowired
    private PlayersService playerService;
    @Autowired
    private DoubleService doubleService;

    @ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if (principal != null) {

			model.addAttribute("logged", true);
			model.addAttribute("userName", principal.getName());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));

		} else {
			model.addAttribute("logged", false);
		}
	}

    @GetMapping(value="/team/{num}")
    public String friendlyMatchDivision(Model model, @PathVariable int num) {
         List<PadelMatch> matches = matchesService.findByDivision(num);
        model.addAttribute("matches", matches);
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

    @RequestMapping(value="/createFriendlyMatch/{num}", method=RequestMethod.GET)
    public String createFriendlyMatch(@PathVariable int num,@RequestParam String fmProvince,@RequestParam String fmcity,@RequestParam String fmfacility,@RequestParam String fmdate,@RequestParam String fmtime, Model model) {
        Player creator = playerService.findByName((String) model.getAttribute("userName"));
        PadelMatch newMatch = new PadelMatch(fmProvince,fmcity,fmfacility,fmdate,fmtime,num,creator);
        matchesService.save(newMatch);
        return "succesfullMatchCreation";
    }

    @GetMapping(value="/joinFriendlyMatchLonley/{id}")
    public String joinFrienlyMatchLonely(@PathVariable long id,Model model) {
        PadelMatch matchToJoin = matchesService.findById(id);
        if(matchToJoin.getnPlayers()==4){
            //match full error
            return "";
        }
        Player playerWhoJoins = playerService.findByName((String) model.getAttribute("userName"));
        return "";
    }
    
    
    


    
}
