package es.udc.tfg.fapptura.service.noteservice;

import java.util.List;

import es.udc.tfg.fapptura.model.note.Note;
import es.udc.tfg.fapptura.util.exceptions.InputValidationException;
import es.udc.tfg.fapptura.util.exceptions.InstanceNotFoundException;

public interface NoteService{
	
	public Note createNote();
	
	public void modifyNote(Long noteId) throws InstanceNotFoundException, InputValidationException;
	
	public void modifyNoteLine(Long noteLineId)throws InstanceNotFoundException, InputValidationException;
	
	public Note findNoteById(Long noteId) throws InstanceNotFoundException;
	
	public void removeNote(Long noteId) throws InstanceNotFoundException;
	
	public void removeNoteLine(Long noteLineId) throws InstanceNotFoundException;
		
	public List<Note> findNotesByClientId(Long clientId);
	
	public List<Note> findNotesByInvoiceId(Long invoiceId);
	
	public List<Note> findNotesByCreatorId(Long creatorId);
	
	public List<Note> findAllNotes();

}
