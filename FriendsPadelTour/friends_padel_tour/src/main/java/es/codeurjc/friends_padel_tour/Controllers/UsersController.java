package es.codeurjc.friends_padel_tour.Controllers;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;

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
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping(value="/bussinesSignUpForm")
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
        Player loggedPlayer = playerService.findById(id);
        model.addAttribute("loggedUser", loggedPlayer);
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
        playerService.savePlayer(loggedUser);
        model.addAttribute("loggedUser", loggedUser);
        return "userProfile";
    }

    @PostMapping(value="/update/{id}/image")
    public String updateImage(@PathVariable long id, MultipartFile profilePicture,Model model) throws IOException {
        Player loggedUser = playerService.findById(id);
        loggedUser.setImage(BlobProxy.generateProxy(
            profilePicture.getInputStream(), profilePicture.getSize()));
        loggedUser.setHasImage(true);
        playerService.savePlayer(loggedUser);
        model.addAttribute("loggedUser", loggedUser);
        return "userProfile";
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
    
    
    


}
