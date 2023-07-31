package es.udc.tfg.fapptura.model.invoice;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg.fapptura.model.genericDao.GenericDaoImpl;

@Repository
@Transactional
public class InvoiceDaoImpl extends GenericDaoImpl<Invoice, Long> implements InvoiceDao{

	@Override
	@SuppressWarnings("unchecked")
	public List<Invoice> findByCreatorId(Long creatorId) {
		return (List<Invoice>) this.em.createQuery("SELECT i FROM Invoice i WHERE i.creator.id like :creatorId")
				.setParameter("creatorId", creatorId).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Invoice> findByClientId(Long clientId) {
		return (List<Invoice>) this.em.createQuery("SELECT i FROM Invoice i WHERE i.client.id like :clientId")
				.setParameter("clientId", clientId).getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Invoice> findAll() {
		return (List<Invoice>) this.em.createQuery("SELECT i FROM Invoice i ORDER BY i.id");
				
	}

}
