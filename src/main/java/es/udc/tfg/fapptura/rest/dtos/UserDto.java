package es.udc.tfg.fapptura.rest.dtos;

//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

public class UserDto {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String dni;
	private String language;
	private String birthDate;
	private String role;
	
	public interface AllValidations {
	}

	public interface UpdateValidations {
	}

	public UserDto() {
	}	

	public UserDto(Long id, String firstName, String lastName, String email, String dni,
			String language, String birthDate, String role) {
		this.id = id;
		this.firstName = firstName.trim();
		this.lastName = lastName.trim();
		this.email = email.trim();
		this.dni = dni.trim();
		this.language = language;
		this.birthDate = birthDate;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


//	@NotNull(groups = { AllValidations.class, UpdateValidations.class })
//	@Size(min = 1, max = 60, groups = { AllValidations.class, UpdateValidations.class })
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName.trim();
	}

//	@NotNull(groups = { AllValidations.class, UpdateValidations.class })
//	@Size(min = 1, max = 60, groups = { AllValidations.class, UpdateValidations.class })
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName.trim();
	}

//	@NotNull(groups = { AllValidations.class, UpdateValidations.class })
//	@Size(min = 1, max = 60, groups = { AllValidations.class, UpdateValidations.class })
//	@Email(groups = { AllValidations.class, UpdateValidations.class })
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.trim();
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	

}
