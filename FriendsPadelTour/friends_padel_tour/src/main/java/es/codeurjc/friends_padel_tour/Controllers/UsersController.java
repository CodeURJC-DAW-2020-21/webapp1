package es.codeurjc.friends_padel_tour.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.codeurjc.friends_padel_tour.Entities.Bussiness;
import es.codeurjc.friends_padel_tour.Entities.Player;
import es.codeurjc.friends_padel_tour.Service.BussinessService;
import es.codeurjc.friends_padel_tour.Service.PlayersService;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class UsersController {

    @Autowired
    private PlayersService playerService;
    @Autowired
    private BussinessService bussinessService;

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
            //Comprobar si es una empresa
            //Mirar como pasar mensaje de que el correo no existe
            return "redirect:/login";
        }
        if(!loggedPlayer.getPassword().equals(password))
            //Pasar mensaje de contraseña incorrecta
            return "redirect:/login";
        model.addAttribute("loggedUser", loggedPlayer);
        return "userProfile";
    }
    


    
    
}
