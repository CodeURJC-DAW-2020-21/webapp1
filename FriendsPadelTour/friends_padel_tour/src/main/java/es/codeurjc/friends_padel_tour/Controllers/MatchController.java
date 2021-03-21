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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.friends_padel_tour.Entities.DoubleOfPlayers;
import es.codeurjc.friends_padel_tour.Entities.PadelMatch;
import es.codeurjc.friends_padel_tour.Entities.Player;
import es.codeurjc.friends_padel_tour.Service.DoubleService;
import es.codeurjc.friends_padel_tour.Service.MatchesService;
import es.codeurjc.friends_padel_tour.Service.PlayersService;


@Controller
public class MatchController {

    @Autowired
    private MatchesService matchesService;
    @Autowired
    private PlayersService playerService;
    @Autowired
    private DoubleService doubleService;
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

    @GetMapping(value="/team/{num}")
    public String friendlyMatchDivision(Model model, @PathVariable int num) {
         List<PadelMatch> matches = matchesService.findByDivision(num);
         List<Player> top10 = playerService.findTOP10(num);
         Player myplayer = playerService.findByUsername("username");
        model.addAttribute("matches", matches);
        switch (num) {
            case 1: 
            model.addAttribute("top10players", top10);
            model.addAttribute("player", myplayer);
            return "team";

            case 2:
            //model.addAttribute("top10players", top10);
             return "team2";
            case 3:
            //model.addAttribute("top10players", top10);
             return "team3";
            case 4: 
            //model.addAttribute("top10players", top10);
            return "team4";
            case 5: 
            //model.addAttribute("top10players", top10);
            return "team5";
            case 6: 
            //model.addAttribute("top10players", top10);
            return "team6";

            default: return "team";
        }
    }

    @GetMapping(value="/friendlyMatch")
    public String getMethodName7(Model model) {
        return "friendlyMatch";
    }

    @RequestMapping(value="/createFriendlyMatch/{num}", method=RequestMethod.GET)
    public String createFriendlyMatch(@PathVariable int num,@RequestParam String fmProvince,@RequestParam String fmcity,@RequestParam String fmfacility,@RequestParam String fmdate,@RequestParam String fmtime, Model model) {
        Player creator = playerService.findByUsername((String) model.getAttribute("userName"));
        PadelMatch newMatch = new PadelMatch(fmProvince,fmcity,fmfacility,fmdate,fmtime,num,creator);
        creator.getCreatedMatches().add(newMatch);
        matchesService.save(newMatch);
        playerService.updatePlayer(creator);
        return "succesMatchCreation";
    }

    @GetMapping(value="/joinFriendlyMatch/{id}")
    public String joinFrienlyMatch(@PathVariable long id,Model model) {
        PadelMatch matchToJoin = matchesService.findById(id);
        model.addAttribute("matchToJoin", matchToJoin);
        if(matchToJoin.getDouble1()==null){
            model.addAttribute("double1Joined", false);
            model.addAttribute("player1Joined", false);
            model.addAttribute("player2Joined", false);
        }else{
            model.addAttribute("double1Joined", true);
            if(matchToJoin.getDouble1().getPlayer1()==null && matchToJoin.getDouble1().getPlayer2()!=null){
                model.addAttribute("player1Joined", false);
                model.addAttribute("player2Joined", true);
                model.addAttribute("player2Name", matchToJoin.getDouble1().getPlayer2().getUsername());
            }
            if(matchToJoin.getDouble1().getPlayer1()!=null && matchToJoin.getDouble1().getPlayer2()==null){
                model.addAttribute("player2Joined", false);
                model.addAttribute("player1Joined", true);
                model.addAttribute("player1Name", matchToJoin.getDouble1().getPlayer1().getUsername());                
            }
            if(matchToJoin.getDouble1().getPlayer1()!=null && matchToJoin.getDouble1().getPlayer2()!=null){
                model.addAttribute("player2Joined", true);
                model.addAttribute("player1Joined", true);
                model.addAttribute("player1Name", matchToJoin.getDouble1().getPlayer1().getUsername());
                model.addAttribute("player2Name", matchToJoin.getDouble1().getPlayer2().getUsername());
            }
        }

        if(matchToJoin.getDouble2()==null){
            model.addAttribute("double2Joined", false);
            model.addAttribute("player3Joined", false);
            model.addAttribute("player4Joined", false);
        }else{
            model.addAttribute("double2Joined", true);
            if(matchToJoin.getDouble2().getPlayer1()==null && matchToJoin.getDouble2().getPlayer2()!=null){
                model.addAttribute("player3Joined", false);
                model.addAttribute("player4Joined", true);
                model.addAttribute("player4Name", matchToJoin.getDouble2().getPlayer2().getUsername());
            }
            if(matchToJoin.getDouble2().getPlayer1()!=null && matchToJoin.getDouble2().getPlayer2()==null){
                model.addAttribute("player4Joined", false);
                model.addAttribute("player3Joined", true);
                model.addAttribute("player3Name", matchToJoin.getDouble2().getPlayer1().getUsername());                
            }
            if(matchToJoin.getDouble2().getPlayer1()!=null && matchToJoin.getDouble2().getPlayer2()!=null){
                model.addAttribute("player4Joined", true);
                model.addAttribute("player3Joined", true);
                model.addAttribute("player3Name", matchToJoin.getDouble2().getPlayer1().getUsername());
                model.addAttribute("player4Name", matchToJoin.getDouble2().getPlayer2().getUsername());
            }
        }
        List<Player> userDoubles = playerService.findAll();
        model.addAttribute("userDoubles", userDoubles);
        return "joiningMatch";
    }

    @GetMapping(value="/joinLonely/{id}/{slot}")
    public String joinFriendlyMatchLonely(@PathVariable long id,@PathVariable int slot,Model model) {
        PadelMatch matchToJoin = matchesService.findById(id);
        Player loggedPlayer = playerService.findByUsername((String) model.getAttribute("userName"));
        if(slot==1||slot==2){
            if(matchToJoin.getDouble1()==null){
                DoubleOfPlayers newDoubleOfPlayers1 = new DoubleOfPlayers();
                doubleService.saveDouble(newDoubleOfPlayers1);
                matchToJoin.setDouble1(newDoubleOfPlayers1);
            }
        }
        if(slot==3||slot==4){
            if(matchToJoin.getDouble2()==null){
                DoubleOfPlayers newDoubleOfPlayers2 = new DoubleOfPlayers();
                doubleService.saveDouble(newDoubleOfPlayers2);
                matchToJoin.setDouble2(newDoubleOfPlayers2);
            }
        }
        if(slot==1){
            matchToJoin.getDouble1().setPlayer1(loggedPlayer);
            loggedPlayer.getDoubles1().add(matchToJoin.getDouble1());
            doubleService.saveDouble(matchToJoin.getDouble1());
        }
        if(slot==2){
            matchToJoin.getDouble1().setPlayer2(loggedPlayer);
            loggedPlayer.getDoubles2().add(matchToJoin.getDouble1());
            doubleService.saveDouble(matchToJoin.getDouble1());
        }
        if(slot==3){
            matchToJoin.getDouble2().setPlayer1(loggedPlayer);
            loggedPlayer.getDoubles1().add(matchToJoin.getDouble2());
            doubleService.saveDouble(matchToJoin.getDouble2());
        }
        if(slot==4){
            matchToJoin.getDouble2().setPlayer2(loggedPlayer);
            loggedPlayer.getDoubles2().add(matchToJoin.getDouble2());
            doubleService.saveDouble(matchToJoin.getDouble2());
        }
        loggedPlayer.getPendingMatches().add(matchToJoin);
        matchToJoin.setnPlayers(matchToJoin.getnPlayers()+1);

        playerService.updatePlayer(loggedPlayer);
        matchesService.save(matchToJoin);

        return "joiningSucces";
    }

    @GetMapping(value="/joinDouble/{id}/{slot}")
    public String joinDouble(@RequestParam String doubleSelect,@PathVariable long id,@PathVariable int slot,Model model) {
        PadelMatch matchToJoin = matchesService.findById(id);
        Player loggedPlayer = playerService.findByUsername((String) model.getAttribute("userName"));
        Player playerDouble = playerService.findByUsername(doubleSelect);
        DoubleOfPlayers newDouble = new DoubleOfPlayers();
        newDouble.setPlayer1(loggedPlayer);
        newDouble.setPlayer2(playerDouble);
        if(slot ==1){
            matchToJoin.setDouble1(newDouble);
        }else{
            matchToJoin.setDouble2(newDouble);
        }
        matchToJoin.setnPlayers(matchToJoin.getnPlayers()+2);
        
        playerDouble.getDoubles2().add(newDouble);
        loggedPlayer.getDoubles1().add(newDouble);

        doubleService.saveDouble(newDouble);

        playerService.updatePlayer(playerDouble);
        playerService.updatePlayer(loggedPlayer);

        
        
        matchesService.save(matchToJoin);
        return "joiningSucces";
    }
    

    
    
}
