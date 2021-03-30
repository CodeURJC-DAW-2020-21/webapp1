package es.codeurjc.friends_padel_tour.Controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.codeurjc.friends_padel_tour.Entities.Bussiness;
import es.codeurjc.friends_padel_tour.Entities.Tournament;
import es.codeurjc.friends_padel_tour.Service.BussinessService;
import es.codeurjc.friends_padel_tour.Service.PlayersService;
import es.codeurjc.friends_padel_tour.Service.TournamentsService;
import es.codeurjc.friends_padel_tour.Service.UserService;



@Controller
public class TournamentController {

    //Autowired section
    @Autowired
    private TournamentsService tournamentsService;
    @Autowired
    private PlayersService playerService;
    @Autowired
    private BussinessService bussinessService;
    @Autowired
    private UserService userService;

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
            if(request.isUserInRole("ADMIN")){
                model.addAttribute("admin", request.isUserInRole("ADMIN"));
                model.addAttribute("userId", userService.findByUsername(principal.getName()).getId());
            }
		} else {
			model.addAttribute("logged", false);
		}
	}

    @GetMapping(value="/tournaments")
    public String showTournaments(Model model) {
        List<Tournament> tournamentsaccepted = tournamentsService.getAllAccepted();
        model.addAttribute("tournaments", tournamentsaccepted);
        return "tournaments";
    }

    @GetMapping(value="/joinTournament/{id}")
    public String joinTournament(@PathVariable long id,Model model) {
        Tournament tournamentToJoin = tournamentsService.findById(id);
        String userWhoJoinsName = (String) model.getAttribute("userName");
        
        return new String();
    }

    @GetMapping(value="/tournamentManagement")
    public String goToTournamentManagment(Model model) {
        List<Tournament> pendingTournaments = tournamentsService.getPending();
        model.addAttribute("pendingTournaments", pendingTournaments);
        return "tournamentManagement";
    }
    
    
    @PostMapping(value="/create/tournament")
    public String createTournament(Tournament tournament,Model model) {
        Long bussinessId = (Long) model.getAttribute("userId");
        Bussiness loggedBussiness = bussinessService.findById(bussinessId);
        tournament.setBussinnes(loggedBussiness);
        tournamentsService.save(tournament);
        loggedBussiness.getTournaments().add(tournament);
        bussinessService.updateBussiness(loggedBussiness);
        return "successTournamentCreation";
    }

    @GetMapping(value="/acceptTournament/{id}")
    public String acceptTournament(@PathVariable int id, Model model){
        Tournament tournament = tournamentsService.findById(id);
        tournament.setAccepted(true);
        tournamentsService.save(tournament);
        return "successEditTournament";
    }

    @GetMapping(value="/declineTournament/{id}")
    public String declineTournament(@PathVariable int id, Model model){
        tournamentsService.deleteById(id);
        return "successEditTournament";
    }

    @GetMapping(value="/tournamentRequest")
    public String tournamentReque(Model model) {
        return "tournamentRequest";
    }
    
}
