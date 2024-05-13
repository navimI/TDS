package umu.tds.vista;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import umu.tds.dominio.Cancion;

public class PruebaPanelBusqueda {

    public static void main(String[] args) {
        //objetos de prueba Cancion
        Cancion cancion1 = new Cancion("Cancion 1", "Rock", "Interprete 1", "Interprete 2");
        Cancion cancion2 = new Cancion("Cancion 2", "Pop", "Interprete 3");
        Cancion cancion3 = new Cancion("Cancion 3", "Electrónica", Arrays.asList("Interprete 4", "Interprete 5"));

        //Agregar los objetos de prueba a una lista simulando el catálogo de canciones
        List<Cancion> canciones = new ArrayList<>();
        canciones.add(cancion1);
        canciones.add(cancion2);
        canciones.add(cancion3);

        //Simular con objetos de prueba
        List<Cancion> resultados = buscarCanciones(canciones, "Interprete 1", "Cancion", "Rock", true);

        //Imprimir
        System.out.println("Resultados de la búsqueda:");
        for (Cancion cancion : resultados) {
            System.out.println(cancion);
        }
    }

    //simular la búsqueda de canciones...
    public static List<Cancion> buscarCanciones(List<Cancion> canciones, String interprete, String titulo, String estilo, boolean favoritas) {
        List<Cancion> cancionesEncontradas = new ArrayList<>();
        for (Cancion cancion : canciones) {
            if ((titulo.isEmpty() || cancion.getTitulo().equalsIgnoreCase(titulo)) &&
                    (estilo.isEmpty() || cancion.getEstilo().equalsIgnoreCase(estilo)) &&
                    (!favoritas || cancion.esFavorita())) {
                //Verificar si algún intérprete de la canción coincide con el intérprete buscado
                if (cancion.getListaInterpretes().contains(interprete)) {
                    cancionesEncontradas.add(cancion);
                }
            }
        }
        return cancionesEncontradas;
    }
}
