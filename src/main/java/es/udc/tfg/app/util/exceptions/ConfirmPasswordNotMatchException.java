package es.udc.tfg.app.util.exceptions;

@SuppressWarnings("serial")
public class ConfirmPasswordNotMatchException extends Exception{
	
	private String password;
	private String confirmPassword;

	public ConfirmPasswordNotMatchException(String password, String confirmPassword) {
			super("Confirm Password Not Matches (password = '" + password + "', confirmPassword = " + confirmPassword + "')");
			this.password = password;
			this.confirmPassword = confirmPassword;
	    }

	public String getPassword() {
		return password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

}
