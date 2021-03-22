package es.codeurjc.friends_padel_tour.Controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import es.codeurjc.friends_padel_tour.Entities.Player;
import es.codeurjc.friends_padel_tour.Entities.pdfGenerator;
import es.codeurjc.friends_padel_tour.Service.BussinessService;
import es.codeurjc.friends_padel_tour.Service.PlayersService;
import es.codeurjc.friends_padel_tour.Service.UserService;





@Controller
public class PrincipalController {

    //Autowired section
    @Autowired
    private PlayersService playerService;
    @Autowired
    private BussinessService bussinessService;
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
			model.addAttribute("admin", request.isUserInRole("ADMIN"));
            model.addAttribute("userId", userService.findByUsername(principal.getName()).getId());
            

		} else {
			model.addAttribute("logged", false);
		}
	}

	@GetMapping("/")
	public String Index() {
        Player newPlayer1 = new Player("username","yo","apellido","email","contraseña","ciudad",1);
        Player newPlayer2 = new Player("username2","yo2","apellido2","email2","contraseña2","ciudad2",1);
        newPlayer2.setScore(500);
        newPlayer1.setScore(200);
        newPlayer1.setMathesPlayed(2);
        newPlayer1.setMathcesWon(1);
        newPlayer1.setMatchesLost(1);
        playerService.savePlayer(newPlayer1);
        playerService.savePlayer(newPlayer2);
        playerService.savePlayer(new Player("username3","yo3","apellido3","email3","contraseña3","ciudad3",1));
        playerService.savePlayer(new Player("username4","yo4","apellido4","email4","contraseña4","ciudad4",1));

		return "Index";
	}

    @GetMapping(value="/AboutUs")
    public String getMethodName4(Model model) {
        return "AboutUs";
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
