package es.udc.tfg.app.model.invoice;

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

import es.udc.tfg.app.model.client.Client;
import es.udc.tfg.app.model.note.Note;
import es.udc.tfg.app.model.user.User;

@Entity
@Table(name = "invoices")
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Float subtotal;

	private Float taxes;

	private Float total;

	private Calendar createDate;

	// ....... Relaciones N a 1 ......./

	@ManyToOne()
	@JoinColumn(name = "client_id")
	private Client client;

	@ManyToOne()
	@JoinColumn(name = "creator_id")
	private User creator;

	// ....... Relaciones 1 a N ......./

	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
	private List<Note> notes = new ArrayList<>();

	// ....... Constructores ......./

	public Invoice() {
	}

	public Invoice(Client client, User creator) {
		this.subtotal = (float) 0.0;
		this.taxes = (float) 0.0;
		this.total = (float) 0.0;
		this.createDate = Calendar.getInstance();
		this.client = client;
		this.creator = creator;
	}

	// ....... Getters & Setters ......./

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Float subtotal) {
		this.subtotal = subtotal;
		this.taxes = subtotal * (client.getTax().getValue() / 100);
		this.total = (subtotal * (client.getTax().getValue() / 100)) + subtotal;
	}

	public Float getTaxes() {
		return taxes;
	}

	public void setTaxes(Float taxes) {
		this.taxes = taxes;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Calendar getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}

	// ....... Relaciones N a 1 ......./

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	// ....... Relaciones 1 a N ......./

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	
	public void addNote(Note note) {
		this.notes.add(note);
		setSubtotal(this.subtotal + note.getSubtotal());
	}

}
