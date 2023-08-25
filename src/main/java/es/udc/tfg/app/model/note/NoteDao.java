package es.udc.tfg.fapptura.model.note;

import java.util.List;

import es.udc.tfg.fapptura.model.genericDao.GenericDao;

public interface NoteDao extends GenericDao<Note, Long>{
	
	
	public List<Note> findByClientId(Long clientId);
	
	public List<Note> findByClientId(Long clientId, boolean invoiced);

	public List<Note> findByClientIdAndInvoiceId(Long clientId, Long invoiceId);
	
	public List<Note> findByClientIdAndCreatorId(Long clientId, Long creatorId);
	
	public List<Note> findByCreatorId(Long creatorId);
	
	public List<Note> findByInvoiceId(Long invoiceId);
		
	public List<Note> findAll();

}
