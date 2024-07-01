package es.udc.tfg.app.service.invoiceservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.udc.tfg.app.model.client.Client;
import es.udc.tfg.app.model.client.ClientDao;
import es.udc.tfg.app.model.invoice.Invoice;
import es.udc.tfg.app.model.invoice.InvoiceDao;
import es.udc.tfg.app.model.note.Note;
import es.udc.tfg.app.model.note.NoteDao;
import es.udc.tfg.app.model.user.User;
import es.udc.tfg.app.model.user.UserDao;
import es.udc.tfg.app.util.exceptions.InputValidationException;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.tfg.app.util.exceptions.NoteAlreadyInvoicedException;

public class InvoiceServiceImp implements InvoiceService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ClientDao clientDao;

	@Autowired
	private InvoiceDao invoiceDao;

	@Autowired
	private NoteDao noteDao;

	@Override
	public Invoice createInvoice(Long creatorId, Long clientId, List<Long> noteIdList)
			throws InstanceNotFoundException, NoteAlreadyInvoicedException, InputValidationException {

		User creator = userDao.find(creatorId);
		Client client = clientDao.find(clientId);

		Invoice invoice = new Invoice(client, creator);
		invoiceDao.save(invoice);

		Note note = null;
		for (Long noteId : noteIdList) {
			note = noteDao.find(noteId);
			if (note.getInvoice() == null) {
				throw new NoteAlreadyInvoicedException(noteId);
			}
			if (note.getClient().getId() != client.getId()) {
				throw new InputValidationException("ClientId", "Invoice Client and Note Client should match");
			}
			note.setInvoice(invoice);
			invoice.addNote(note);
			invoice.setSubtotal(note.getSubtotal());
		}
		creator.addInvoice(invoice);

		return invoice;
	}

	@Override
	public Invoice findInvoiceById(Long invoiceId) throws InstanceNotFoundException {
		return invoiceDao.find(invoiceId);
	}

	@Override
	public List<Invoice> findInvoicesByClientId(Long clientId) {
		return invoiceDao.findByClientId(clientId);
	}

	@Override
	public List<Invoice> findInvoicesByCreatorId(Long creatorId) {
		return invoiceDao.findByCreatorId(creatorId);
	}

	@Override
	public List<Invoice> findAllInvoices() {
		return invoiceDao.findAll();
	}

}
