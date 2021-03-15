package es.codeurjc.friends_padel_tour.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.codeurjc.friends_padel_tour.Entities.Bussiness;
import es.codeurjc.friends_padel_tour.Entities.Player;
import es.codeurjc.friends_padel_tour.Service.BussinessService;
import es.codeurjc.friends_padel_tour.Service.PlayersService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;




@Controller
public class UsersController {

    @Autowired
    private PlayersService playerService;
    @Autowired
    private BussinessService bussinessService;

    @RequestMapping(value="/signUpPlayer", method=RequestMethod.GET)
    public ModelAndView signUpUser(Player p,Model model) {
        if(!playerService.savePlayer(p))
            return new ModelAndView("redirect:/404",model.asMap());
        model.addAttribute("loggedUser", p);
        return new ModelAndView("userProfile",model.asMap());
    }

    @RequestMapping(value="/loginUser", method=RequestMethod.GET)
    public ModelAndView logInUser(String email, String password,Model model) {
        if(email.equals("administradorSistema")&&password.equals("password"))//Comprobar sie es el admin
            return new ModelAndView("redirect:/404", model.asMap());//Usuario administrador Logeado
        Player loggedPlayer= playerService.getPlayer(email);
        if(loggedPlayer == null){
            Bussiness loggedBussiness = bussinessService.getBussiness(email);
            if(loggedBussiness != null){
                
            }
            //Mirar como pasar mensaje de que el correo no existe
            return new ModelAndView("login", model.asMap());
        }
        if(!loggedPlayer.getPassword().equals(password))
            //Pasar mensaje de contraseña incorrecta
            return new ModelAndView("login", model.asMap());
        model.addAttribute("loggedUser", loggedPlayer);
        return new ModelAndView("userProfile", model.asMap());
    }      
    
}
