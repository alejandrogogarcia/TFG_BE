package es.udc.tfg.app.service.noteservice;

import java.util.ArrayList;
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
import es.udc.tfg.app.model.noteline.NotelinePK;
import es.udc.tfg.app.model.product.Product;
import es.udc.tfg.app.model.product.ProductDao;
import es.udc.tfg.app.model.user.User;
import es.udc.tfg.app.model.user.UserDao;
import es.udc.tfg.app.util.exceptions.InputValidationException;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;

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
	public Note createNote(Long creatorId, Long clientId) throws InstanceNotFoundException {

		User creator = userDao.find(creatorId);
		Client client = clientDao.find(clientId);

		Note note = new Note(client, creator);
		// REVISAR TRANSACCIONALIDAD
		noteDao.save(note);
		return note;
	}

	@Override
	public void addNotelines(Long noteId, List<NotelineData> notelineData) throws InstanceNotFoundException {

		Note note = noteDao.find(noteId);
		List<Noteline> notelines = new ArrayList<>();

		for (NotelineData noteline : notelineData) {

			if (noteline.getComment() == null) {
				Product product = productDao.findByReference(noteline.getReference());
				notelines.add(new Noteline(product.getPrice(), noteline.getAmount(), noteline.getDiscount(), null,
						product, note));
			} else {
				notelines.add(new Noteline(null, null, null, noteline.getComment(), null, note));
			}
		}
		note.setNotelines(notelines);
		noteDao.save(note);

		for (Noteline noteline : notelines) {
			notelineDao.save(noteline);
		}

	}

	@Override
	public void modifyNote(Long noteId, Long clientId) throws InstanceNotFoundException, InputValidationException {

		Note note = noteDao.find(noteId);
		Client client = clientDao.find(clientId);

		note.setClient(client);
		noteDao.save(note);

	}

	@Override
	public void modifyNoteLine(Long noteId, Long noteLineId, NotelineData notelineData)
			throws InstanceNotFoundException, InputValidationException {
		
		Noteline noteline = notelineDao.find(noteId, noteLineId);
		
		if (notelineData.getComment() == null) {
			Product product = productDao.findByReference(notelineData.getReference());
			noteline.setComment(null);
			noteline.setPrice(product.getPrice());
			noteline.setAmount(notelineData.getAmount());
			noteline.setDiscount(notelineData.getDiscount());
			noteline.setProduct(product);
		} else {
			noteline.setComment(notelineData.getComment());
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

		NotelinePK id = new NotelinePK();
		id.setId(noteLineId);
		id.setNoteId(noteId);
		// REVISAR
		// notelineDao.remove(id);

	}

	@Override
	public Noteline findNotelineById(Long noteId, Long notelineId) throws InstanceNotFoundException {
		return notelineDao.find(noteId, notelineId);
	}

	@Override
	public List<Noteline> findNotelinesByNoteId(Long noteId) throws InstanceNotFoundException {
		return notelineDao.findByNoteId(noteId);
	}

	@Override
	public List<Noteline> findNotelinesByProductId(Long productId) throws InstanceNotFoundException {
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
