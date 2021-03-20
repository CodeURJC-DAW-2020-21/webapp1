package es.codeurjc.friends_padel_tour.Controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< HEAD
=======

>>>>>>> f17d2ad90e8818957c1f9757344f3e697c9fe68c
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

<<<<<<< HEAD
import es.codeurjc.friends_padel_tour.Entities.Player;
import es.codeurjc.friends_padel_tour.Entities.pdfGenerator;
import es.codeurjc.friends_padel_tour.Service.PlayersService;
=======
import es.codeurjc.friends_padel_tour.Entities.User;
import es.codeurjc.friends_padel_tour.Entities.pdfGenerator;
import es.codeurjc.friends_padel_tour.Repositories.UserRepository;
>>>>>>> f17d2ad90e8818957c1f9757344f3e697c9fe68c





@Controller
public class PrincipalController {

    @Autowired
    private PlayersService playerService;

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
    @Autowired
    private UserRepository userRepository;

	@GetMapping("/")
	public String Index() {

        playerService.savePlayer(new Player("username","yo","apellido","email","contraseña","ciudad",1));
        playerService.savePlayer(new Player("username2","yo2","apellido2","email2","contraseña2","ciudad2",1));
        playerService.savePlayer(new Player("username3","yo3","apellido3","email3","contraseña3","ciudad3",1));
        playerService.savePlayer(new Player("username4","yo4","apellido4","email4","contraseña4","ciudad4",1));

		return "Index";
	}

    @GetMapping(value="/AboutUs")
    public String getMethodName4(Model model) {
        return "AboutUs";
    }

	@GetMapping(value="/previousSignUp")
    public String getMethodpreviousSignUp(Model model) {
        return "previousSignUp";
    }

	@GetMapping(value="/userProfile")
    public String userProfilePage(Model model, HttpServletRequest request) {
        String name = request.getUserPrincipal().getName();

        User user = userRepository.findByName(name).orElseThrow();

        model.addAttribute("username", user.getName());
        model.addAttribute("user", request.isUserInRole("user"));
        return "userProfile";
    }

    @GetMapping(value="/bussinessProfile")
    public String bussinessProfilePage(Model model, HttpServletRequest request) {
        String name = request.getUserPrincipal().getName();

        User user = userRepository.findByName(name).orElseThrow();

        model.addAttribute("username", user.getName());
        model.addAttribute("bussiness", request.isUserInRole("bussiness"));
        return "bussinessProfile";
    }

	@GetMapping(value="/userSignUp")
    public String getMethoduserSignUp(Model model) {
        return "userSignUp";
    }

	@GetMapping(value="/bussinessSignUp")
    public String getMethodbussinessSignUp(Model model) {
        return "bussinessSignUp";
    }

	@GetMapping(value="/404")
    public String getMethod404(Model model) {
        return "404";
    }
	
    @GetMapping("/download-pdf")
        public void downloadFile(HttpServletResponse response) throws IOException {
            String mystring = "Juan";
            String mystring2 = "Padel Arroyomolinos";
            pdfGenerator generator = new pdfGenerator();
            generator.setNameWinner(mystring);
            generator.setNameTournament(mystring2);
            byte[] pdfReport = generator.getPDF().toByteArray();
    
            String mimeType =  "application/pdf";
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", "reporte.pdf"));
    
            response.setContentLength(pdfReport.length);
    
            ByteArrayInputStream inStream = new ByteArrayInputStream( pdfReport);
    
            FileCopyUtils.copy(inStream, response.getOutputStream());
        }


}
