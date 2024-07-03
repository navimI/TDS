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
 * Clase Generado de PDF
 * Genera un pdf con la lista de canciones de un usuario.
 * <p>
 * El fichero sera generado en la ruta pasada por parametro a la función generarFichero()
 * 
 * @version 1.0
 * @author Ivan Garcia Alcaraz
 * @see Cancion
 * @see PlayList
 */
public class GeneradorPDF {
	

    /**
     * Inicializa un generador de pdf.
     * @throws FileNotFoundException Excepcion lanzada si no se encuentra el fichero
     * @throws DocumentException Excepcion lanzada si no se puede crear el documento
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
     * @param usuario Nombre del usuario que genera el PDF
     * @param listaCanciones Lista de canciones a incluir en el fichero.
     * @throws FileNotFoundException Excepcion lanzada si no se encuentra el fichero
     * @throws DocumentException Excepcion lanzada si no se puede crear el documento
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

    /**
     * Genera la cabecera de la PlayList
     * @param nombrePlayList Nombre de la PlayList
     * @param numReproducciones Numero de reproducciones de la lista
     * @return Cadena con la cabecera
     */

    private String generaCabecera(String nombrePlayList, int numReproducciones) {
    	return "PlayList" + nombrePlayList + " con "+numReproducciones+" canciones";
    }
    
    /**
     * Imprime una PlayList en el documento
     * @param documento Documento en el que se imprime la lista
     * @param p PlayList a imprimir
     * @throws DocumentException Excepcion lanzada si no se puede añadir la PlayList al documento
     */
    private void imprimePlayList(Document documento, PlayList p) throws DocumentException {
        documento.add(new Paragraph(generaCabecera(p.getNombre(),p.getNumCanciones()), new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD)));
        documento.add(Chunk.NEWLINE);
        documento.add(tablaCanciones(p.getPlayList()));
        documento.add(Chunk.NEWLINE);
    }

    /**
     * Genera una tabla con las canciones de la PlayList
     * @param canciones Lista de canciones a incluir en la tabla
     * @return Tabla con las canciones
     */
	
    private PdfPTable tablaCanciones(List<Cancion> canciones){
        PdfPTable tabla = new PdfPTable(3);
        tabla.addCell(new PdfPCell(new Paragraph("Titulo", new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD))));
        tabla.addCell(new PdfPCell(new Paragraph("Estilo", new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD))));
        tabla.addCell(new PdfPCell(new Paragraph("Intérpretes", new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD))));
        tabla.setHeaderRows(1);
        for (Cancion cancion : canciones) {
            tabla.addCell(new PdfPCell(new Paragraph(cancion.getTitulo())));
            tabla.addCell(new PdfPCell(new Paragraph(cancion.getEstilo())));
            tabla.addCell(new PdfPCell(new Paragraph(cancion.getStringInterpretes())));
        }
        return tabla;
    }
}
