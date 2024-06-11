package umu.tds.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import umu.tds.dominio.Cancion;
import umu.tds.dominio.PlayList;

/**
 * <h1>Clase Generado de PDF</h1>
 * Genera un pdf con la lista de canciones de un usuario.
 * <p>
 * El fichero sera generado en la ruta pasada por parametro a la función generarFichero()
 */
public class GeneradorPDF {
	

    /**
     * Inicializa un generador de pdf.
     * @throws FileNotFoundException 
     * @throws DocumentException 
     */

     private Document inicializaDocumento(String rutaFichero) throws FileNotFoundException, DocumentException{
        FileOutputStream ficheroPDF = new FileOutputStream(rutaFichero);
        Document documento = new Document();
        PdfWriter.getInstance(documento, ficheroPDF);
        return documento;
        
     }

    /**
     * Genera un fichero pdf con la lista de canciones de un usuario.
     * @param rutaFichero Ruta donde se guardará el fichero.
     * @param listaCanciones Lista de canciones a incluir en el fichero.
     * @throws DocumentException 
     * @throws FileNotFoundException 
     */
    public void generarFichero(String rutaFichero, String usuario,List<PlayList> listaCanciones) throws FileNotFoundException, DocumentException {
        Document documento = inicializaDocumento(rutaFichero+"\\"+usuario+".pdf");
        documento.open();
        documento.add(new Paragraph("Lista de reproducciones del usuario " + usuario, new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD)));

        
        listaCanciones.stream().forEach(p -> {
            try {
                imprimePlayList(documento, p);
            } catch (DocumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        documento.close();
        
    }

    private void imprimePlayList(Document documento, PlayList p) throws DocumentException {
        documento.add(new Paragraph("PlayList" + p.getNombre()+ " con "+p.getNumCanciones(), new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD)));
        documento.add(Chunk.NEWLINE);
        documento.add(tablaCanciones(p.getPlayList()));
    }
	
    private PdfPTable tablaCanciones(List<Cancion> canciones){
        PdfPTable tabla = new PdfPTable(3);
        tabla.addCell(new PdfPCell(new Paragraph("Titulo")));
        tabla.addCell(new PdfPCell(new Paragraph("Estilo")));
        tabla.addCell(new PdfPCell(new Paragraph("Intérpretes")));
        tabla.setHeaderRows(1);
        for (Cancion cancion : canciones) {
            tabla.addCell(new PdfPCell(new Paragraph(cancion.getTitulo())));
            tabla.addCell(new PdfPCell(new Paragraph(cancion.getEstilo())));
            tabla.addCell(new PdfPCell(new Paragraph(cancion.getStringInterpretes())));
        }
        return tabla;
    }
}
