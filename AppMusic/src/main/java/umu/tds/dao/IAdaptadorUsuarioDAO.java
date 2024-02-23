package umu.tds.dao;

import java.util.List;

import umu.tds.dominio.Usuario;

public interface IAdaptadorUsuarioDAO {
	
	public void registrarUsuario(Usuario asistente);
	public boolean borrarUsuario(Usuario asistente);
	public void modificarUsuario(Usuario asistente);
	public Usuario recuperarUsuario(int id);
	public List<Usuario> recuperarTodosUsuarios();
	
}
