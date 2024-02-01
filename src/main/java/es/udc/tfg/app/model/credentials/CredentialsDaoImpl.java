package es.udc.tfg.app.model.credentials;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg.app.model.genericDao.GenericDaoImpl;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;

@Repository
@Transactional
public class CredentialsDaoImpl extends GenericDaoImpl<Credentials, Long> implements CredentialsDao {

	@Override
	public Credentials findByUserId(Long userId) throws InstanceNotFoundException {
		
		Credentials credentials = null;

		try {
			Query query = this.em.createQuery("SELECT c FROM Credentials c WHERE c.user.id = :userId")
					.setParameter("userId", userId);
			credentials = (Credentials) query.getSingleResult();
		} catch (Exception e) {
			throw new InstanceNotFoundException(userId, Credentials.class.getName());
		}

		return credentials;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Credentials> findAll() {
		return (List<Credentials>) this.em.createQuery("SELECT c FROM Credentials c ORDER BY c.id")
				.getResultList();
	}

}
