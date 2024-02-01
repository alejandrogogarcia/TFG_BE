package es.udc.tfg.app.model.credentials;

import java.util.List;

import es.udc.tfg.app.model.genericDao.GenericDao;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;

public interface CredentialsDao extends GenericDao<Credentials, Long> {
	
	public Credentials findByUserId(Long userId) throws InstanceNotFoundException;
	
	public List<Credentials> findAll();

}
