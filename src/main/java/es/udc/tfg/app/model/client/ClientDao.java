package es.udc.tfg.fapptura.model.client;

import java.util.List;

import es.udc.tfg.fapptura.model.genericDao.GenericDao;
import es.udc.tfg.fapptura.util.exceptions.InstanceNotFoundException;

public interface ClientDao extends GenericDao<Client, Long>{
	
	public List<Client> findByFirstName(String firstName);
	
	public List<Client> findByLastName(String lastName);
	
	public Client findByDni(String dni) throws InstanceNotFoundException;
	
	public List<Client> findByCity(String city);
	
	public List<Client> findByCreatorId(Long creatorId);
	
	public List<Client> findAll();

}