package umu.tds.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.dominio.Cancion;

public class AdaptadorCancionTDS implements IAdaptadorCancionDAO {

	private static final String CANCION = "Cancion";
	private static final String TITULO = "titulo";
	private static final String NUMREPRODUCCIONES = "numReproducciones";
	private static final String ESTILO = "estilo";
	private static final String INTERPRETES = "listaInterpretes";

	private ServicioPersistencia servPersistencia;

	private static AdaptadorCancionTDS unicaInstancia;

	public AdaptadorCancionTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	public static AdaptadorCancionTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			return new AdaptadorCancionTDS();
		else
			return unicaInstancia;
	}

	/* metodo para transformar las entidades en cancion */
	private Cancion entidadToCancion(Entidad eCancion) {
		String titulo = servPersistencia.recuperarPropiedadEntidad(eCancion, TITULO);
		String numReproducciones = servPersistencia.recuperarPropiedadEntidad(eCancion, NUMREPRODUCCIONES);
		String estilo = servPersistencia.recuperarPropiedadEntidad(eCancion, ESTILO);

		// Recuperamos las listas de interppretes
		String interpretes = servPersistencia.recuperarPropiedadEntidad(eCancion, INTERPRETES);
		List<String> listaInterpretes = obtenerListaInterpretes(interpretes);

		// Creamos el objeto cancion
		
		Cancion cancion = new Cancion(titulo, estilo, listaInterpretes);
		cancion.setNumReproducciones(Integer.parseInt(numReproducciones));
		cancion.setID(eCancion.getId());

		return cancion;
	}

	/* cuando se registra un interprete se le asigna un identificador unico */
	private Entidad cancionToEntidad(Cancion cancion) {
		Entidad eCancion = null;


		// Crear entidad Cancion
		eCancion = new Entidad();
		eCancion.setNombre(CANCION);
		eCancion.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad(TITULO, cancion.getTitulo()), 
						new Propiedad(NUMREPRODUCCIONES, Integer.toString(cancion.getNumReproducciones())), 
						new Propiedad(ESTILO, cancion.getEstilo()),
						new Propiedad(INTERPRETES, obtenerStringInterpretes(cancion.getListaInterpretes())))));
		return eCancion;
	}

	public void registrarCancion(Cancion cancion) {

		Entidad eCancion = null;

		// registra entidad
		try {
			eCancion = servPersistencia.recuperarEntidad(cancion.getID());
		} catch (NullPointerException e) {
		}
		if (eCancion != null)
			return;
		// creación y gestion de las entidades
		eCancion = this.cancionToEntidad(cancion);
		// registrar entidad de Cancion
		eCancion = servPersistencia.registrarEntidad(eCancion);
		// asignar identificador unico
		// se usa el que genera el servicio de persistencia
		cancion.setID(eCancion.getId());
	}

	public boolean borrarCancion(Cancion cancion) {
		// recupera entidad
		Entidad eCancion = servPersistencia.recuperarEntidad(cancion.getID());
		// borra entidad
		return servPersistencia.borrarEntidad(eCancion);
	}

	public void modificarCancion(Cancion cancion) {
		// recupera entidad
		Entidad eCancion = servPersistencia.recuperarEntidad(cancion.getID());
		// modifica entidad
		for (Propiedad prop : eCancion.getPropiedades()) {
			if (prop.getNombre().equals("codigo")) {
				prop.setValor(String.valueOf(cancion.getID()));
			} else if (prop.getNombre().equals(TITULO)) {
				prop.setValor(cancion.getTitulo());
			} else if (prop.getNombre().equals(NUMREPRODUCCIONES)) {
				prop.setValor(String.valueOf(cancion.getNumReproducciones()));
			} else if (prop.getNombre().equals(INTERPRETES)) {
				String interpretes = obtenerStringInterpretes(cancion.getListaInterpretes());
				prop.setValor(interpretes);
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}

	public Cancion recuperarCancion(int id) {
		// recupera entidad
		Entidad eCancion = servPersistencia.recuperarEntidad(id);
		// devuelve cancion
		return entidadToCancion(eCancion);
	}

	public List<Cancion> recuperarTodasCanciones() {
		// recupera todas las entidades de tipo cancion
		List<Entidad> entidades = servPersistencia.recuperarEntidades(CANCION);
		// recupera todas las canciones del servicio de persistencia
		List<Cancion> canciones  = new LinkedList<Cancion>();
		for (Entidad eCancion : entidades) {
			canciones .add(recuperarCancion(eCancion.getId()));
		}

		return canciones ;
	}

	// -------------------Funciones auxiliares-----------------------------

	private List<String> obtenerListaInterpretes(String interpretes) {
		return Arrays.stream(interpretes.split("&"))
				.map(s -> s.replace("_", " "))
				.collect(Collectors.toList());
		
	}

	private String obtenerStringInterpretes(List<String> cancion) {
		String listaInterpretes = cancion.stream()
			.map(s -> s.replace(" ", "_"))
			.collect(Collectors.joining("&"));
        return listaInterpretes;
	}

}
