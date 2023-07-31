package es.udc.tfg.fapptura.model.user;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg.fapptura.model.genericDao.GenericDaoImpl;
import es.udc.tfg.fapptura.util.enums.UserRole;
import es.udc.tfg.fapptura.util.exceptions.InstanceNotFoundException;

@Repository
@Transactional
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findByFirstName(String firstName) {
		return (List<User>) this.em.createQuery("SELECT u FROM User u WHERE u.firstName like :firstName")
				.setParameter("firstName", "%" + firstName + "%").getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findByLastName(String lastName) {
		return (List<User>) this.em.createQuery("SELECT u FROM User u WHERE u.lastName like :lastName")
				.setParameter("lastName", "%" + lastName + "%").getResultList();
	}

	@Override
	public User findByDni(String dni) throws InstanceNotFoundException {

		User user = null;

		try {
			Query query = this.em.createQuery("SELECT u FROM User u WHERE u.dni = :dni").setParameter("dni", dni);
			user = (User) query.getSingleResult();
		} catch (Exception e) {
			throw new InstanceNotFoundException(dni, User.class.getName());
		}

		return user;
	}

	@Override
	public User findByEmail(String email) throws InstanceNotFoundException {
		User user = null;

		try {
			Query query = this.em.createQuery("SELECT u FROM User u WHERE u.email = :email").setParameter("email",
					email);
			user = (User) query.getSingleResult();
		} catch (Exception e) {
			throw new InstanceNotFoundException(email, User.class.getName());
		}

		return user;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findByUserRole(UserRole role) {
		return (List<User>) this.em.createQuery("SELECT u FROM User u WHERE u.role like :role")
				.setParameter("role", role).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		return (List<User>) this.em.createQuery("SELECT u FROM User u ORDER BY u.id").getResultList();
	}

}
