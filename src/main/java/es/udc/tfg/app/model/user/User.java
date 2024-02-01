package es.udc.tfg.app.model.user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.udc.tfg.app.model.category.Category;
import es.udc.tfg.app.model.client.Client;
import es.udc.tfg.app.model.credentials.Credentials;
import es.udc.tfg.app.model.invoice.Invoice;
import es.udc.tfg.app.model.note.Note;
import es.udc.tfg.app.model.product.Product;
import es.udc.tfg.app.util.enums.Languages;
import es.udc.tfg.app.util.enums.UserRole;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;

	private String lastName;

	private String dni;

	private String email;

	private Calendar birthDate;

	private Calendar createDate;

	@Column(columnDefinition = "ENUM('ESP', 'GAL', 'ENG')")
	@Enumerated(EnumType.STRING)
	private Languages language;

	@Column(columnDefinition = "ENUM('EMPLOYEE', 'CLERK', 'ADMIN')")
	@Enumerated(EnumType.STRING)
	private UserRole role;

	private String image;

	private boolean isActive;

	// ....... Relaciones 1 a 1 ......./

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Credentials credentials;

	// ....... Relaciones 1 a N ......./

	@OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
	private List<Category> createdCategories = new ArrayList<>();

	@OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
	private List<Client> createdClients = new ArrayList<>();

	@OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
	private List<Invoice> createdInvoices = new ArrayList<>();

	@OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
	private List<Note> createdNotes = new ArrayList<>();

	@OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
	private List<Product> createdProdcuts = new ArrayList<>();

	// ....... Constructores ......./

	public User() {
	}

	public User(String firstName, String lastName, String dni, String email, Calendar birthDate, Languages language,
			UserRole role, String image, boolean isActive) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dni = dni;
		this.email = email;
		this.birthDate = birthDate;
		this.createDate = Calendar.getInstance();
		this.language = language;
		this.role = role;
		this.image = image;
		this.isActive = isActive;
	}

	// ....... Getters & Setters ......./

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}

	public Calendar getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}

	public Languages getLanguage() {
		return language;
	}

	public void setLanguage(Languages language) {
		this.language = language;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	// ....... Relaciones 1 a 1 ......./

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	// ....... Relaciones 1 a N ......./

	public List<Category> getCreatedCategories() {
		return createdCategories;
	}

	public void setCreatedCategories(List<Category> createdCategories) {
		this.createdCategories = createdCategories;
	}

	public void addCategory(Category category) {
		createdCategories.add(category);
	}

	public void removeCategory(Category category) {
		createdCategories.remove(category);
	}

	public List<Client> getCreatedClients() {
		return createdClients;
	}

	public void setCreatedClients(List<Client> createdClients) {
		this.createdClients = createdClients;
	}

	public void addClient(Client client) {
		createdClients.add(client);
	}

	public void removeClient(Client client) {
		createdClients.remove(client);
	}

	public List<Invoice> getCreatedInvoices() {
		return createdInvoices;
	}

	public void setCreatedInvoices(List<Invoice> createdInvoices) {
		this.createdInvoices = createdInvoices;
	}

	public void addInvoice(Invoice invoice) {
		createdInvoices.add(invoice);
	}

	public void removeInvoice(Invoice invoice) {
		createdInvoices.remove(invoice);
	}

	public List<Note> getCreatedNotes() {
		return createdNotes;
	}

	public void setCreatedNotes(List<Note> createdNotes) {
		this.createdNotes = createdNotes;
	}

	public void addNote(Note note) {
		createdNotes.add(note);
	}

	public void removeNote(Note note) {
		createdNotes.remove(note);
	}

	public List<Product> getCreatedProdcuts() {
		return createdProdcuts;
	}

	public void setCreatedProdcuts(List<Product> createdProdcuts) {
		this.createdProdcuts = createdProdcuts;
	}

	public void addProduct(Product product) {
		createdProdcuts.add(product);
	}

	public void removeProduct(Product product) {
		createdProdcuts.remove(product);
	}

}
