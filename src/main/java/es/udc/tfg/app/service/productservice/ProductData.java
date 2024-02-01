package es.udc.tfg.app.service.productservice;

public class ProductData {

	private Long reference;

	private String name;

	private String description;

	private String image;

	private String data;

	private Float price;

	private Integer discount;

	private Integer stock;

	private Long categoryId;

	private Long mainProductReference;

	private Long creatorId;
	
	public ProductData() {
	}
	
	public ProductData(Long reference, String name, String description, String image, String data, Float price,
			Integer discount, Integer stock, Long categoryId, Long mainProductReference, Long creatorId) {
		this.reference = reference;
		this.name = name;
		this.description = description;
		this.image = image;
		this.data = data;
		this.price = price;
		this.discount = discount;
		this.stock = stock;
		this.categoryId = categoryId;
		this.mainProductReference = mainProductReference;
		this.creatorId = creatorId;
	}

	

	public Long getReference() {
		return reference;
	}

	public void setReference(Long reference) {
		this.reference = reference;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getMainProductReference() {
		return mainProductReference;
	}

	public void setMainProductReference(Long mainProductReference) {
		this.mainProductReference = mainProductReference;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	
	

}
