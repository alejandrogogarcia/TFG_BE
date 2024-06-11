package es.udc.tfg.app.service.invoiceservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.udc.tfg.app.model.client.Client;
import es.udc.tfg.app.model.client.ClientDao;
import es.udc.tfg.app.model.invoice.Invoice;
import es.udc.tfg.app.model.user.User;
import es.udc.tfg.app.model.user.UserDao;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;

public class InvoiceServiceImp implements InvoiceService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ClientDao clientDao;

	@Override
	public Invoice createInvoice(Long creatorId, Long clientId, List<Long> noteIdList)
			throws InstanceNotFoundException {

		User creator = userDao.find(creatorId);
		Client client = clientDao.find(clientId);

		return null;
	}

	@Override
	public Invoice findInvoiceById(Long invoiceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Invoice> findInvoicesByClientId(Long clientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Invoice> findInvoicesByCreatorId(Long creatorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Invoice> findAllInvoices() {
		// TODO Auto-generated method stub
		return null;
	}

}
