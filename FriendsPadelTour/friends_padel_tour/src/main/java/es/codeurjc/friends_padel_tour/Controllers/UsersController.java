package es.codeurjc.friends_padel_tour.Controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
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
import es.codeurjc.friends_padel_tour.Entities.pdfGenerator;
import es.codeurjc.friends_padel_tour.Service.BussinessService;
import es.codeurjc.friends_padel_tour.Service.DoubleService;
import es.codeurjc.friends_padel_tour.Service.MatchesService;
import es.codeurjc.friends_padel_tour.Service.PlayersService;






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

    @GetMapping(value="/bussinessSignUpForm")
    public String bussinessSignUp(Model model) {
        return "bussinessSignUp";
    }

    @RequestMapping(value="/signUpPlayer", method=RequestMethod.GET)
    public String signUpUser(Player loggedPlayer,Model model) {
        if(!playerService.savePlayer(loggedPlayer))
            return "404";
        model.addAttribute("loggedUser", loggedPlayer);
        List<DoubleOfPlayers> userDoubles = doubleService.findDoublesOf(loggedPlayer.getUserName());
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
            //Mirar como pasar mensaje de que el correo no existe
            return "login";
        }
        if(!loggedPlayer.getPassword().equals(password))
            //Pasar mensaje de contraseña incorrecta
            return "login";
        model.addAttribute("loggedUser", loggedPlayer);
        List<DoubleOfPlayers> userDoubles = doubleService.findDoublesOf(loggedPlayer.getUserName());
        model.addAttribute("userDoubles", userDoubles);
        model.addAttribute("userCreatedGames", loggedPlayer.getCreatedMatches());
        model.addAttribute("userPlayedGames", loggedPlayer.getPlayedMatches());
        model.addAttribute("userPendingGames", loggedPlayer.getPendingMatches());
        return "userProfile";
    }

    /*@GetMapping(value="/users/{id}")
    public String playerProfile(Model model,@PathVariable long id) {
        Player loggedPlayer = playerService.findById(id);
        model.addAttribute("loggedUser", loggedPlayer);
        List<DoubleOfPlayers> userDoubles = doubleService.findDoublesOf(loggedPlayer.getUserName());
        model.addAttribute("userDoubles", userDoubles);
        model.addAttribute("userCreatedGames", loggedPlayer.getCreatedMatches());
        model.addAttribute("userPlayedGames", loggedPlayer.getPlayedMatches());
        model.addAttribute("userPendingGames", loggedPlayer.getPendingMatches());
        return "userProfile";
    }*/
    
    @GetMapping(value="/users/{id}")
    public String playerProfile2(Model model,@PathVariable String id) {
        Player loggedPlayer = playerService.findByUsername(id);
        model.addAttribute("loggedUser", loggedPlayer);
        List<DoubleOfPlayers> userDoubles = doubleService.findDoublesOf(loggedPlayer.getUserName());
        model.addAttribute("userDoubles", userDoubles);
        model.addAttribute("loggedUser.matchesWon", loggedPlayer.getMathcesWon());
        model.addAttribute("loggedUser.matchesPlayed", loggedPlayer.getPlayedMatches());
        model.addAttribute("loggedUser.matchesLost", loggedPlayer.getMatchesLost());
        return "userProfile";
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
        List<DoubleOfPlayers> userDoubles = doubleService.findDoublesOf(loggedUser.getUserName());
        int playedmatches = loggedUser.getMathesPlayed();
        boolean hasplayedmatches = (playedmatches > 0):
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
        model.addAttribute("loggedUser", loggedUser);
        List<DoubleOfPlayers> userDoubles = doubleService.findDoublesOf(loggedUser.getUserName());
        model.addAttribute("userDoubles", userDoubles);
        model.addAttribute("userCreatedGames", loggedUser.getCreatedMatches());
        model.addAttribute("userPlayedGames", loggedUser.getPlayedMatches());
        model.addAttribute("userPendingGames", loggedUser.getPendingMatches());
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
        List<DoubleOfPlayers> userDoubles = doubleService.findDoublesOf(loggedUser.getUserName());
        model.addAttribute("userDoubles", userDoubles);
        model.addAttribute("userCreatedGames", loggedUser.getCreatedMatches());
        model.addAttribute("userPlayedGames", loggedUser.getPlayedMatches());
        model.addAttribute("userPendingGames", loggedUser.getPendingMatches());
        return "userProfile";
    }
    
    
    }

    
    



