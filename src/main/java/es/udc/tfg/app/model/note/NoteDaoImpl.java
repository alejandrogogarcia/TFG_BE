package es.udc.tfg.app.model.note;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg.app.model.genericDao.GenericDaoImpl;

@Repository
@Transactional
public class NoteDaoImpl extends GenericDaoImpl<Note, Long> implements NoteDao{
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Note> findByClientId(Long clientId) {
		return (List<Note>) this.em.createQuery("SELECT n FROM Note n WHERE n.client.id like :clientId")
				.setParameter("clientId", clientId).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Note> findByClientId(Long clientId, boolean invoiced) {
		
		if (invoiced) {
			return (List<Note>) this.em.createQuery("SELECT n FROM Note n WHERE n.client.id like :clientId AND  n.invoice IS NOT NULL")
					.setParameter("clientId", clientId).getResultList();
		} else {
			return (List<Note>) this.em.createQuery("SELECT n FROM Note n WHERE n.client.id like :clientId AND  n.invoice IS NULL")
					.setParameter("clientId", clientId).getResultList();
		}
		
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Note> findByClientIdAndInvoiceId(Long clientId, Long invoiceId) {
		return (List<Note>) this.em.createQuery("SELECT n FROM Note n WHERE n.client.id like :clientId AND n.invoice.id like :invoiceId ")
				.setParameter("clientId", clientId).setParameter("invoiceId", invoiceId).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Note> findByClientIdAndCreatorId(Long clientId, Long creatorId) {
		return (List<Note>) this.em.createQuery("SELECT n FROM Note n WHERE n.client.id like :clientId AND n.creator.id like :creatorId ")
				.setParameter("clientId", clientId).setParameter("creatorId", creatorId).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Note> findByCreatorId(Long creatorId) {
		return (List<Note>) this.em.createQuery("SELECT n FROM Note n WHERE n.creator.id like :creatorId")
				.setParameter("creatorId", creatorId).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Note> findByInvoiceId(Long invoiceId) {
		return (List<Note>) this.em.createQuery("SELECT n FROM Note n WHERE n.invoice.id like :invoiceId")
				.setParameter("invoiceId", invoiceId).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Note> findAll() {
		return (List<Note>) this.em.createQuery("SELECT n FROM Note n ORDER BY n.id").getResultList();
	}

	

}
