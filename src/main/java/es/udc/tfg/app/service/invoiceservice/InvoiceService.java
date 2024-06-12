package es.udc.tfg.app.service.invoiceservice;

import java.util.List;

import es.udc.tfg.app.model.invoice.Invoice;
import es.udc.tfg.app.util.exceptions.InputValidationException;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.tfg.app.util.exceptions.NoteAlreadyInvoicedException;

public interface InvoiceService {

	public Invoice createInvoice(Long creatorId, Long clientId, List<Long> noteIdList)
			throws InstanceNotFoundException, NoteAlreadyInvoicedException, InputValidationException;
	
	public Invoice findInvoiceById(Long invoiceId) throws InstanceNotFoundException;
	
	public List<Invoice> findInvoicesByClientId(Long clientId);
	
	public List<Invoice> findInvoicesByCreatorId(Long creatorId);
	
	public List<Invoice> findAllInvoices();

}
