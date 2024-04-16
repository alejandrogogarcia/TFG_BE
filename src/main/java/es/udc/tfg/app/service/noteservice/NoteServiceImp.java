package es.udc.tfg.app.service.noteservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.udc.tfg.app.model.note.Note;
import es.udc.tfg.app.model.note.NoteDao;
import es.udc.tfg.app.util.exceptions.InputValidationException;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;

public class NoteServiceImp implements NoteService{
	
	@Autowired
	private NoteDao noteDao;

	@Override
	public Note createNote() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyNote(Long noteId) throws InstanceNotFoundException, InputValidationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyNoteLine(Long noteLineId) throws InstanceNotFoundException, InputValidationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Note findNoteById(Long noteId) throws InstanceNotFoundException {
		return noteDao.find(noteId);
	}

	@Override
	public void removeNote(Long noteId) throws InstanceNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeNoteLine(Long noteLineId) throws InstanceNotFoundException {
		// TODO Auto-generated method stub
		
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
