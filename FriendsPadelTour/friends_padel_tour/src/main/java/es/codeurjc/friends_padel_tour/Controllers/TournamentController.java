package es.codeurjc.friends_padel_tour.Controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.friends_padel_tour.Entities.Bussiness;
import es.codeurjc.friends_padel_tour.Entities.DoubleOfPlayers;
import es.codeurjc.friends_padel_tour.Entities.Player;
import es.codeurjc.friends_padel_tour.Entities.Tournament;
import es.codeurjc.friends_padel_tour.Service.BussinessService;
import es.codeurjc.friends_padel_tour.Service.DoubleService;
import es.codeurjc.friends_padel_tour.Service.PlayersService;
import es.codeurjc.friends_padel_tour.Service.TournamentsService;
import es.codeurjc.friends_padel_tour.Service.UserService;
import org.springframework.web.bind.annotation.RequestBody;





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
    @Autowired
    private DoubleService doubleService;

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
    public String showTournaments(Model model,@RequestParam(required = false, defaultValue = "3") int pageSize) {
        Player loggedPlayer;
        if(model.getAttribute("userId")==null){
            loggedPlayer= null;
        }else{
            loggedPlayer = playerService.findById((long) model.getAttribute("userId"));
        }
        List<Player> userDoubles = null;
        if(loggedPlayer !=null){
            userDoubles = doubleService.findDoublesOf(loggedPlayer.getUsername());
        }
        List<Tournament> tournamentsaccepted = tournamentsService.getAllAccepted();
        model.addAttribute("userDoubles", userDoubles);
        model.addAttribute("tournaments", tournamentsaccepted);



        Page<Tournament> tournaments = tournamentsService.getPageTournaments(0, pageSize);
        
        model.addAttribute("tournamentspage", tournaments);
        return "tournaments";
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

    @PostMapping(value="/joinTournament/{id}")
    public String joinTournamen(@PathVariable long id, @RequestParam String doubleSelect, Model model) {
        DoubleOfPlayers doubleWhoJoin = doubleService.findDouble((String)model.getAttribute("userName"),doubleSelect);
        Tournament tournamentToJoin = tournamentsService.findById(id);

        doubleWhoJoin.getTournaments().add(tournamentToJoin);
        tournamentToJoin.getPlayers().add(doubleWhoJoin);
        
        tournamentToJoin.setRegisteredCouples(tournamentToJoin.getRegisteredCouples()+1);
        if(tournamentToJoin.getRegisteredCouples()==tournamentToJoin.getMaxCouples()){
            tournamentToJoin.setFull(true);
        }
        doubleService.update(doubleWhoJoin);
        tournamentsService.uptdate(tournamentToJoin);
        return "joiningSucces";
    }

    @GetMapping(value="/tournamentInfo/{id}")
    public String tournamentInfo(@PathVariable long id, Model model) {
        Tournament tournament = tournamentsService.findById(id);
        model.addAttribute("tournament", tournament);
        model.addAttribute("doubles", tournament.getPlayers());
        return "tournamentInfo";
    }

    @GetMapping(value="/deleteTournament/{id}")
    public String deleteTournamen(@PathVariable long id, Model model) {
        Long bussinessId = (Long) model.getAttribute("userId");
        Bussiness loggedBussiness = bussinessService.findById(bussinessId);
        Tournament torunament = tournamentsService.findById(id);

        loggedBussiness.getTournaments().remove(torunament);

        tournamentsService.deleteById(id);
        return "successEditTournament";
    }
    
    @PostMapping(value="/selectTournamentWinner/{id}")
    public String selectTournamentWinner(@PathVariable long id,Model model,@RequestParam int doubleSelect) {
        Tournament tournament = tournamentsService.findById(id);
        DoubleOfPlayers winners = tournament.getPlayers().get(doubleSelect-1);
        tournament.setFirstWinnngCouple(winners);
        tournament.setFinished(true);

        tournamentsService.uptdate(tournament);
        return "successEditTournament";
    }
    

}
