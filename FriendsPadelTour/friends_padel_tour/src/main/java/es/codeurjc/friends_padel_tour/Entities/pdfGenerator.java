package es.codeurjc.friends_padel_tour.Entities;


import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.util.Date;



public class pdfGenerator {

    private String date;
    private String nameTournament;
    
    public ByteArrayOutputStream getPDF() {
        

        // Creamos la instancia de memoria en la que se escribirá el archivo temporalmente
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            

            Document document = new Document(PageSize.A4);            
            Font fuenteTitulo = new Font();
            fuenteTitulo.setSize(20);


            //Fonts
            
            Font TituloFont = new Font(FontFamily.TIMES_ROMAN,30.0f,Font.BOLD,BaseColor.BLACK);
            Font TituloFont2 = new Font(FontFamily.TIMES_ROMAN,20.0f,Font.NORMAL,BaseColor.BLACK);
            Font TituloFont3 = new Font(FontFamily.TIMES_ROMAN,20.0f,Font.NORMAL,BaseColor.WHITE);
            Font HeaderFont = new Font(FontFamily.TIMES_ROMAN,10.0f,Font.UNDERLINE,BaseColor.BLUE);
            

            
           

            PdfWriter.getInstance(document, bos);
            document.open();


            Paragraph Titulo = new Paragraph("AYUDA PARA LA CREACIÓN DE AMISTOSOS "+ date, TituloFont);
            Paragraph HeaderX = new Paragraph("...........................................", TituloFont3);
            Paragraph Header = new Paragraph("1) Pincha en el lugar donde te permite rellenar un formulario y crea un partido amistoso ", TituloFont2);
            Paragraph Header2 = new Paragraph("2) Rellena todos los campos y pulsa el botón de crear", TituloFont2);
            Paragraph Header3 = new Paragraph("3) Una vez creado ya te puedes unir a dicho partido solo o con tu pareja, además de otros usuarios", TituloFont2);
            Paragraph Header4 = new Paragraph("...........................................", TituloFont3);
            Paragraph Header5 = new Paragraph("Recuerda que para crear un partido debes de estar registrado y estar logeado en la app", HeaderFont);

            document.add(Titulo);
            document.add(HeaderX);
            document.add(Header);
            document.add(Header2);
            document.add(Header3);
            document.add(Header4);
            document.add(Header5);

            document.close();
            return bos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String getNameTournament() {
        return nameTournament;
    }
    public void setNameTournament(String nameTournament) {
        this.nameTournament = nameTournament;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}

