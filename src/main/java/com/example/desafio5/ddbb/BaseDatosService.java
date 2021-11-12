package com.example.desafio5.ddbb;

import com.example.desafio5.model.Articulo;

public interface BaseDatosService {
	
	public void initDB();
	
	public Articulo findArticuloById(int id);
	
	public Articulo insertarArticulo (Articulo articulo);

	public int lastIndex();
}
