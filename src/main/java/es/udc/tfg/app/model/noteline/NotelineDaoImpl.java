package es.udc.tfg.app.model.noteline;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg.app.model.genericDao.GenericDaoImpl;

@Repository
@Transactional
public class NotelineDaoImpl extends GenericDaoImpl<Noteline, Long> implements NotelineDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Noteline> findByProductId(Long productId) {
		return (List<Noteline>) this.em.createQuery("SELECT n FROM Noteline n WHERE n.product.id like :productId")
				.setParameter("productId", productId).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Noteline> findByNoteId(Long noteId) {
		return (List<Noteline>) this.em.createQuery("SELECT n FROM Noteline n WHERE n.note.id like :noteId")
				.setParameter("noteId", noteId).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Noteline> findAll() {
		return (List<Noteline>) this.em.createQuery("SELECT n FROM Noteline n ORDER BY n.id");
	}

}
