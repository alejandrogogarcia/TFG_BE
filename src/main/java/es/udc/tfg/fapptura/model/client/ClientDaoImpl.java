package es.udc.tfg.fapptura.model.client;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg.fapptura.model.genericDao.GenericDaoImpl;
import es.udc.tfg.fapptura.util.exceptions.InstanceNotFoundException;

@Repository
@Transactional
public class ClientDaoImpl extends GenericDaoImpl<Client, Long> implements ClientDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Client> findByFirstName(String firstName) {
		return (List<Client>) this.em.createQuery("SELECT c FROM Client c WHERE c.firstName like :firstName")
				.setParameter("firstName", "%" + firstName + "%").getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Client> findByLastName(String lastName) {
		return (List<Client>) this.em.createQuery("SELECT c FROM Client c WHERE c.lastName like :lastName")
				.setParameter("lastName", "%" + lastName + "%").getResultList();
	}

	@Override
	public Client findByDni(String dni) throws InstanceNotFoundException {
		Client client = null;

		try {
			Query query = this.em.createQuery("SELECT c FROM Client c WHERE c.dni like :dni").setParameter("dni", dni);
			client = (Client) query.getSingleResult();
		} catch (Exception e) {
			throw new InstanceNotFoundException(dni, Client.class.getName());
		}

		return client;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Client> findByCity(String city) {
		return (List<Client>) this.em.createQuery("SELECT c FROM Client c WHERE c.city like :city")
				.setParameter("city", "%" + city + "%").getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Client> findByCreatorId(Long creatorId) {
		return (List<Client>) this.em.createQuery("SELECT c FROM Client c WHERE c.creator.id like :creatorId")
				.setParameter("creatorId", creatorId).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Client> findAll() {
		return (List<Client>) this.em.createQuery("SELECT c FROM Client c ORDER BY c.id").getResultList();
	}

}
