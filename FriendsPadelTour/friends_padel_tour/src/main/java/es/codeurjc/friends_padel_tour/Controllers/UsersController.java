package es.codeurjc.friends_padel_tour.Controllers;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import es.codeurjc.friends_padel_tour.Service.BussinessService;
import es.codeurjc.friends_padel_tour.Service.DoubleService;
import es.codeurjc.friends_padel_tour.Service.MatchesService;
import es.codeurjc.friends_padel_tour.Service.PlayersService;
import es.codeurjc.friends_padel_tour.Service.TournamentsService;






@Controller
public class UsersController {

    @Autowired
    private PlayersService playerService;
    @Autowired
    private BussinessService bussinessService;
    @Autowired
    private DoubleService doubleService;
    @Autowired
    private MatchesService matchesService;

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

    @GetMapping(value="/bussinesSignUpForm")
    public String bussinessSignUp(Model model) {
        return "bussinessSignUp";
    }


    @PostMapping(value="/signUpPlayer")
    public String signUpUser(Player loggedPlayer,Model model) {
        if(!playerService.savePlayer(loggedPlayer))
            return "404";
        model.addAttribute("loggedUser", loggedPlayer);
        List<DoubleOfPlayers> userDoubles = doubleService.findDoublesOf(loggedPlayer.getUsername());
        model.addAttribute("userDoubles", userDoubles);
        model.addAttribute("userCreatedGames", loggedPlayer.getCreatedMatches());
        model.addAttribute("userPlayedGames", loggedPlayer.getPlayedMatches());
        model.addAttribute("userPendingGames", loggedPlayer.getPendingMatches());
        return "userProfile";
    }

    @RequestMapping(value="/loginUser", method=RequestMethod.POST)
    public String logInUser(String email, String password,Model model) {
        if(email.equals("admin")&&password.equals("pass"))//Comprobar si es el admin
            return "404";//Usuario administrador Logeado
        Player loggedPlayer= playerService.getPlayer(email);
        if(loggedPlayer == null){
            Bussiness loggedBussiness = bussinessService.getBussiness(email);
            if(loggedBussiness != null){   
            }
            return "login";
        }
        if(!loggedPlayer.getPassword().equals(password))
            //Pasar mensaje de contraseña incorrecta
            return "login";
        model.addAttribute("loggedUser", loggedPlayer);
        List<DoubleOfPlayers> userDoubles = doubleService.findDoublesOf(loggedPlayer.getUsername());
        model.addAttribute("userDoubles", userDoubles);
        model.addAttribute("userCreatedGames", loggedPlayer.getCreatedMatches());
        model.addAttribute("userPlayedGames", loggedPlayer.getPlayedMatches());
        model.addAttribute("userPendingGames", loggedPlayer.getPendingMatches());
        return "userProfile";
    }

        
    @GetMapping(value="/users/{id}")
    public String playerProfile(Model model,@PathVariable Long id) {
        Player loggedPlayer = playerService.findById(id);
        model.addAttribute("loggedUser", loggedPlayer);
        List<DoubleOfPlayers> userDoubles = doubleService.findDoublesOf(loggedPlayer.getUsername());
        model.addAttribute("userDoubles", userDoubles);
        model.addAttribute("loggedUser.matchesWon", loggedPlayer.getMathcesWon());
        model.addAttribute("loggedUser.matchesPlayed", loggedPlayer.getPlayedMatches());
        model.addAttribute("loggedUser.matchesLost", loggedPlayer.getMatchesLost());
        return "userProfile";
    }

    @GetMapping(value="/bussinessProfile/{{id}}")
    public String bussinessProfile(Model model,@PathVariable Long id) {
        Bussiness loggedBussiness = bussinessService.findById(id);
        TournamentsService tournamentsService = new TournamentsService();
        model.addAttribute("bussinessName", loggedBussiness.getBussinessName());
        model.addAttribute("adress", loggedBussiness.getAdress());
        model.addAttribute("scheduleHeader", loggedBussiness.getSchedule()[0]);
        model.addAttribute("scheduleMorning", loggedBussiness.getSchedule()[1]);
        model.addAttribute("scheduleAfternoon", loggedBussiness.getSchedule()[2]);
        model.addAttribute("acceptedTournaments",tournamentsService.getAccepted(loggedBussiness));
        model.addAttribute("nonAcceptedTournaments",tournamentsService.getNotAccepted(loggedBussiness));
        model.addAttribute("finished",tournamentsService.getNotAccepted(loggedBussiness));
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
        int playedmatches = loggedUser.getMathesPlayed();
        boolean hasplayedmatches = (playedmatches > 0);
        model.addAttribute("userDoubles", userDoubles);
        model.addAttribute("userCreatedGames", loggedUser.getCreatedMatches());
        model.addAttribute("userPlayedGames", loggedUser.getPlayedMatches());
        model.addAttribute("userPendingGames", loggedUser.getPendingMatches());
        model.addAttribute("hasplayedmatches", hasplayedmatches);
        return "succesEdit";
    }

    @PostMapping(value="/update/{id}/image")
    public String updateImage(@PathVariable long id, @RequestParam MultipartFile profilePicture,Model model) throws IOException {
        Player loggedUser = playerService.findById(id);
        Boolean notMyProfile = false;
        loggedUser.setImage(BlobProxy.generateProxy(
            profilePicture.getInputStream(), profilePicture.getSize()));
        loggedUser.setHasImage(true);
        playerService.updatePlayer(loggedUser);
        model.addAttribute("UserExtern", notMyProfile);
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

    @GetMapping(value="/selectMatchWinner/{id}/{index}/{doubleWiner}")
    public String selectMatchWinner(@PathVariable long id,@PathVariable int index,@PathVariable int doubleWinnerSlot,Model model) {
        Player loggedUser = playerService.findById(id);
        PadelMatch match = loggedUser.getCreatedMatches().get(index);
        DoubleOfPlayers doubleWinner;
        if(doubleWinnerSlot==1){
            doubleWinner = match.getDouble1();
        }else{
            doubleWinner = match.getDouble2();
        }
        match.setDoubleWinner(doubleWinner);
        match.setHasWinner(true);
        loggedUser.getCreatedMatches().remove(index);
        loggedUser.getPlayedMatches().add(match);

        Player winner1 = doubleWinner.getPlayer1();
        Player winner2 = doubleWinner.getPlayer2();

        winner1.setScore(winner1.getScore()+3);
        winner2.setScore(winner2.getScore()+3);

        doubleWinner.setPlayer1(winner1);
        doubleWinner.setPlayer2(winner2);

        matchesService.save(match);
        doubleService.saveDouble(doubleWinner);
        playerService.updatePlayer(winner1);
        playerService.updatePlayer(winner2);

        model.addAttribute("loggedUser", loggedUser);
        List<DoubleOfPlayers> userDoubles = doubleService.findDoublesOf(loggedUser.getUsername());
        model.addAttribute("userDoubles", userDoubles);
        model.addAttribute("userCreatedGames", loggedUser.getCreatedMatches());
        model.addAttribute("userPlayedGames", loggedUser.getPlayedMatches());
        model.addAttribute("userPendingGames", loggedUser.getPendingMatches());
        return "userProfile";
    }
    
    @RequestMapping(value="/editBussinessProfile/{id}", method=RequestMethod.GET)
    public String editBussinessProfile(@PathVariable long id, String password, int division,Model model, String s1_0, String s1_1, String s1_2, String s1_3, String s1_4, String s1_5, String s1_6,String s2_0, String s2_1, String s2_2, String s2_3, String s2_4, String s2_5, String s2_6) {
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
    
    }

    
    



