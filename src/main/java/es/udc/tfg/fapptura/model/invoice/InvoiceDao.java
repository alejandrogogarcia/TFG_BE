package es.udc.tfg.fapptura.model.invoice;

import java.util.List;

import es.udc.tfg.fapptura.model.genericDao.GenericDao;

public interface InvoiceDao extends GenericDao<Invoice, Long>{
	
	public List<Invoice> findByCreatorId(Long creatorId);
	
	public List<Invoice> findByClientId(Long clientId);
	
	public List<Invoice> findAll ();

}
