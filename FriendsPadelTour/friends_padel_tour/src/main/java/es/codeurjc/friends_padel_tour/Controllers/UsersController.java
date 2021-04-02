package es.codeurjc.friends_padel_tour.Controllers;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.friends_padel_tour.Entities.Bussiness;
import es.codeurjc.friends_padel_tour.Entities.DoubleOfPlayers;
import es.codeurjc.friends_padel_tour.Entities.PadelMatch;
import es.codeurjc.friends_padel_tour.Entities.Player;
import es.codeurjc.friends_padel_tour.Entities.Tournament;
import es.codeurjc.friends_padel_tour.Repositories.TournamentRepository;
import es.codeurjc.friends_padel_tour.Service.BussinessService;
import es.codeurjc.friends_padel_tour.Service.DoubleService;
import es.codeurjc.friends_padel_tour.Service.MatchesService;
import es.codeurjc.friends_padel_tour.Service.PlayersService;
import es.codeurjc.friends_padel_tour.Service.TournamentsService;
import es.codeurjc.friends_padel_tour.Service.UserService;
import net.bytebuddy.asm.Advice.Return;






@Controller
public class UsersController {

    //Autowired section
    @Autowired
    private PlayersService playerService;
    @Autowired
    private BussinessService bussinessService;
    @Autowired
    private DoubleService doubleService;
    @Autowired
    private MatchesService matchesService;
    @Autowired
    private TournamentsService tournamentsService;
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
                model.addAttribute("loggedUser", playerService.findByUsername(principal.getName()));
            }
            if(request.isUserInRole("BUSSINESS")){
                model.addAttribute("bussiness", request.isUserInRole("BUSSINESS"));
                model.addAttribute("userId", bussinessService.findByUsername(principal.getName()).getId());
            }
			model.addAttribute("admin", request.isUserInRole("ADMIN"));
            model.addAttribute("userId", userService.findByUsername(principal.getName()).getId());
		} else {
			model.addAttribute("logged", false);
		}
	}


    @GetMapping(value="/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping(value="/goToSignUpPrev")
    public String goToSignUp(Model model) {
        return "previousSignUp";
    }

    @GetMapping(value="/playerSignUpForm")
    public String playerSignUp(Model model) {
        return "userSignUp";
    }

    @GetMapping(value="/bussinessSignUpForm")
    public String bussinessSignUp(Model model) {
        return "bussinessSignUp";
    }


    @PostMapping(value = "/signUpBussiness")
    public String signUpBussiness(Bussiness loggedBussiness,Model model){
        bussinessService.saveBussiness(loggedBussiness);
        model.addAttribute("bussiness", loggedBussiness);
        model.addAttribute("userId", loggedBussiness.getId());
        model.addAttribute("logged", true);
        model.addAttribute("bussinessName", loggedBussiness.getBussinessName());
        model.addAttribute("adress", loggedBussiness.getAdress());
        model.addAttribute("scheduleHeader", loggedBussiness.getSchedule()[0]);
        model.addAttribute("scheduleMorning", loggedBussiness.getSchedule()[1]);
        model.addAttribute("scheduleAfternoon", loggedBussiness.getSchedule()[2]);
        model.addAttribute("acceptedTournaments",null);
        model.addAttribute("nonAcceptedTournaments",null);
        model.addAttribute("bussinessId", loggedBussiness.getId());
        model.addAttribute("createdTournaments", loggedBussiness.getCreatedTournaments());
        return "bussinessProfile";
    }

    @PostMapping(value="/signUpPlayer")
    public String signUpUser(Player loggedPlayer,Model model) {
        if(!playerService.savePlayer(loggedPlayer))
            return "404";
        model.addAttribute("loggedUser", loggedPlayer);
        List<DoubleOfPlayers> userDoubles = doubleService.findDoublesOf(loggedPlayer.getUsername());
        model.addAttribute("userDoubles", userDoubles);
        model.addAttribute("efectivity", 0);
        model.addAttribute("userCreatedGames", loggedPlayer.getCreatedMatches());
        model.addAttribute("userPlayedGames", loggedPlayer.getPlayedMatches());
        model.addAttribute("userPendingGames", loggedPlayer.getPendingMatches());
        return "userProfile";
    }

    @RequestMapping("/loginError")
	public String loginerror() {
		return "loginError";
	}

        

    @GetMapping(value = "/users/{username}")
    public String goToOtherPlayerProfile(Model model,@PathVariable String username) {
        Player loggedPlayer = playerService.findByUsername(username);
        int playedmatches = loggedPlayer.getMathesPlayed();
        boolean hasplayedmatches = (playedmatches > 0);
        Boolean notMyProfile;
        if(model.getAttribute("userName")==null){
            notMyProfile=true;
        }else{
            notMyProfile = !model.getAttribute("userName").equals(username);
        }
        model.addAttribute("loggedUser", loggedPlayer);
        List<DoubleOfPlayers> userDoubles = doubleService.findDoublesOf(loggedPlayer.getUsername());
        model.addAttribute("userDoubles", userDoubles);
        model.addAttribute("loggedUser.matchesWon", loggedPlayer.getMathcesWon());
        model.addAttribute("loggedUser.matchesPlayed", loggedPlayer.getMathesPlayed());
        model.addAttribute("loggedUser.matchesLost", loggedPlayer.getMatchesLost());
        double efectivity;
        if(loggedPlayer.getMathesPlayed()==0)
            efectivity=0;
        else
            efectivity =  (((double) loggedPlayer.getMathcesWon())/ ((double)loggedPlayer.getMathesPlayed()))*100;
            double efectivity2 = Math.floor(efectivity);
        model.addAttribute("efectivity", efectivity2);
        model.addAttribute("userCreatedGames", loggedPlayer.getCreatedMatches());
        model.addAttribute("UserExtern", notMyProfile);
        model.addAttribute("hasplayedmatches", hasplayedmatches);
        return "userProfile";
    }

    @GetMapping(value="/bussinessProfile/{username}")
    public String bussinessProfile(Model model,@PathVariable String username,@Qualifier("accepted")@PageableDefault(page=0, size=10) Pageable pageable,@Qualifier("nonAccepted")@PageableDefault(page=0, size=10)  Pageable pageable2) {
        
        Bussiness loggedBussiness = bussinessService.findByUsername(username);
        Page<Tournament> acceptedTournaments = tournamentsService.getAccepted(loggedBussiness, pageable);
        Page<Tournament> nonAcceptedTournaments = tournamentsService.getAccepted(loggedBussiness, pageable2);


        model.addAttribute("bussinessName", loggedBussiness.getBussinessName());
        model.addAttribute("adress", loggedBussiness.getAdress());
        model.addAttribute("scheduleHeader", loggedBussiness.getSchedule()[0]);
        model.addAttribute("scheduleMorning", loggedBussiness.getSchedule()[1]);
        model.addAttribute("scheduleAfternoon", loggedBussiness.getSchedule()[2]);
        model.addAttribute("acceptedTournaments",null);
        model.addAttribute("nonAcceptedTournaments",null);
        model.addAttribute("bussinessId", loggedBussiness.getId());
        model.addAttribute("createdTournaments", loggedBussiness.getCreatedTournaments());
        return "bussinessProfile";
    }

    @RequestMapping(value="/editProfile/{id}", method=RequestMethod.GET)
    public String editProfile(@PathVariable long id, String password, int division,Model model) {
        Player loggedUser = playerService.findById(id);
        if(division!=0){
            loggedUser.setDivision(division);
        }
        if(!password.isBlank()){
            loggedUser.setPassword(password);
        }
        playerService.updatePlayer(loggedUser);
        model.addAttribute("loggedUser", loggedUser);
        List<DoubleOfPlayers> userDoubles = doubleService.findDoublesOf(loggedUser.getUsername());
        model.addAttribute("userDoubles", userDoubles);
        model.addAttribute("userCreatedGames", loggedUser.getCreatedMatches());
        model.addAttribute("userPlayedGames", loggedUser.getPlayedMatches());
        model.addAttribute("userPendingGames", loggedUser.getPendingMatches());
        
        return "succesEdit";
    }

    @PostMapping(value="/update/{id}/image")
    public String updateImage(@PathVariable long id, @RequestParam MultipartFile profilePicture,Model model) throws IOException {
        Player loggedUser = playerService.findById(id);
        loggedUser.setImage(BlobProxy.generateProxy(
            profilePicture.getInputStream(), profilePicture.getSize()));
        loggedUser.setHasImage(true);
        playerService.updatePlayer(loggedUser);
        return "succesEdit";
    }

    @GetMapping(value="/user/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException{
        Player loggedUser = playerService.findById(id);
        if(loggedUser.getImage() != null){
            Resource file = new  InputStreamResource(loggedUser.getImage().getBinaryStream());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
            .contentLength(loggedUser.getImage().length()).body(file);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value="/selectMatchWinner/{id}/{index}/{doubleWinnerSlot}")
    public String selectMatchWinner(@PathVariable long id, @PathVariable int index, @PathVariable int doubleWinnerSlot, Model model) {
        Player loggedUser = playerService.findById(id);
        PadelMatch match = loggedUser.getCreatedMatches().get(index - 1);
        DoubleOfPlayers doubleWinner;
        DoubleOfPlayers doubleLoss;
        if(doubleWinnerSlot==1){
            doubleWinner = match.getDouble1();
            doubleLoss = match.getDouble2();
        }else{
            doubleWinner = match.getDouble2();
            doubleLoss = match.getDouble1();
        }
        match.setDoubleWinner(doubleWinner);
        match.setHasWinner(true);
        loggedUser.getCreatedMatches().remove(index - 1);
        
        Player winner1 = doubleWinner.getPlayer1();
        Player winner2 = doubleWinner.getPlayer2();
        Player loser1 = doubleLoss.getPlayer1();
        Player loser2 = doubleLoss.getPlayer2();

        matchesService.save(match);
        doubleService.saveDouble(doubleWinner);
        doubleService.saveDouble(doubleLoss);
        winner1.getPlayedMatches().add(match);
        winner2.getPlayedMatches().add(match);
        loser1.getPlayedMatches().add(match);
        loser2.getPlayedMatches().add(match);

        winner1.setScore(winner1.getScore()+3);
        winner2.setScore(winner2.getScore()+3);
        loser1.setScore(loser1.getScore()-3);
        loser2.setScore(loser2.getScore()-3);

        model.addAttribute("loggedUser", loggedUser);
              
        winner1.setMathcesWon(loggedUser.getMathcesWon()+1);
        winner2.setMathcesWon(loggedUser.getMathcesWon()+1);
        loser1.setMatchesLost(loggedUser.getMatchesLost()+1);
        loser2.setMatchesLost(loggedUser.getMatchesLost()+1);

        winner1.setMathesPlayed(loggedUser.getMathesPlayed()+1);
        winner2.setMathesPlayed(loggedUser.getMathesPlayed()+1);
        loser1.setMathesPlayed(loggedUser.getMathesPlayed()+1);
        loser2.setMathesPlayed(loggedUser.getMathesPlayed()+1);

        double efectivity;
        if(loggedUser.getMathesPlayed()==0)
            efectivity=0;
        else           
            efectivity =  (((double) loggedUser.getMathcesWon())/ ((double)loggedUser.getMathesPlayed()))*100;
            double efectivity2 = Math.floor(efectivity);
        model.addAttribute("efectivity", efectivity2);
           
        model.addAttribute("loggedUser.matchesWon", loggedUser.getMathcesWon());
        model.addAttribute("loggedUser.matchesPlayed", loggedUser.getMathesPlayed());
        model.addAttribute("loggedUser.matchesLost", loggedUser.getMatchesLost());       
        boolean hasplayedmatches = true;
        boolean notMyProfile = false;
        model.addAttribute("UserExtern", notMyProfile);
        model.addAttribute("hasplayedmatches", hasplayedmatches);
        List<DoubleOfPlayers> userDoubles = doubleService.findDoublesOf(loggedUser.getUsername());
        model.addAttribute("userDoubles", userDoubles);
        model.addAttribute("userCreatedGames", loggedUser.getCreatedMatches());
        model.addAttribute("userPlayedGames", loggedUser.getPlayedMatches());
        model.addAttribute("userPendingGames", loggedUser.getPendingMatches());
        playerService.updatePlayer(winner1);
        playerService.updatePlayer(winner2);
        playerService.updatePlayer(loser1);
        playerService.updatePlayer(loser2);
        return "userProfile";
    }

    
    @RequestMapping(value="/editBussinessProfile/{id}", method=RequestMethod.GET)
    public String editBussinessProfile(@PathVariable long id, String password, int division,Model model, @RequestParam String s1_0, @RequestParam String s1_1, @RequestParam String s1_2, @RequestParam String s1_3, @RequestParam String s1_4, @RequestParam String s1_5, @RequestParam String s1_6,@RequestParam String s2_0,@RequestParam String s2_1,@RequestParam String s2_2,@RequestParam String s2_3,@RequestParam String s2_4, @RequestParam String s2_5, @RequestParam String s2_6) {
        Bussiness loggedBussiness = bussinessService.findById(id);
        String[][] schedule = {{"L", "M", "X", "J", "V", "S", "D"},{s1_0, s1_1, s1_2, s1_3, s1_4, s1_5, s1_6},{s2_0, s2_1, s2_2, s2_3, s2_4, s2_5, s2_6}};
        loggedBussiness.setSchedule(schedule);
        if(!password.isBlank()){
            loggedBussiness.setPassword(password);
        }
        bussinessService.updateBussiness(loggedBussiness);
        model.addAttribute("loggedUser", loggedBussiness);
        
        return "succesEdit";
    }
    @PostMapping(value="/updateBussiness/{id}/image")
    public String updateBussinessImage(@PathVariable long id, @RequestParam MultipartFile profilePicture,Model model) throws IOException {
        Bussiness loggedBussiness = bussinessService.findById(id);
        Boolean notMyProfile = false;
        loggedBussiness.setImage(BlobProxy.generateProxy(
            profilePicture.getInputStream(), profilePicture.getSize()));
        loggedBussiness.setHasImage(true);
        bussinessService.updateBussiness(loggedBussiness);
        model.addAttribute("UserExtern", notMyProfile);
        return "succesEdit";
    }
    
    @DeleteMapping("/delete/tournament/{id}")
    public String deleteTournament(@PathVariable Long id){
        tournamentsService.deleteById(id);
        return "successTournamentDelete";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteAMatch(@PathVariable long id, Model model){
        matchesService.deleteMatch(id);
        return "successDelete";
    }

    
    }

    
    



