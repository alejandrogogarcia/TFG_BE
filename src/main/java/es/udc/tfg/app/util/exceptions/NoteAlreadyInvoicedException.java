package es.udc.tfg.app.util.exceptions;

@SuppressWarnings("serial")
public class NoteAlreadyInvoicedException extends Exception {

	private Object instanceId;

	public NoteAlreadyInvoicedException (Object instanceId) {
		super("Note already invoiced (noteId = '" + instanceId + "')");
		this.instanceId = instanceId;
	}

	public Object getInstanceId() {
		return instanceId;
	}

}
