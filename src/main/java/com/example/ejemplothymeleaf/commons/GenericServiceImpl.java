package com.example.ejemplothymeleaf.commons;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class GenericServiceImpl<T, ID extends Serializable> implements GenericServiceAPI<T, ID>
{

	@Override
	public T save(T entity) {
		return getDao().save(entity);
	}

	@Override
	public void delete(ID id) {
		getDao().deleteById(id);
	}

	@Override
	public T get(ID id) {
		Optional<T> obj = getDao().findById(id);
		if(obj.isPresent()) {
			return obj.get();
		}
		return null;
	}

	@Override
	public List<T> getAll() {
		List<T> returnList = new ArrayList<T>();
		getDao().findAll().forEach(obj -> returnList.add(obj));
		return returnList;
	}
	
	public abstract CrudRepository<T, ID> getDao();
}
