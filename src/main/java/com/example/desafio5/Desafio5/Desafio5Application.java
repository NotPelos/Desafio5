package com.example.desafio5.Desafio5;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.desafio5.ddbb.BaseDatosService;
import com.example.desafio5.model.Articulo;

@SpringBootApplication
public class Desafio5Application implements CommandLineRunner {
	
	private BaseDatosService baseDatosService;

	public static void main(String[] args) {
		SpringApplication.run(Desafio5Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		baseDatosService.initDB();
		Articulo articulo = new Articulo("Calcetines",3.49);
		baseDatosService.insertarArticulo(articulo);
		Articulo buscado = baseDatosService.findArticuloById(baseDatosService.lastIndex());
		
		
	}

}
