package es.udc.tfg.app.model.noteline;

import java.io.Serializable;
import java.util.Objects;

import es.udc.tfg.app.model.note.Note;

public class NotelinePK implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long notelineId;

	private Note note;

	public NotelinePK() {
	}

	public NotelinePK(Long notelineId, Note note) {
		this.notelineId = notelineId;
		this.note = note;
	}

	public Long getNotelineId() {
		return notelineId;
	}

	public void setNotelineId(Long notelineId) {
		this.notelineId = notelineId;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

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
