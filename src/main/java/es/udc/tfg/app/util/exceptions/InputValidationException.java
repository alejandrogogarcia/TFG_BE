package es.udc.tfg.fapptura.util.exceptions;

@SuppressWarnings("serial")
public class InputValidationException extends Exception{
	
	private String propertyName;
	private String message;

	public InputValidationException(String propertyName, String message) {
		super("Input Validation (propertyName = '" + propertyName + "', message = " + message + ")");
		this.propertyName = propertyName;
		this.message = message;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public String getMessage() {
		return message;
	}

}
