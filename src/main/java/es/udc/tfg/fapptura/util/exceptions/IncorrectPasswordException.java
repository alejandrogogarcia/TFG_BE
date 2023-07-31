package es.udc.tfg.fapptura.util.exceptions;

@SuppressWarnings("serial")
public class IncorrectPasswordException extends Exception {
	 private String username;

	    public IncorrectPasswordException(String username) {
	    	super("Incorrect Password (username = '" + username + "')");
			this.username = username;
	    }

	    public String getLoginName() {
	        return username;
	    }

		@Override
		public String toString() {
			return "IncorrectPasswordException [username = '" + username + "']";
		}

}
