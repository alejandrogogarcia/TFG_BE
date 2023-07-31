package es.udc.tfg.fapptura.model.product;

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

import es.udc.tfg.fapptura.model.category.Category;
import es.udc.tfg.fapptura.model.noteline.Noteline;
import es.udc.tfg.fapptura.model.user.User;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String reference;
	
	private String name;
	
	private String description;
	
	private String image;
	
	private String data;
	
	private Float price;
	
	private Integer discount;
	
	private Integer stock;
	
	private Calendar createDate;
	
	// ....... Relaciones N a 1  ......./
	
	@ManyToOne()
    @JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne()
    @JoinColumn(name = "mainProduct_id")
	private Product mainProduct;
	
	@ManyToOne()
    @JoinColumn(name = "creator_id")
	private User creator;
	
	// ....... Relaciones 1 a N  ......./

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Noteline> notelines = new ArrayList<>();
	
	@OneToMany(mappedBy = "mainProduct", cascade = CascadeType.ALL)
	private List<Product> subproducts = new ArrayList<>();

	// ....... Constructores ......./
	
	public Product() {
	}

	public Product(String reference, String name, String description, String image, String data, Float price, Integer discount,
			Integer stock, Calendar createDate, Category category, Product mainProduct, User creator) {
		this.reference = reference;
		this.name = name;
		this.description = description;
		this.image = image;
		this.data = data;
		this.price = price;
		this.discount = discount;
		this.stock = stock;
		this.createDate = createDate;
		this.category = category;
		this.mainProduct = mainProduct;
		this.creator = creator;
	}
	
	// ....... Getters & Setters ......./

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
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

	public Calendar getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}
	
	// ....... Relaciones N a 1  ......./

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Product getMainProduct() {
		return mainProduct;
	}

	public void setMainProduct(Product mainProduct) {
		this.mainProduct = mainProduct;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	// ....... Relaciones 1 a N  ......./

	public List<Noteline> getNotelines() {
		return notelines;
	}

	public void setNotelines(List<Noteline> notelines) {
		this.notelines = notelines;
	}

	public List<Product> getSubproducts() {
		return subproducts;
	}

	public void setSubproducts(List<Product> subproducts) {
		this.subproducts = subproducts;
	}
	
	
	
}
