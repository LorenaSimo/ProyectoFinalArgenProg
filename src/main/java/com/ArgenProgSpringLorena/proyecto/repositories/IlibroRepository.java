package com.ArgenProgSpringLorena.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import com.ArgenProgSpringLorena.proyecto.models.Libro;

@Repository

public interface IlibroRepository  extends JpaRepository< Libro,Integer >{

}
