package es.udc.tfg.fapptura.model.noteline;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.udc.tfg.fapptura.model.note.Note;
import es.udc.tfg.fapptura.model.product.Product;

@Entity
@Table(name = "notelines")
public class Noteline {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Float price;
	
	private Integer amount;
	
	private Integer discount;
	
	private Float subtotal;
	
	private String comment;

	// ....... Relaciones N a 1  ......./
	
	@ManyToOne()
    @JoinColumn(name = "product_id")	
	private Product product;

	@ManyToOne()
    @JoinColumn(name = "note_id")
	private Note note;

	// ....... Constructores ......./
	
	public Noteline() {
	}

	public Noteline(Float price, Integer amount, Integer discount, Float subtotal, String comment, Product product,
			Note note) {
		this.price = price;
		this.amount = amount;
		this.discount = discount;
		this.subtotal = subtotal;
		this.comment = comment;
		this.product = product;
		this.note = note;
	}

	// ....... Getters & Setters ......./
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Float subtotal) {
		this.subtotal = subtotal;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	// ....... Relaciones N a 1  ......./
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}
	
	
}
