package es.udc.tfg.fapptura.model.client;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.udc.tfg.fapptura.model.invoice.Invoice;
import es.udc.tfg.fapptura.model.note.Note;
import es.udc.tfg.fapptura.model.user.User;

@Entity
@Table(name = "clients")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;

	private String lastName;

	private String dni;

	private String address;

	private String city;

	private Long postCode;

	private String province;

	private String email;

	private Long phoneNumber;

	private Calendar createDate;
	
	private Calendar modifyDate;

	// ....... Relaciones N a 1 ......./

	@ManyToOne()
	@JoinColumn(name = "creator_id")
	private User creator;

	// ....... Relaciones 1 a N ......./

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<Invoice> invoices = new ArrayList<>();

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<Note> notes = new ArrayList<>();

	// ....... Constructores ......./

	public Client() {
	}

	public Client(String firstName, String lastName, String dni, String address, String city, Long postCode,
			String province, String email, Long phoneNumber, User creator) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dni = dni;
		this.address = address;
		this.city = city;
		this.postCode = postCode;
		this.province = province;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.createDate = Calendar.getInstance();
		this.modifyDate = Calendar.getInstance();
		this.creator = creator;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getPostCode() {
		return postCode;
	}

	public void setPostCode(Long postCode) {
		this.postCode = postCode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Calendar getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}
	
	public Calendar getModifyDate() {
		return modifyDate;
	}
	
	// ....... Relaciones N a 1 ......./

	public void setModifyDate(Calendar modifyDate) {
		this.modifyDate = modifyDate;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	// ....... Relaciones 1 a N ......./

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	

}
