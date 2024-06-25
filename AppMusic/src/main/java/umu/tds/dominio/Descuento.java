package umu.tds.dominio;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Interfaz que define los descuentos que se pueden aplicar a los usuarios
 * de la aplicacion
 * Usa un patron estrategia para crear los descuentos
 * @version 1.0
 * @author Ivan Garcia Alcaraz
 * @see DescuentoMayores
 * @see DescuentoDiaUno
 */
public interface Descuento {
    
	/**
	 * Devuelve el precio final de la compra con el descuento aplicado
	 * @return Precio final de la compra
	 */
	double precioFinal();
	
	/**
	 * Comprueba si el descuento es aplicable al usuario
	 * @param usuario el usuario al que se le aplica el descuento
	 * @return true si es aplicable, false en caso contrario
	 */
	boolean isApplicable(Usuario usuario);
	
	/**
	 * Devuelve el descuento en formato String
	 * @return Descuento en formato String
	 */
	String asString();
	
	/**
	 * Devuelve el tipo de descuento
	 * @return Tipo de descuento
	 */
	static Set<Descuento> descuetos(){
		Set<Descuento> d = new HashSet<>();
		Collections.addAll(d, new DescuentoMayores(), new DescuentoDiaUno());
		return d;
	}
    
}
