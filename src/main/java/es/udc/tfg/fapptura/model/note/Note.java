package es.udc.tfg.fapptura.model.note;

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

import es.udc.tfg.fapptura.model.client.Client;
import es.udc.tfg.fapptura.model.invoice.Invoice;
import es.udc.tfg.fapptura.model.noteline.Noteline;
import es.udc.tfg.fapptura.model.user.User;

@Entity
@Table(name = "notes")
public class Note {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Float subtotal;
	
	private Float taxes;
	
	private Float total;
	
	private Calendar createDate;
	
	// ....... Relaciones N a 1  ......./
	
	@ManyToOne()
    @JoinColumn(name = "client_id")
	private Client client;
	
	@ManyToOne()
    @JoinColumn(name = "creator_id")
	private User creator; 

	@ManyToOne()
    @JoinColumn(name = "invoice_id")
	private Invoice invoice;
	
	// ....... Relaciones 1 a N  ......./
	
	@OneToMany(mappedBy = "note", cascade = CascadeType.ALL)
	private List<Noteline> notelines = new ArrayList<>();

	// ....... Constructores ......./
	
	public Note() {
	}

	public Note(Float subtotal, Float taxes, Float total, Calendar createDate, Client client, User creator) {
		this.subtotal = subtotal;
		this.taxes = taxes;
		this.total = total;
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

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public List<Noteline> getNotelines() {
		return notelines;
	}

	public void setNotelines(List<Noteline> notelines) {
		this.notelines = notelines;
	}
	
	

}
