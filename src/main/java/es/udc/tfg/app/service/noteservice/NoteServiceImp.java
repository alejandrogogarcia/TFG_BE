package es.udc.tfg.app.service.noteservice;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.tfg.app.model.client.Client;
import es.udc.tfg.app.model.client.ClientDao;
import es.udc.tfg.app.model.note.Note;
import es.udc.tfg.app.model.note.NoteDao;
import es.udc.tfg.app.model.noteline.Noteline;
import es.udc.tfg.app.model.noteline.NotelineDao;
import es.udc.tfg.app.model.product.Product;
import es.udc.tfg.app.model.product.ProductDao;
import es.udc.tfg.app.model.user.User;
import es.udc.tfg.app.model.user.UserDao;
import es.udc.tfg.app.util.exceptions.InputValidationException;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.tfg.app.util.validator.ValidatorProperties;

@Transactional
@Service
public class NoteServiceImp implements NoteService {

	@Autowired
	private NoteDao noteDao;

	@Autowired
	private NotelineDao notelineDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ClientDao clientDao;

	@Autowired
	private ProductDao productDao;

	@Override
	public Note createNote(Long creatorId, Long clientId, String comment, List<NotelineData> notelineDataList)
			throws InstanceNotFoundException, InputValidationException {

		User creator = userDao.find(creatorId);
		Client client = clientDao.find(clientId);

		Note note = new Note(client, creator);
		if (comment != null) {
			if (comment.trim().length() != 0) {
				note.setComment(comment);
			}
		}
		noteDao.save(note);
		Noteline noteline = null;

		for (NotelineData notelineData : notelineDataList) {

			Product product = productDao.findByReference(notelineData.getReference());
			Long notelineId = (long) (notelineDataList.indexOf(notelineData) + 1);
			Integer amount = notelineData.getAmount();
			if (amount == 0) {
				throw new InputValidationException("amount", "It cannot be 0");
			}
			Integer discount = notelineData.getDiscount();
			if (discount < 0 || discount > 100) {
				throw new InputValidationException("discount", "It cannot be less than 0 or greater than 100");
			}
			noteline = new Noteline(notelineId, product.getPrice(), amount, discount, product, note);
			String commentNoteline = notelineData.getComment();
			if (commentNoteline != null) {
				if (commentNoteline.trim().length() != 0) {
					noteline.setComment(commentNoteline);
				}
			}
			note.addNoteline(noteline);
			notelineDao.save(noteline);
		}

		return note;
	}

	@Override
	public Note modifyNote(Long noteId, Long clientId, String comment)
			throws InstanceNotFoundException, InputValidationException {

		Note note = noteDao.find(noteId);
		if (note.getClient().getId() != clientId) {
			Client client = clientDao.find(clientId);
			note.setClient(client);
		}
		if (comment != null) {
			if (comment.trim().length() != 0) {
				note.setComment(comment);
			}
		}
		noteDao.save(note);

		return note;
	}

	@Override
	public void modifyNoteLine(Long noteId, Long noteLineId, NotelineData notelineData)
			throws InstanceNotFoundException, InputValidationException {

		Noteline noteline = notelineDao.find(noteId, noteLineId);
		
		String productReference = notelineData.getReference();
		ValidatorProperties.validateString(productReference);
		if (noteline.getProduct().getReference() != productReference) {
			Product product = productDao.findByReference(notelineData.getReference());
			noteline.setPrice(product.getPrice());
			noteline.setProduct(product);
		}
		Integer amount = notelineData.getAmount();
		if (amount == 0) {
			throw new InputValidationException("amount", "It cannot be 0");
		}
		noteline.setAmount(amount);
		Integer discount = notelineData.getDiscount();
		if (discount < 0 || discount > 100) {
			throw new InputValidationException("discount", "It cannot be less than 0 or greater than 100");
		}
		noteline.setDiscount(discount);
		String comment = notelineData.getComment();
		if (comment != null) {
			if (comment != noteline.getComment()) {
				noteline.setComment(comment);
			}
		}
	}

	@Override
	public Note findNoteById(Long noteId) throws InstanceNotFoundException {
		return noteDao.find(noteId);
	}

	@Override
	public void removeNote(Long noteId) throws InstanceNotFoundException {
		noteDao.remove(noteId);
	}

	@Override
	public void removeNoteLine(Long noteId, Long noteLineId) throws InstanceNotFoundException {
		Noteline noteline = notelineDao.find(noteId, noteLineId);
		notelineDao.remove(noteId, noteLineId);
		Note note = noteDao.find(noteId);
		note.removeNoteline(noteline);
	}

	@Override
	public Noteline findNotelineById(Long noteId, Long notelineId) throws InstanceNotFoundException {
		return notelineDao.find(noteId, notelineId);
	}

	@Override
	public List<Noteline> findNotelinesByNoteId(Long noteId) {
		return notelineDao.findByNoteId(noteId);
	}

	@Override
	public List<Noteline> findNotelinesByProductId(Long productId) {
		return notelineDao.findByProductId(productId);
	}

	@Override
	public List<Note> findNotesByClientId(Long clientId) {
		return noteDao.findByClientId(clientId);
	}

	@Override
	public List<Note> findNotesByInvoiceId(Long invoiceId) {
		return noteDao.findByInvoiceId(invoiceId);
	}

	@Override
	public List<Note> findNotesByCreatorId(Long creatorId) {
		return noteDao.findByCreatorId(creatorId);
	}

	@Override
	public List<Note> findAllNotes() {
		return noteDao.findAll();
	}

}
