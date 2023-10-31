package com.ArgenProgSpringLorena.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ArgenProgSpringLorena.proyecto.models.Libro;
import com.ArgenProgSpringLorena.proyecto.services.LibroServiceImpl;

@RestController
@RequestMapping("/libros")

public class LibroController {

	@Autowired
	private LibroServiceImpl libroService;

	@GetMapping("")
	public ResponseEntity<List<Libro>> obtenerTodosLosLibros() {
		List<Libro> libros = libroService.obtenerTodosLosLibros();

		if (!libros.isEmpty()) {
			return new ResponseEntity<>(libros, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/{libroId}")
	public ResponseEntity<Libro> buscarLibroPorId(@PathVariable("libroId") Integer libroId) {
		Libro libro = libroService.buscarLibroPorId(libroId);

		if (libro != null) {
			return new ResponseEntity<>(libro, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<String> guardarLibro(@RequestBody Libro libro) {

		try {
			libroService.guardarLibro(libro);
			return new ResponseEntity<>("libro creado", HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Libro> modificarLibro(@RequestBody Libro libro) {
		try {
			libroService.modificarLibro(libro);

			Libro libroUpdated = libroService.buscarLibroPorId(libro.getLibroId());

			if (libroUpdated != null) {
				return new ResponseEntity<>(libroUpdated, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{libro_Id}")
	public ResponseEntity<String> deleteLibroById(@PathVariable Integer libro_Id) {

		try {
			Libro libro = libroService.buscarLibroPorId(libro_Id);
			if (libro != null) {
				libroService.deleteLibroById(libro_Id);
				return new ResponseEntity<>("Libro borrado", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("No existe el libro", HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


        @DeleteMapping("/baja/{libro_Id}")
          public ResponseEntity<String> bajaLibroById(@PathVariable Integer libro_Id) {

	  try {
		  Libro libro = libroService.buscarLibroPorId(libro_Id);
		  if (libro != null) {
			  libroService.darDeBajaLibro(libro_Id);
			  return new ResponseEntity<>("Libro borrado", HttpStatus.OK);
		   } else {
			return new ResponseEntity<>("No existe el libro", HttpStatus.INTERNAL_SERVER_ERROR);
		 }

	     } catch (Exception e) {
		 e.printStackTrace();
		 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
     }
	
	
	
	
	@GetMapping("/prestar/{libroId}")
	public ResponseEntity<String> prestarLibro(@PathVariable Integer libroId) {
		Libro libro = libroService.buscarLibroPorId(libroId);

		if (libro != null) {
			libro.prestarEjemplar();
			libroService.modificarLibro(libro);
			return new ResponseEntity<>("Ejemplar prestado", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Libro no encontrado", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/devolver/{libroId}")
	public ResponseEntity<String> devolverLibro(@PathVariable Integer libroId) {
		Libro libro = libroService.buscarLibroPorId(libroId);

		if (libro != null) {
			libro.devolverEjemplar();
			libroService.modificarLibro(libro);
			return new ResponseEntity<>("Ejemplar devuelto", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Libro no encontrado", HttpStatus.NOT_FOUND);
		}
		
		
		
	}

}
