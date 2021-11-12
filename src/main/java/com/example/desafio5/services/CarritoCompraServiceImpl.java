package com.example.desafio5.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.desafio5.ddbb.BaseDatosServiceImpl;
import com.example.desafio5.model.Articulo;


public class CarritoCompraServiceImpl implements CarritoCompraService{

	@Autowired
	private BaseDatosServiceImpl baseDatos;
	
	List<Articulo> listaArticulos = new ArrayList<>();
	
	@Override
	public void limpiarcesta() {
		listaArticulos.clear();
	}

	@Override
	public void addArticulo(Articulo a) {
		// TODO Auto-generated method stub
		listaArticulos.add(a);
	}

	@Override
	public int getNumArticulo() {
		// TODO Auto-generated method stub
		return listaArticulos.size();
	}
	
	@Override
	public Double calculadorDescuento(double precio, double procentajeDescuento) {
		// TODO Auto-generated method stub
		return precio - precio*procentajeDescuento/100;
	}
	
	@Override
	public Double totalprice() {
		double total = 00;
		for (Articulo articulo:listaArticulos) {
			total = total+articulo.getPrecio();
		}
		return total;
	}
	
	@Override
	public Double aplicarDescuento (Integer idArticulo, Double porcentaje){
		Double resultado = null;
		Articulo articulo = baseDatos.findArticuloById(idArticulo);
		
		if (articulo !=null) {
			resultado = calculadorDescuento(articulo.getPrecio(), porcentaje);
		}else {
			System.out.println("No se ha encontrado ningun articulo con id:" + idArticulo);
		}
		return resultado;
	}

	@Override
	public List<Articulo> getArticulos() {
		// TODO Auto-generated method stub
		return listaArticulos;
	}
	
	
	public Integer insertarEnDbYLista(Integer id, Articulo a) {
		Integer id2 = baseDatos.insertarArticulo(id,a);
		listaArticulos.add(baseDatos.findArticuloById(id2));	
		return id2;
	}

}
