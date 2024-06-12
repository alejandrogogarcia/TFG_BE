package es.udc.tfg.app.service.noteservice;

import java.util.List;

import es.udc.tfg.app.model.note.Note;
import es.udc.tfg.app.model.noteline.Noteline;
import es.udc.tfg.app.util.exceptions.InputValidationException;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.tfg.app.util.exceptions.ProductInsufficientStockException;

public interface NoteService {

	public Note createNote(Long creatorId, Long clientId, String comment, List<NotelineData> notelineDataList)
			throws InstanceNotFoundException, InputValidationException, ProductInsufficientStockException;

	public Note modifyNote(Long noteId, Long clientId, String comment)
			throws InstanceNotFoundException, InputValidationException;

	public Noteline addNoteLine(Long noteId, Long noteLineId, NotelineData notelineData)
			throws InstanceNotFoundException, InputValidationException, ProductInsufficientStockException;

	public void modifyNoteLine(Long noteId, Long noteLineId, NotelineData notelineData)
			throws InstanceNotFoundException, InputValidationException, ProductInsufficientStockException;

	public Note findNoteById(Long noteId) throws InstanceNotFoundException;

	public void removeNote(Long noteId) throws InstanceNotFoundException;

	public void removeNoteLine(Long noteId, Long notelineId) throws InstanceNotFoundException;

	public Noteline findNotelineById(Long noteId, Long notelineId) throws InstanceNotFoundException;

	public List<Noteline> findNotelinesByNoteId(Long noteId);

	public List<Noteline> findNotelinesByProductId(Long productId);

	public List<Note> findNotesByClientId(Long clientId);

	public List<Note> findNotesByCreatorId(Long creatorId);

	public List<Note> findNotesByInvoiceId(Long invoiceId);

	public List<Note> findAllNoBilledNotes();

	public List<Note> findAllBilledNotes();

	public List<Note> findAllNotes();

}
