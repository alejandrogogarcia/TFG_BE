package es.udc.tfg.app.model.noteline;

import java.io.Serializable;
import java.util.Objects;

public class NotelinePK implements Serializable {

	private static final long serialVersionUID = 1L;

//	@Column(name = "noteline_id")
	private Long notelineId;

//	@Column(name = "note_id")
	private Long noteId;

	public NotelinePK() {
	}

	public NotelinePK(Long notelineId, Long noteId) {
		this.notelineId = notelineId;
		this.noteId = noteId;
	}

	public Long getId() {
		return notelineId;
	}

	public void setId(Long id) {
		this.notelineId = id;
	}

	public Long getNoteId() {
		return noteId;
	}

	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		NotelinePK other = (NotelinePK) obj;
		return Objects.equals(notelineId, other.notelineId) && Objects.equals(noteId, other.noteId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(notelineId, noteId);
	}

}
