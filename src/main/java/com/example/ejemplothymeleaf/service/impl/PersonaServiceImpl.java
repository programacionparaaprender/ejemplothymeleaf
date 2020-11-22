package com.example.ejemplothymeleaf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.example.ejemplothymeleaf.commons.GenericServiceImpl;
import com.example.ejemplothymeleaf.dao.api.PersonaDaoApi;
import com.example.ejemplothymeleaf.model.Persona;
import com.example.ejemplothymeleaf.service.api.PersonaServiceAPI;


@Service
public class PersonaServiceImpl extends GenericServiceImpl<Persona, Long> implements PersonaServiceAPI{
	
	@Autowired
	private PersonaDaoApi presonaDaoAPI;
	
	
	
	@Override
	public CrudRepository<Persona, Long> getDao(){
		return presonaDaoAPI;
	}
}
