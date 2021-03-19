package es.codeurjc.friends_padel_tour.Controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;





@Controller
public class PrincipalController {

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
    
    @GetMapping(value="/AboutUs")
    public String getMethodName4(Model model) {
        return "AboutUs";
    }
    
    @GetMapping(value="/404")
    public String error(Model model) {
        return "404";
    }
    
}
