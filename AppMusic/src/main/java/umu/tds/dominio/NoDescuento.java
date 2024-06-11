package umu.tds.dominio;

public class NoDescuento implements Descuento {

	@Override
	public double precioFinal() {
		// TODO Auto-generated method stub
		return  Usuario.PRECIO_PREMIUM;
	}

	@Override
	public boolean isApplicable(Usuario usuario) {
		return true;
	}

	@Override
	public String asString() {
		return "Ningun descuento";
	}

}
