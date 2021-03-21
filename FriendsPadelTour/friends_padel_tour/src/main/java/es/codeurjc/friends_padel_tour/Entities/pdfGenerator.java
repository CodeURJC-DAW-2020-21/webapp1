package es.codeurjc.friends_padel_tour.Entities;


import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;


public class pdfGenerator {

    private String nameWinner;
    private String nameTournament;
    
    public ByteArrayOutputStream getPDF() {
        

        // Creamos la instancia de memoria en la que se escribirá el archivo temporalmente
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            

            Document document = new Document(PageSize.A4);            
            Font fuenteTitulo = new Font();
            fuenteTitulo.setSize(20);


            //Fonts
            
            Font TituloFont = new Font(FontFamily.TIMES_ROMAN,30.0f,Font.BOLD,BaseColor.BLACK);
            Font HeaderFont = new Font(FontFamily.TIMES_ROMAN,10.0f,Font.UNDERLINE,BaseColor.BLUE);
            

            
           

            PdfWriter.getInstance(document, bos);
            document.open();


            Paragraph Titulo = new Paragraph("Enhorabuena, has sido galardonado con este diploma, " + nameWinner + " gracias a tu participación en el torneo disputado en las instalaciones '" + nameTournament +"'.", TituloFont);
            Paragraph Header = new Paragraph("Documento expedido por la pagina en convenio con las instalaciones organizadoras del torneo", HeaderFont);

            document.add(Header);
            document.add(Titulo);

            document.close();
            return bos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getNameWinner() {
        return nameWinner;
    }
    public void setNameWinner(String nameWinner) {
        this.nameWinner = nameWinner;
    }
    public String getNameTournament() {
        return nameTournament;
    }
    public void setNameTournament(String nameTournament) {
        this.nameTournament = nameTournament;
    }
}

