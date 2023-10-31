package com.ArgenProgSpringLorena.proyecto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.ArgenProgSpringLorena.proyecto.models.Libro;
import com.ArgenProgSpringLorena.proyecto.repositories.IlibroRepository;

@Service

public class LibroServiceImpl implements ILibroService {

	@Autowired
	private IlibroRepository libroRepository;

	@Override
	public List<Libro> obtenerTodosLosLibros() {

		return libroRepository.findAll();
	}

	@Override
	public Libro buscarLibroPorId(Integer libroId) {

		Libro libro = libroRepository.findById(libroId).orElse(null);
		return libro;

	}

	@Override
	public void guardarLibro(Libro libro) {
		libroRepository.save(libro);

	}

	@Override
	public void modificarLibro(Libro libro) {
		libroRepository.save(libro);

	}

	@Override
	public void darDeBajaLibro(Integer libroId) {
		Libro libro = libroRepository.findById(libroId).orElse(null);
		if (libro != null) {
			libro.setAlta(false);
			libroRepository.save(libro);
		}

	}

	public void deleteLibroById(Integer libro_Id) {
		libroRepository.deleteById(libro_Id);
		
	}
}
