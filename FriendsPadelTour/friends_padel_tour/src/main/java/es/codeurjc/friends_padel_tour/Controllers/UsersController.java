package es.codeurjc.friends_padel_tour.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.codeurjc.friends_padel_tour.Entities.Bussiness;
import es.codeurjc.friends_padel_tour.Entities.Player;
import es.codeurjc.friends_padel_tour.Service.BussinessService;
import es.codeurjc.friends_padel_tour.Service.PlayersService;




@Controller
public class UsersController {

    @Autowired
    private PlayersService playerService;
    @Autowired
    private BussinessService bussinessService;

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
    public String signUpUser(Player p,Model model) {
        if(!playerService.savePlayer(p))
            return "404";
        model.addAttribute("loggedUser", p);
        return "userProfile";
    }

    @RequestMapping(value="/loginUser", method=RequestMethod.GET)
    public String logInUser(String email, String password,Model model) {
        if(email.equals("administradorSistema")&&password.equals("password"))//Comprobar sie es el admin
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
        return "userProfile";
    }

    @GetMapping(value="/users/{id}")
    public String playerProfile(Model model,@PathVariable long id) {
        Object loggedUser = model.getAttribute("loggedUser");
        if(loggedUser == null){
            return "login";
        }
        if(loggedUser instanceof Player){
            return "userProfile";
        }else if(loggedUser instanceof Bussiness){
            return "bussinessProfile";
        }
        return "userProfile";
    }


}
