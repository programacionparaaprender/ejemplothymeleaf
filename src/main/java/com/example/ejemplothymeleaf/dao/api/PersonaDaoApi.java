package com.example.ejemplothymeleaf.dao.api;

import org.springframework.data.repository.CrudRepository;

import com.example.ejemplothymeleaf.model.Persona;


public interface PersonaDaoApi extends CrudRepository<Persona, Long> {

}
