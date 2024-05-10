package umu.tds.dao;

import java.util.List;

import umu.tds.dominio.PlayList;

public interface IAdaptadorPlayListDAO {
	public void registrarPlayList(PlayList listaV);
	public boolean borrarPlayList(PlayList listaV);
	public void modificarPlayList(PlayList listaV);
	public PlayList recuperarPlayList(int id);
	public List<PlayList> recuperarTodosPlayList();
	public PlayList buscarPlayListPorNombre(PlayList playlistSeleccionada);
}
