package es.udc.tfg.fapptura.model.genericDao;

import java.io.Serializable;

import es.udc.tfg.fapptura.util.exceptions.InstanceNotFoundException;
 
public interface GenericDao<E, PK extends Serializable> {

	void save(E entity);

	E find(PK id) throws InstanceNotFoundException;

	void remove(PK id) throws InstanceNotFoundException;

	void update(E entity);
	
}
