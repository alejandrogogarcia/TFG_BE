package es.udc.tfg.app.model.noteline;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.udc.tfg.app.model.note.Note;
import es.udc.tfg.app.model.product.Product;

@Entity
@Table(name = "notelines")
@IdClass(NotelinePK.class)
public class Noteline {

	@Id
	@Column(name = "noteline_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long notelineId;

	@Id
	@Column(name = "note_id")
	private Long noteId;

	private Float price;

	private Integer amount;

	private Integer discount;

	private String comment;

	// ....... Relaciones N a 1 ......./

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "note_id" , insertable = false, updatable = false)
	private Note note;

	// ....... Constructores ......./

	public Noteline() {
	}

	public Noteline(Float price, Integer amount, Integer discount, String comment, Product product, Note note) {
		this.price = price;
		this.amount = amount;
		this.discount = discount;
		this.comment = comment;
		this.product = product;
		this.note = note;
	}

	// ....... Getters & Setters ......./

	public Long getNotelineId() {
		return notelineId;
	}

	public void setNotelineId(Long notelineId) {
		this.notelineId = notelineId;
	}

	public Long getNoteId() {
		return noteId;
	}

	public void setNoteId(Long noteId) {
		this.noteId = noteId;
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
		return (price * amount) * (1 - discount / 100);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
		this.price = null;
		this.amount = null;
		this.discount = null;
		this.product = null;
	}

	// ....... Relaciones N a 1 ......./

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
