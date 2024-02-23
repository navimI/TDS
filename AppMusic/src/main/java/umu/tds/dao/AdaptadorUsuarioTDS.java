package umu.tds.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.dominio.PlayList;
import umu.tds.dominio.Usuario;
import beans.Entidad;
import beans.Propiedad;

/**
 * 
 * Clase que implementa el Adaptador DAO concreto de Usuario para el tipo H2.
 * 
 */
public final class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO {

	private static final String USUARIO = "Usuario";
	private static final String NOMBRE = "nombre";
	private static final String APELLIDOS = "apellidos";
	private static final String EMAIL = "email";
	private static final String USER = "user";
	private static final String PASSWORD = "password";
	private static final String FECHA_NACIMIENTO = "fechaNacimiento";
	private static final String PREMIUM = "premium";
	private static final String LISTAUSUARIO = "listaUsuario";


	private ServicioPersistencia servPersistencia;
	private SimpleDateFormat dateFormat;
	
	private static AdaptadorUsuarioTDS unicaInstancia;

	public AdaptadorUsuarioTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	public static AdaptadorUsuarioTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			return new AdaptadorUsuarioTDS();
		else
			return unicaInstancia;
	}

	private Usuario entidadToUsuario(Entidad eUsuario) {

		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE);
		String apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, APELLIDOS);
		String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, EMAIL);
		String user = servPersistencia.recuperarPropiedadEntidad(eUsuario, USER);
		String password = servPersistencia.recuperarPropiedadEntidad(eUsuario, PASSWORD);
		String fechaNacimiento = servPersistencia.recuperarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO);
		String premium = servPersistencia.recuperarPropiedadEntidad(eUsuario, PREMIUM);
		List<PlayList> playList = new LinkedList<PlayList>();
		
		Usuario usuario = new Usuario(nombre, apellidos, email, user, password, fechaNacimiento);
		usuario.setId(eUsuario.getId());
		usuario.setPremium(Boolean.valueOf(premium)==Boolean.TRUE);
		
		playList = obtenerListasUsuarioDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eUsuario, LISTAUSUARIO));
		usuario.addPlayListUsuarios(playList);

		return usuario;
	}

	private Entidad usuarioToEntidad(Usuario usuario) {
		Entidad eUsuario = null;
		
		// Registro de los atributos que son objetos
		AdaptadorPlayListTDS adaptadorPlayList = AdaptadorPlayListTDS.getUnicaInstancia();
		for (PlayList lv : usuario.getPlayListUsuario())
			adaptadorPlayList.registrarPlayList(lv);
	
		//Crear entidad Usuario

		eUsuario = new Entidad();
		eUsuario.setNombre(USUARIO);
		eUsuario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(NOMBRE, usuario.getNombre()),
				new Propiedad(APELLIDOS, usuario.getApellidos()), new Propiedad(EMAIL, usuario.getEmail()),
				new Propiedad(USER, usuario.getUser()), new Propiedad(PASSWORD, usuario.getPassword()),
				new Propiedad(FECHA_NACIMIENTO, usuario.getFechaNacim()),
				new Propiedad(PREMIUM, Boolean.valueOf(usuario.isPremiun()).toString()),
				new Propiedad(LISTAUSUARIO, obtenerCodigosListasUsuario(usuario.getPlayListUsuario()))
				)));
				
		return eUsuario;
	}

	public void registrarUsuario(Usuario usuario) {
		Entidad eUsuario = null;
		
		// Si la entidad esta registrada no la registra de nuevo
		try {
			eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		} catch (NullPointerException e) {}
		
		// creación y gestion de las entidades
		eUsuario = this.usuarioToEntidad(usuario);
		
		// registrar entidad de Cancion
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		
		// asignar identificador unico
		// se usa el que genera el servicio de persistencia
		usuario.setId(eUsuario.getId());
	}

	public boolean borrarUsuario(Usuario usuario) {
		Entidad eUsuario;
		AdaptadorPlayListTDS adaptadorLV = AdaptadorPlayListTDS.getUnicaInstancia();
		
		for(PlayList playList : usuario.getPlayListUsuario())
			adaptadorLV.borrarPlayList(playList);
		
		eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		return servPersistencia.borrarEntidad(eUsuario);
	}

	/**
	 * Permite que un Usuario modifique su perfil: password y email
	 */
	public void modificarUsuario(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());

		for (Propiedad prop : eUsuario.getPropiedades()) {
			if (prop.getNombre().equals(PASSWORD)) {
				prop.setValor(usuario.getPassword());
			} else if (prop.getNombre().equals(EMAIL)) {
				prop.setValor(usuario.getEmail());
			} else if (prop.getNombre().equals(NOMBRE)) {
				prop.setValor(usuario.getNombre());
			} else if (prop.getNombre().equals(APELLIDOS)) {
				prop.setValor(usuario.getApellidos());
			} else if (prop.getNombre().equals(USER)) {
				prop.setValor(usuario.getUser());
			} else if (prop.getNombre().equals(FECHA_NACIMIENTO)) {
				prop.setValor(dateFormat.format(usuario.getFechaNacim()));
			}else if (prop.getNombre().equals(PREMIUM)) {
				prop.setValor(Boolean.valueOf(usuario.isPremiun()).toString());
			}else if (prop.getNombre().equals(LISTAUSUARIO)) {
				String listas = obtenerCodigosListasUsuario(usuario.getPlayListUsuario());
				prop.setValor(listas);
			}
			servPersistencia.modificarPropiedad(prop);
		}

		servPersistencia.eliminarPropiedadEntidad(eUsuario, PASSWORD);
		servPersistencia.anadirPropiedadEntidad(eUsuario, PASSWORD, usuario.getPassword());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, EMAIL);
		servPersistencia.anadirPropiedadEntidad(eUsuario, EMAIL, usuario.getEmail());
	}

	public Usuario recuperarUsuario(int id) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(id);

		return entidadToUsuario(eUsuario);
	}
	
	public List<Usuario> recuperarTodosUsuarios() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(USUARIO);
		
		List<Usuario> usuarios = new LinkedList<Usuario>();
		for (Entidad eUsuario : entidades) {
			usuarios.add(recuperarUsuario(eUsuario.getId()));
		}
		
		return usuarios;
	}
	
	// -------------------Funciones auxiliares-----------------------------

	private String obtenerCodigosListasUsuario (List<PlayList> listasUsuario) {
		String lineas = "";
		for (PlayList playList: listasUsuario) {
			lineas += playList.getId() + " ";
		}
		return lineas.trim();
	}
	
	private List<PlayList> obtenerListasUsuarioDesdeCodigos (String lineas){
		List<PlayList> listasUsuarios = new LinkedList<PlayList>();
		StringTokenizer strTok = new StringTokenizer(lineas, " ");
		AdaptadorPlayListTDS adaptadorLV = AdaptadorPlayListTDS.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			listasUsuarios.add(adaptadorLV.recuperarPlayList(Integer.valueOf((String) strTok.nextElement())));
		}
		return listasUsuarios;
	}


}
