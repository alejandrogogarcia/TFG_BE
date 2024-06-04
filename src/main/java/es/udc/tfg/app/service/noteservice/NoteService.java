package es.udc.tfg.app.service.noteservice;

import java.util.List;

import es.udc.tfg.app.model.note.Note;
import es.udc.tfg.app.model.noteline.Noteline;
import es.udc.tfg.app.util.exceptions.InputValidationException;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;

public interface NoteService{
	
	public Note createNote(Long creatorId, Long clientId,  List<NotelineData> notelineDataList) throws InstanceNotFoundException;
	
	public void modifyNote(Long noteId, Long clientId) throws InstanceNotFoundException, InputValidationException;
		
	public void modifyNoteLine(Long noteLineId, Long noteId, NotelineData notelineData)throws InstanceNotFoundException, InputValidationException;
	
	public Note findNoteById(Long noteId) throws InstanceNotFoundException;
	
	public void removeNote(Long noteId) throws InstanceNotFoundException;
	
	public void removeNoteLine(Long noteId, Long notelineId) throws InstanceNotFoundException;
		
	public Noteline findNotelineById(Long noteId, Long notelineId) throws InstanceNotFoundException;
	
	public List<Noteline> findNotelinesByNoteId(Long noteId) throws InstanceNotFoundException;
	
	public List<Noteline> findNotelinesByProductId(Long productId) throws InstanceNotFoundException;

	public List<Note> findNotesByClientId(Long clientId);
	
	public List<Note> findNotesByInvoiceId(Long invoiceId);
	
	public List<Note> findNotesByCreatorId(Long creatorId);
	
	public List<Note> findAllNotes();

}
