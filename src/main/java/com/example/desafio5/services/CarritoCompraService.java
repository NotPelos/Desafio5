package com.example.desafio5.services;

import java.util.List;

import com.example.desafio5.model.Articulo;

public interface CarritoCompraService {
	
	void limpiarcesta();	
	void addArticulo(Articulo a);
	int getNumArticulo();
	List<Articulo> getArticulos();
	Double totalprice ();
	Double calculadorDescuento(double precio, double procentajeDescuento);
	Double aplicarDescuento(Integer idArticulo, Double porcentaje);
}
