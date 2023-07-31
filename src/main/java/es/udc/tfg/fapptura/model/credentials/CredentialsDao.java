package es.udc.tfg.fapptura.model.credentials;

import java.util.List;

import es.udc.tfg.fapptura.model.genericDao.GenericDao;
import es.udc.tfg.fapptura.util.exceptions.InstanceNotFoundException;

public interface CredentialsDao extends GenericDao<Credentials, Long> {
	
	public Credentials findByUserId(Long userId) throws InstanceNotFoundException;
	
	public List<Credentials> findAll();

}
