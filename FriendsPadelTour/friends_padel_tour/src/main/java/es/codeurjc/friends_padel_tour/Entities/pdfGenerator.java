package es.codeurjc.friends_padel_tour.Entities;


import com.itextpdf.text.*;

import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;


public class pdfGenerator {

    public ByteArrayOutputStream getPDF() {

        // Creamos la instancia de memoria en la que se escribirá el archivo temporalmente
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            Document document = new Document(PageSize.A4);            
            Font fuenteTitulo = new Font();
            fuenteTitulo.setSize(20);


            //Fonts
            Font TituloFont = new Font();
            TituloFont.setStyle(Font.BOLD);
            TituloFont.setColor(1, 0, 0);



           
            // Asignamos la variable ByteArrayOutputStream bos donde se escribirá el documento
            PdfWriter.getInstance(document, bos);
            document.open();
            //document.add(tabla);
            Paragraph Titulo = new Paragraph("Enhorabuena, has sido galardonado con este diploma");
            Chunk uno = new Chunk("Esto es una prueba");
            Titulo.setFont(TituloFont);
            document.add(Titulo);
            document.add(uno);

            document.close();
            // Retornamos la variable al finalizar
            return bos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

