package es.udc.tfg.app.model.credentials;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.udc.tfg.app.model.user.User;

@Entity
@Table(name = "credentials")
public class Credentials {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String encryptedPassword;
	
	// ....... Relaciones 1 a 1  ......./
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	
	// ....... Constructors .......//

	public Credentials() {
	}
	

	public Credentials(String encryptedPassword, User user) {
		this.encryptedPassword = encryptedPassword;
		this.user = user;
	}

	// ....... Getters and Setters .......//

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
