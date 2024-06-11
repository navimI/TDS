package umu.tds.dominio;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public interface Descuento {
    
	double precioFinal();
	
	boolean isApplicable(Usuario usuario);
	
	String asString();
	
	static Set<Descuento> descuetos(){
		Set<Descuento> d = new HashSet<>();
		Collections.addAll(d, new DescuentoMayores(), new DescuentoDiaUno());
		return d;
	}
    
}
