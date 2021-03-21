package es.codeurjc.friends_padel_tour.Controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import es.codeurjc.friends_padel_tour.Entities.pdfGenerator;





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
            pdfGenerator generator = new pdfGenerator();
            byte[] pdfReport = generator.getPDF().toByteArray();
    
            String mimeType =  "application/pdf";
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", "reporte.pdf"));
    
            response.setContentLength(pdfReport.length);
    
            ByteArrayInputStream inStream = new ByteArrayInputStream( pdfReport);
    
            FileCopyUtils.copy(inStream, response.getOutputStream());
        }
}
