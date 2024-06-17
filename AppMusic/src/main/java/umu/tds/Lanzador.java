package umu.tds;

import java.awt.EventQueue;

import umu.tds.vista.login.VentanaLoginRegistro;

public class Lanzador {
	public static void main(final String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLoginRegistro ventana = new VentanaLoginRegistro();
					ventana.mostrarVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}