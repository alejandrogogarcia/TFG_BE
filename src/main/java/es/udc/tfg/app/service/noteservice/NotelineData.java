package es.udc.tfg.app.service.noteservice;

public class NotelineData {

	private Integer amount;

	private Integer discount;

	private String comment;

	private String reference;

	public NotelineData() {
	}

	public NotelineData(Integer amount, Integer discount, String comment, String reference) {
		this.amount = amount;
		this.discount = discount;
		this.comment = comment;
		this.reference = reference;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

}
