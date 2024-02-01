package es.udc.tfg.app.model.category;

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

import es.udc.tfg.app.model.product.Product;
import es.udc.tfg.app.model.user.User;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	private Calendar createDate;

	// ....... Relaciones N a 1 ......./

	@ManyToOne()
	@JoinColumn(name = "creator_id")
	private User creator;

	// ....... Relaciones 1 a N ......./

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Product> products = new ArrayList<>();

	// ....... Constructores ......./

	public Category() {
	}

	public Category(String name, String description, User creator) {
		this.name = name;
		this.description = description;
		this.createDate = Calendar.getInstance();
		this.creator = creator;
	}

	// ....... Getters & Setters ......./
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Calendar getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}
	
	// ....... Relaciones N a 1 ......./
	
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	// ....... Relaciones 1 a N ......./
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void addProduct(Product product) {
		products.add(product);
		
	}

	public void removeProduct(Product product) {
		products.remove(product);
		
		
	}
	
	

}
