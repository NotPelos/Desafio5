package com.example.desafio5.ddbb;

import java.util.HashMap;
import java.util.Map;

import com.example.desafio5.model.Articulo;

public class BaseDatosServiceImpl implements BaseDatosService{
	
	private Map<Integer, Articulo> storage;

	@Override
	public void initDB() {

		storage = new HashMap<>();
		
		storage.put(1, new Articulo ("camiseta", 18.95));
		storage.put(2, new Articulo ("camiseta", 18.95));
		storage.put(3, new Articulo ("camiseta", 18.95));
		storage.put(4, new Articulo ("camiseta", 18.95));
		
		
	}

	@Override
	public Articulo findArticuloById(int id) {
		// TODO Auto-generated method stub
		return storage.get(id);
	}
	
	
	public Integer insertarArticulo(Integer id,Articulo articulo) {
		Integer retorno = null;
		if(!storage.containsKey(id)) {
			storage.put(id, articulo);
		}else {
			System.out.println("La id ya está en uso");
		}
		for (Integer clave : storage.keySet()) {
			if(storage.get(clave).equals(articulo)) {
				retorno = clave;
			}
		}
		return retorno;
	}
	
	public int lastIndex() {
		return storage.size();
	}
	
	public void setStorage(Map<Integer, Articulo> storage) {
		this.storage = storage;
	}

	@Override
	public Articulo insertarArticulo(Articulo articulo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	

}
