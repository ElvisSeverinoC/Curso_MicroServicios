package pe.edu.galaxy.training.java.ms.gestion.talleres.service;

import java.util.List;
import java.util.Optional;

import pe.edu.galaxy.training.java.ms.gestion.talleres.service.exception.ExceptionService;

public interface GenericService<T> {

	List<T> findAll() throws ExceptionService;

	Optional<T> findById(T t) throws ExceptionService;
	
	T insert(T t) throws ExceptionService;
	
	T update(T t) throws ExceptionService;
	
	T delete(T t) throws ExceptionService;
	
}
