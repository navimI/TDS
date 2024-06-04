package umu.tds.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import umu.tds.dominio.Cancion;



public class CancionCargadorAdapter extends Cancion{

    private tds.CargadorCanciones.Cancion cancion;
    
    public CancionCargadorAdapter(tds.CargadorCanciones.Cancion cancion) {
    	this.cancion = cancion;
    }
    
    
    @Override
    public String getTitulo() {
        return cancion.getTitulo();
    }


    @Override
    public Boolean esFavorita() {
    	return false;
    }
    
    @Override
    public int getNumReproducciones() {
    	return 0;
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
    
}
