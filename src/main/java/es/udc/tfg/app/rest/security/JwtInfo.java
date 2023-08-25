package es.udc.tfg.fapptura.rest.security;

public class JwtInfo {
	
	private Long userId;
	private String dni;
	private String role;
	
	public JwtInfo(Long userId, String dni, String role) {
		this.userId = userId;
		this.dni = dni;
		this.role = role;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getdni() {
		return dni;
	}

	public void setdni(String dni) {
		this.dni = dni;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	

}
