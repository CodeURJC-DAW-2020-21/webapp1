package es.codeurjc.friends_padel_tour.Controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import es.codeurjc.friends_padel_tour.Service.BussinessService;
import es.codeurjc.friends_padel_tour.Service.PlayersService;
import es.codeurjc.friends_padel_tour.Service.UserService;
import es.codeurjc.friends_padel_tour.Service.pdfGenerator;





@Controller
public class PrincipalController {

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
            if(request.isUserInRole("ADMIN")){
                model.addAttribute("admin", request.isUserInRole("ADMIN"));
                model.addAttribute("userId", userService.findByUsername(principal.getName()).getId());
            }
		} else {
			model.addAttribute("logged", false);
		}
	}

	@GetMapping("/")
	public String Index() {
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
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'a las' HH:mm:ss z"); 
            Date date = new Date(System.currentTimeMillis());
            String date2 = formatter.format(date);
            String mystring2 = "Padel Arroyomolinos";
            pdfGenerator generator = new pdfGenerator();
            generator.setDate(date2);
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
