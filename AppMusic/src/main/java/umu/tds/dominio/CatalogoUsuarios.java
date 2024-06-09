package umu.tds.dominio;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;
import umu.tds.dao.IAdaptadorUsuarioDAO;

public class CatalogoUsuarios {
	private static CatalogoUsuarios unicaInstancia;
	private FactoriaDAO factoria;
	private IAdaptadorUsuarioDAO adaptadorUsuario;

	private HashMap<Integer, Usuario> userID;

	public static CatalogoUsuarios getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new CatalogoUsuarios();
		return unicaInstancia;
	}

	private CatalogoUsuarios (){
		
		try {
			factoria = FactoriaDAO.getInstancia();
			adaptadorUsuario = factoria.getUsuarioDAO();
			userID = new HashMap<Integer, Usuario>();
			this.cargarCatalogo();
			
		} catch (DAOException eDAO) {
			   eDAO.printStackTrace();
		}
	}
	
	public List<Usuario> getUsuarios() throws DAOException {
		return new LinkedList<Usuario>(userID.values());
	}

	public Usuario getUsuario(int id) {
		return userID.get(id);
	}

	public Usuario getUsuario(String user) {
		Usuario foundUsuario = userID.values().stream()
			.filter(usuario -> usuario.getUser().equals(user))
			.findFirst()
			.orElse(null);
		return foundUsuario;

	}

	public boolean login(String user, String password) {
		Usuario usuario = getUsuario(user);
		if (usuario != null) {
			return usuario.isPassword(password);
		}
		return false;
	}
		
	
	public void addUsuario(Usuario usuario) {
		userID.put(usuario.getId(), usuario);
	}
	
	public void removeUsuario(Usuario usuario) {
		userID.remove(usuario.getId());
	}

	public void updateUsuario(Usuario usuario) {
		addUsuario(usuario);
	}
	
	private void cargarCatalogo() throws DAOException{
		List<Usuario> listaAsistentes = adaptadorUsuario.recuperarTodosUsuarios();
		for (Usuario usuario : listaAsistentes) {
			userID.put(usuario.getId(), usuario);
		}
	}
}
