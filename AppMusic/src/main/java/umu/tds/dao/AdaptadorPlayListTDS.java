package umu.tds.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.PlayList;

public class AdaptadorPlayListTDS implements IAdaptadorPlayListDAO{
	// Declaración de constantes
	
	private static final String PLAYLIST = "PlayList";
	private static final String NOMBRE = "nombre";
	private static final String LISTACANCIONES = "listaCanciones";
	
	// Declaración de servicio de persistencia
	private ServicioPersistencia servPersistencia;
	
	private static AdaptadorPlayListTDS unicaInstancia;

	
	public AdaptadorPlayListTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	public static AdaptadorPlayListTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			return new AdaptadorPlayListTDS();
		else
			return unicaInstancia;
	}
	
	// Metodos para transformar entre entidad y la clase PlayList
	
	private PlayList entidadToPlayList(Entidad ePlayList) {
		// Recuperación de propiedades que no son objetos
		String nombre = servPersistencia.recuperarPropiedadEntidad(ePlayList, NOMBRE);
		List<Cancion> canciones = new LinkedList<Cancion>();
		
		PlayList listaCanciones = new PlayList(nombre);
		listaCanciones.setId(ePlayList.getId());
		
		// Recupera propiedades que son objetos llamando a adaptadores de Cancion
		
		canciones = obtenerCancionDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(ePlayList, LISTACANCIONES));
		listaCanciones.addCanciones(canciones);
		
		return listaCanciones;
	}
	
	private Entidad playListToEntidad(PlayList playList) {
		Entidad ePlayList = null;
		
		// Registro de los atributos que son objetos
		AdaptadorCancionTDS adaptadorCancion = AdaptadorCancionTDS.getUnicaInstancia();
		for (Cancion c : playList.getPlayList())
			adaptadorCancion.registrarCancion(c);
		
		// Crear entidad PlayList
		ePlayList = new Entidad();
		ePlayList.setNombre(PLAYLIST);
		ePlayList.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad(NOMBRE, playList.getNombre()),
						new Propiedad(LISTACANCIONES, obtenerCodigosCancion(playList.getPlayList())))));
		return ePlayList;
	}
	
	public void registrarPlayList(PlayList playList) {
		Entidad ePlayList = null;
		
		// Si la entidad esta registrada no la registra de nuevo
				try {
					ePlayList = servPersistencia.recuperarEntidad(playList.getId());
				} catch (NullPointerException e) {}
				if (ePlayList != null) return;
				// creación y gestion de las entidades
				ePlayList = this.playListToEntidad(playList);
				// registrar entidad de Cancion
				ePlayList = servPersistencia.registrarEntidad(ePlayList);
				// asignar identificador unico
				// se usa el que genera el servicio de persistencia
				playList.setId(ePlayList.getId());
	}

	public boolean borrarPlayList(PlayList listaV) {
		Entidad ePlayList = servPersistencia.recuperarEntidad(listaV.getId());
		return servPersistencia.borrarEntidad(ePlayList);
	}
	
	public void modificarPlayList(PlayList listaV) {
		Entidad ePlayList = servPersistencia.recuperarEntidad(listaV.getId());
		
		for(Propiedad prop : ePlayList.getPropiedades()) {
			if(prop.getNombre().equals("codigo")) {
				prop.setValor(String.valueOf(listaV.getId()));
			}else if(prop.getNombre().equals(NOMBRE)) {
				prop.setValor(listaV.getNombre());
			}else if(prop.getNombre().equals(LISTACANCIONES)) {
				String canciones = obtenerCodigosCancion(listaV.getPlayList());
				prop.setValor(canciones);
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}
	
	public PlayList recuperarPlayList(int id) {
		Entidad ePlayList = servPersistencia.recuperarEntidad(id);
		
		return entidadToPlayList(ePlayList);
	}
	
	public List<PlayList> recuperarTodosPlayList() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(PLAYLIST);
		
		List<PlayList> playList = new LinkedList<PlayList>();
		for(Entidad ePlayList : entidades) {
			playList.add(recuperarPlayList(ePlayList.getId()));
		}
		
		return playList;
	}
	
	// -------------------Funciones auxiliares-----------------------------

	private String obtenerCodigosCancion(List<Cancion> playList) {
		String lineas = "";
		for (Cancion cancion : playList) {
			lineas += cancion.getID() + " ";
		}
		return lineas.trim();
	}
	
	private List<Cancion> obtenerCancionDesdeCodigos(String cancion){
		List<Cancion> playList = new LinkedList<Cancion>();
		StringTokenizer strTok = new StringTokenizer(cancion, " ");
		AdaptadorCancionTDS adaptadorC = AdaptadorCancionTDS.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			playList.add(adaptadorC.recuperarCancion(Integer.valueOf((String) strTok.nextElement())));
		}
		return playList;
	}

}
