package es.udc.tfg.fapptura.model.user;

import java.util.List;

import es.udc.tfg.fapptura.model.genericDao.GenericDao;
import es.udc.tfg.fapptura.util.enums.UserRole;
import es.udc.tfg.fapptura.util.exceptions.InstanceNotFoundException;

public interface UserDao extends GenericDao<User, Long>{
	
	public List<User> findByFirstName(String firstName);
	
	public List<User> findByLastName(String lastName);
	
	public User findByDni(String dni) throws InstanceNotFoundException;
	
	public User findByEmail(String email)throws InstanceNotFoundException;
	
	public List<User> findByUserRole(UserRole role);
	
	public List<User> findAll();

}
