package umu.tds.dao;

import java.util.List;

import umu.tds.dominio.Cancion;

public interface IAdaptadorCancionDAO {
	public void registrarCancion(Cancion asistente);
	public boolean borrarCancion(Cancion asistente);
	public void modificarCancion(Cancion asistente);
	public Cancion recuperarCancion(int id);
	public List<Cancion> recuperarTodasCanciones();
	
}
