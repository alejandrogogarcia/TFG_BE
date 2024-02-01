package es.udc.tfg.app.model.client;

import java.util.List;

import es.udc.tfg.app.model.genericDao.GenericDao;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;

public interface ClientDao extends GenericDao<Client, Long>{
	
	public List<Client> findByFirstName(String firstName);
	
	public List<Client> findByLastName(String lastName);
	
	public Client findByDni(String dni) throws InstanceNotFoundException;
	
	public List<Client> findByCity(String city);
	
	public List<Client> findByCreatorId(Long creatorId);
	
	public List<Client> findAll();

}