package es.udc.tfg.app.model.noteline;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg.app.model.genericDao.GenericDaoImpl;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;

@Repository
@Transactional
public class NotelineDaoImpl extends GenericDaoImpl<Noteline, Long> implements NotelineDao {

	@Override
	public Noteline find(Long noteId, Long notelineId) throws InstanceNotFoundException {

		Noteline noteline = null;

		try {
			Query query = this.em
					.createQuery("SELECT n FROM Noteline n WHERE n.notelineId = :notelineId AND n.note.id = :noteId")
					.setParameter("notelineId", notelineId).setParameter("noteId", noteId);
			noteline = (Noteline) query.getSingleResult();
		} catch (Exception e) {
			throw new InstanceNotFoundException(noteId + " - " + notelineId, Noteline.class.getName());
		}

		return noteline;
	}

	@Override
	public void remove(Long noteId, Long notelineId) throws InstanceNotFoundException {

		try {
			Query query = this.em
					.createQuery("DELETE FROM Noteline  WHERE notelineId = :notelineId AND note.id = :noteId")
					.setParameter("notelineId", notelineId).setParameter("noteId", noteId);
			query.executeUpdate();
		} catch (Exception e) {
			throw new InstanceNotFoundException(noteId + " - " + notelineId, Noteline.class.getName());
		}
	}
	
	@Override
	public void remove(NotelinePK PK) throws InstanceNotFoundException {
		Noteline entity = find(PK.getNote().getId(), PK.getNotelineId());
        if (entity != null) {
        	em.remove(entity);
        }
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Noteline> findByProductId(Long productId) {
		return (List<Noteline>) this.em.createQuery("SELECT n FROM Noteline n WHERE n.product.id like :productId")
				.setParameter("productId", productId).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Noteline> findByNoteId(Long noteId) {
		return (List<Noteline>) this.em.createQuery("SELECT n FROM Noteline n WHERE n.noteId like :noteId")
				.setParameter("noteId", noteId).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Noteline> findAll() {
		return (List<Noteline>) this.em.createQuery("SELECT n FROM Noteline n ORDER BY n.id");
	}

}
