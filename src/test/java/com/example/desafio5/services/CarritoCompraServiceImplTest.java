package com.example.desafio5.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.desafio5.ddbb.*;
import com.example.desafio5.model.*;

@ExtendWith(MockitoExtension.class)
class CarritoCompraServiceImplTest {
	
	@Mock
	BaseDatosServiceImpl bbddService;
	
	@InjectMocks
	private CarritoCompraServiceImpl ccService = new CarritoCompraServiceImpl();
	
	Articulo art1 = new Articulo("Gafas",152.58);
	Articulo art2 = new Articulo("Pendientes",1.52);
	List<Articulo> listado = new ArrayList<>();
	
	@BeforeEach
	public void inicializar() {		
		listado.add(art1);
		listado.add(art2);
		ccService.addArticulo(art1);
		ccService.addArticulo(art2);
	}
	
	@AfterEach
	public void resetear() {
		ccService.getArticulos().clear();
	}
	
	@Test
	void testAddArticulo() {
		Articulo articuloToAdd = new Articulo("Gafas de sol",34.99);
		int tamanyoO = ccService.listaArticulos.size();
		assertFalse(ccService.listaArticulos.contains(articuloToAdd));
		ccService.addArticulo(articuloToAdd);
		assertTrue(ccService.listaArticulos.contains(articuloToAdd));
		assertEquals(tamanyoO+1,ccService.listaArticulos.size());
	}

	@Test
	void testLimpiarCesta() {
		for (Articulo articulo : listado) {
			assertTrue(ccService.getArticulos().contains(articulo));	
		}
		ccService.limpiarcesta();
		for (Articulo articulo : listado) {
			assertFalse(ccService.getArticulos().contains(articulo));
			assertTrue(ccService.getArticulos().isEmpty());
		}			
	}


	@Test
	void testGetNumArticulo() {
		assertEquals(listado.size(),ccService.getArticulos().size());
	}

	@Test
	void testGetArticulos() {
		assertTrue(ccService.getArticulos().containsAll(listado));
	}

	@Test
	void testTotalPrice() {
		Double total = .0;
		for (Articulo articulo : listado) {
			total += articulo.getPrecio();
		}
		assertEquals(total, ccService.totalprice());
	}
	
	@Test
	void testInsertarEnDbYLista() {
		Integer idN = 25;
		Articulo artN = new Articulo("Jersey",25.85);
		when(bbddService.insertarArticulo(anyInt(), any(Articulo.class))).thenReturn(idN);
		when(bbddService.findArticuloById(anyInt())).thenReturn(artN);
		assertEquals(idN, ccService.insertarEnDbYLista(idN, artN));
		assertTrue(ccService.getArticulos().contains(artN));
		verify(bbddService,times(1)).insertarArticulo(anyInt(), any(Articulo.class));
	}

	@Test
	void testCalculadorDescuento() {
		assertEquals(95,ccService.calculadorDescuento(100, 5));
	}

	@Test
	void testAplicarDescuentoSuccess() {
		when(bbddService.findArticuloById(anyInt())).thenReturn(art1);
		Double d = 5.0;
		Double pArt = art1.getPrecio();
		Double pDescuento = pArt - pArt / 100 * d;
		assertEquals(pDescuento, ccService.aplicarDescuento(1, d));
	}
	
	@Test
	void testAplicarDescuentoFailure() {
		when(bbddService.findArticuloById(anyInt())).thenReturn(null);
		Double descuento = 5.0;
		assertNull(ccService.aplicarDescuento(2, descuento));
	}
	


}
