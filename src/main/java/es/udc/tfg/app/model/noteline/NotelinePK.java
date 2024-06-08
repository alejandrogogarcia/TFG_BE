package es.udc.tfg.app.model.noteline;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

import es.udc.tfg.app.model.note.Note;

public class NotelinePK implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long notelineId;

	private Note note;

//	public NotelinePK() {
//	}
//
//	public NotelinePK(Long notelineId, Long noteId) {
//		this.notelineId = notelineId;
//		this.noteId = noteId;
//	}
//
////	public Long getId() {
////		return notelineId;
////	}
////
////	public void setId(Long id) {
////		this.notelineId = id;
////	}
//
//	public void setNotelineId(Long notelineId) {
//		this.notelineId = notelineId;
//	}
//
//	public void setNoteId(Long noteId) {
//		this.noteId = noteId;
//	}
//
//	public Long getNoteId() {
//		return noteId;
//	}
//
//	public Long getNotelineId() {
//		return notelineId;
//	}
//
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		NotelinePK other = (NotelinePK) obj;
		return Objects.equals(notelineId, other.notelineId) && Objects.equals(note, other.note);
	}

	@Override
	public int hashCode() {
		return Objects.hash(notelineId, note);
	}

}
