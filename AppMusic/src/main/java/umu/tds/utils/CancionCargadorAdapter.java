package umu.tds.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import umu.tds.dominio.Cancion;



public class CancionCargadorAdapter extends Cancion{

    private tds.CargadorCanciones.Cancion cancion;
    
    
    
    public CancionCargadorAdapter(tds.CargadorCanciones.Cancion cancion) {
    	super();
    	this.cancion = cancion;
    	
    }
    
    
    @Override
    public String getTitulo() {
        return cancion.getTitulo();
    }
    
    @Override
    public List<String> getListaInterpretes(){
        return Arrays.asList(cancion.getInterprete().split(","));
    }
    
    @Override
    public String getEstilo() {
    	return cancion.getEstilo();
    }
    
    @Override
    public String getRutaFichero() {
        String url = getEstilo() + "/";
		url += getListaInterpretes().stream().collect(Collectors.joining("&"));
		url += "-" + getTitulo() + ".mp3";
        return url;
    }
    
    public boolean descargarCancion(){
        try {
        URL uri = new URL(cancion.getURL());
        String outputPath = "src/main/resources/canciones";
        if (!Files.exists(Paths.get(outputPath, getEstilo()))) {
            try {
                Files.createDirectories(Paths.get(outputPath, getEstilo()));
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        Path outputFilePath = Paths.get(outputPath, getRutaFichero());

        if (Files.exists(outputFilePath)) {
            
            return true;
        }

        try (InputStream stream = uri.openStream()) {
            Files.copy(stream, outputFilePath, StandardCopyOption.REPLACE_EXISTING);
        }

        
        return true;
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
    }
    
    
}
