package com.ArgenProgSpringLorena.proyecto.services;



import java.util.List;


import org.springframework.stereotype.Service;

import com.ArgenProgSpringLorena.proyecto.models.Libro;






@Service

public interface ILibroService {
	
	   List<Libro> obtenerTodosLosLibros();
	   
	   public Libro  buscarLibroPorId(Integer libroId);
	   
	   public void guardarLibro(Libro libro);
	   
	   public void modificarLibro(Libro libro);
	   
	   public void darDeBajaLibro(Integer libroId);
	
	 
	 
}
