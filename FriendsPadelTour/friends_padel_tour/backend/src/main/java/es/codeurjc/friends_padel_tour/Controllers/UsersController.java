package es.codeurjc.friends_padel_tour.Controllers;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import es.codeurjc.friends_padel_tour.Entities.Tournament;
import es.codeurjc.friends_padel_tour.Entities.User;
import es.codeurjc.friends_padel_tour.Service.BussinessService;
import es.codeurjc.friends_padel_tour.Service.DoubleService;
import es.codeurjc.friends_padel_tour.Service.MatchesService;
import es.codeurjc.friends_padel_tour.Service.PlayersService;
import es.codeurjc.friends_padel_tour.Service.UserService;


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
    public String signUpBussiness(@RequestParam String password, @RequestParam String username,@RequestParam String ownerName,@RequestParam String ownerSurname
    ,@RequestParam String email,@RequestParam String bussinessName,@RequestParam String city,@RequestParam String adress,
    @RequestParam String location,@RequestParam String bussinessType,Model model){
        User user = new User(username,password,"BUSSINESS");
        userService.saveUser(user);
        Bussiness newBussiness = new Bussiness(bussinessName, ownerName, ownerSurname, email, city, adress, location, bussinessType, user);
        bussinessService.saveBussiness(newBussiness);
        return "successSignUp";
    }

    @PostMapping(value="/signUpPlayer")
    public String signUpUser(@RequestParam String password, @RequestParam String username,
     @RequestParam String name, @RequestParam String surname, @RequestParam String email,
      @RequestParam String location, @RequestParam int division) {
        User user = new User(username,password,"USER");
        userService.saveUser(user);
        Player newPlayer = new Player(username, name, surname, email, location, division, user);
        if(!playerService.savePlayer(newPlayer))
            return "404";
        return "successSignUp";
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
        List<Player> userDoubles = doubleService.findDoublesOf(loggedPlayer.getUsername());
        Player principalDouble = null;
        if(userDoubles != null && !userDoubles.isEmpty()){
            principalDouble = userDoubles.get(0);
        }
        model.addAttribute("principalDouble", principalDouble);
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
        model.addAttribute("userCreatedGames", loggedPlayer.getCreatedMatches());
        model.addAttribute("userPlayedGames", loggedPlayer.getPlayedMatches());
        model.addAttribute("userPendingGames", loggedPlayer.getPendingMatches());
        return "userProfile";
    }

    @GetMapping(value="/bussinessProfile/{username}")
    public String bussinessProfile(Model model,@PathVariable String username,@Qualifier("accepted")@PageableDefault(page=0, size=10) Pageable pageable,@Qualifier("nonAccepted")@PageableDefault(page=0, size=10)  Pageable pageable2) {
        
        Bussiness loggedBussiness = bussinessService.findByUsername(username);
        ArrayList<Tournament> acceptedTournaments = new ArrayList<>();
        ArrayList<Tournament> nonAcceptedTournaments = new ArrayList<>();

        for( Tournament t : loggedBussiness.getTournaments()){
            if(t.isAccepted()){ 
                acceptedTournaments.add(t);
            }
            else {
                nonAcceptedTournaments.add(t);
            }
        }


        model.addAttribute("bussinessName", loggedBussiness.getBussinessName());
        model.addAttribute("adress", loggedBussiness.getAdress());
        model.addAttribute("acceptedTournaments",acceptedTournaments);
        model.addAttribute("nonAcceptedTournaments",nonAcceptedTournaments);
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
            User user = loggedUser.getUser();
            user.setEncodedPassword(password);
            userService.updatePasswordOf(loggedUser.getUser(),password);
        }
        playerService.updatePlayer(loggedUser);
        model.addAttribute("loggedUser", loggedUser);
        List<Player> userDoubles = doubleService.findDoublesOf(loggedUser.getUsername());
        Player principalDouble = null;
        if(userDoubles != null && !userDoubles.isEmpty()){
            principalDouble = userDoubles.get(0);
        }
        model.addAttribute("principalDouble", principalDouble);
        model.addAttribute("userDoubles", userDoubles);
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
        matchesService.selectMatchWinner(match,doubleWinnerSlot,loggedUser);
        double efectivity;
        if(loggedUser.getMathesPlayed()==0)
            efectivity=0;
        else           
            efectivity =  (((double) loggedUser.getMathcesWon())/ ((double)loggedUser.getMathesPlayed()))*100;
            double efectivity2 = Math.floor(efectivity);       
        boolean hasplayedmatches = true;
        boolean notMyProfile = false;
        List<Player> userDoubles = doubleService.findDoublesOf(loggedUser.getUsername());
        Player principalDouble = null;
        if(userDoubles != null && !userDoubles.isEmpty()){
            principalDouble = userDoubles.get(0);
        }
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("principalDouble", principalDouble);
        model.addAttribute("userDoubles", userDoubles);
        model.addAttribute("userCreatedGames", loggedUser.getCreatedMatches());
        model.addAttribute("userPlayedGames", loggedUser.getPlayedMatches());
        model.addAttribute("userPendingGames", loggedUser.getPendingMatches());
        model.addAttribute("UserExtern", notMyProfile);
        model.addAttribute("hasplayedmatches", hasplayedmatches);
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("loggedUser.matchesWon", loggedUser.getMathcesWon());
        model.addAttribute("loggedUser.matchesPlayed", loggedUser.getMathesPlayed());
        model.addAttribute("loggedUser.matchesLost", loggedUser.getMatchesLost());
        model.addAttribute("efectivity", efectivity2);
        return "userProfile";
    }

    
    @RequestMapping(value="/editBussinessProfile/{id}", method=RequestMethod.GET)
    public String editBussinessProfile(@PathVariable long id, @RequestParam String password, Model model) {
        Bussiness loggedBussiness = bussinessService.findById(id);
        if(!password.isBlank()){
            User user = loggedBussiness.getUser();
            user.setEncodedPassword(password);
            userService.updatePasswordOf(loggedBussiness.getUser(), password);
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
    

    @GetMapping(value = "/delete/{id}")
    public String deleteAMatch(@PathVariable long id, Model model){
        matchesService.deleteMatch(id);
        return "successDelete";
    }

    @GetMapping(value="/makeDoubleWhith/{doubleName}")
    public String makeDoubleWith(@PathVariable String doubleName, Model model) {
        Player loggedUser = playerService.findByUsername((String) model.getAttribute("userName"));
        Player userNewDouble = playerService.findByUsername(doubleName);
        if(doubleService.findDouble(loggedUser.getUsername(), userNewDouble.getUsername())!=null){
            model.addAttribute("message", "Ya eres pareja de este usuario.");
            return "successDoubleCreation";
        }
        DoubleOfPlayers newDouble = new DoubleOfPlayers();
        
        newDouble.setPlayer1(loggedUser);
        newDouble.setPlayer2(userNewDouble);

        loggedUser.getDoubles1().add(newDouble);
        userNewDouble.getDoubles2().add(newDouble);

        playerService.updatePlayer(loggedUser);
        playerService.updatePlayer(userNewDouble);

        doubleService.saveDouble(newDouble);
        model.addAttribute("message", "Exito creando la nueva pareja.");
        return "successDoubleCreation";
    }
    

}


    
    



