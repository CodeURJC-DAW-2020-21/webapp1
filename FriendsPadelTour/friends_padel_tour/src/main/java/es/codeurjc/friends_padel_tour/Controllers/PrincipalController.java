package es.codeurjc.friends_padel_tour.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import es.codeurjc.friends_padel_tour.Entities.Bussiness;
import es.codeurjc.friends_padel_tour.Entities.Player;


@Controller
public class PrincipalController {
    private Bussiness loggedBussiness;
    private Player loggedPlayer;

    @GetMapping(value="/Index")
    public String getMethodName(Model model) {
        return  "Index";
    }

    @GetMapping(value="/team/{num}")
    public String getMethodName2(Model model, @PathVariable int num) {
        switch (num) {
            case 1: return "team";
            case 2: return "team2";
            case 3: return "team3";
            case 4: return "team4";
            case 5: return "team5";
            case 6: return "team6";
            default: 
        }
        return "team";
    }

    @GetMapping(value="/tournaments")
    public String getMethodName3(Model model) {
        return "tournaments";
    }
    @GetMapping(value="/aboutUs")
    public String getMethodName4(Model model) {
        return "AboutUs";
    }
    @GetMapping(value="/userProfile")
    public String getMethodName5(Model model) {
        Object loggedUser = model.getAttribute("loggedUser");
        if(loggedUser ==null){
            return "login";
        }
        if(loggedUser instanceof Player){
            loggedPlayer = (Player) loggedUser;
            return "userProfile";
        }else if(loggedUser instanceof Bussiness){
            loggedBussiness = (Bussiness) loggedUser;
            return "bussinessProfile";
        }
        return "userProfile";
    }
    @GetMapping(value="/login")
    public String getMethodName6(Model model) {
        return "login";
    }
    
    @GetMapping(value="/friendlyMatch")
    public String getMethodName7(Model model) {
        return "friendlyMatch";
    }

    @GetMapping(value="/userSignUp")
    public String goSignUp(Model model) {
        return "userSignUp";
    }

    @GetMapping(value="/goToSignUpPrev")
    public String goToSignUp(Model model) {
        return "previousSignUp";
    }

    @GetMapping(value="/playerSignUpForm")
    public String goToSignUpPlayerForm(Model model) {
        return "userSignUp";
    }

    @GetMapping(value="/bussinessSignUpForm")
    public String goToSignUpBussinessForm(Model model) {
        return "bussinessSignUp";
    }
    
    
    
    
}
