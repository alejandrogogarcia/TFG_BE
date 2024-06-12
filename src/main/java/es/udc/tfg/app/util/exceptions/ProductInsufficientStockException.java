package es.udc.tfg.app.util.exceptions;

@SuppressWarnings("serial")
public class ProductInsufficientStockException extends Exception {

	private Long producId;
	private int needed;
	private int stock;

	public ProductInsufficientStockException(Long producId, int needed, int stock) {
		super("Product insufficient stock (productId = '" + producId + ", Needed Stock = " + needed
				+ ", Existing stock = " + stock + "')");
		this.producId = producId;
		this.needed = needed;
		this.stock = stock;
	}

	public Long getProducId() {
		return producId;
	}

	public int getNeeded() {
		return needed;
	}

	public int getStock() {
		return stock;
	}

}
