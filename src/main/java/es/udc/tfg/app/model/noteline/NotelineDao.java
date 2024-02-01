package es.udc.tfg.app.model.noteline;

import java.util.List;

import es.udc.tfg.app.model.genericDao.GenericDao;

public interface NotelineDao extends GenericDao<Noteline, Long>{
	
	public List<Noteline> findByProductId(Long productId);
	
	public List<Noteline> findByNoteId(Long noteId);
	
	public List<Noteline> findAll();

}
