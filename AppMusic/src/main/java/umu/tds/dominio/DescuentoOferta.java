package umu.tds.dominio;

public class DescuentoOferta implements Descuento{
    @Override
    public double aplicarDescuento(int precio) {
        return precio / 1.2;
    }

}
