package es.udc.tfg.app.model.noteline;

import java.util.List;

import es.udc.tfg.app.model.genericDao.GenericDao;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;

public interface NotelineDao extends GenericDao<Noteline, Long> {

	public Noteline find(Long noteId, Long notelineId) throws InstanceNotFoundException;

	public void remove(Long noteId, Long notelineId) throws InstanceNotFoundException;

	public List<Noteline> findByProductId(Long productId);

	public List<Noteline> findByNoteId(Long noteId);

	public List<Noteline> findAll();

	void remove(NotelinePK PK) throws InstanceNotFoundException;

}
