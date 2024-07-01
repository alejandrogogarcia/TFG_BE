package es.udc.tfg.app.service.productservice;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.tfg.app.model.category.Category;
import es.udc.tfg.app.model.category.CategoryDao;
import es.udc.tfg.app.model.product.Product;
import es.udc.tfg.app.model.product.ProductDao;
import es.udc.tfg.app.model.user.User;
import es.udc.tfg.app.model.user.UserDao;
import es.udc.tfg.app.util.exceptions.DuplicateInstanceException;
import es.udc.tfg.app.util.exceptions.InputValidationException;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.tfg.app.util.validator.ValidatorProperties;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private UserDao userDao;

	@Override
	public Product createProduct(ProductData productData)
			throws InstanceNotFoundException, InputValidationException, DuplicateInstanceException {

		String reference = productData.getReference().trim();
		ValidatorProperties.validateString(reference);
		try {
			productDao.findByReference(reference);
			throw new DuplicateInstanceException(productData.getReference(), Product.class.getName());

		} catch (InstanceNotFoundException e) {

			User creator = userDao.find(productData.getCreatorId());
			Category category = categoryDao.find(productData.getCategoryId());
			String name = productData.getName();
			ValidatorProperties.validateString(name);
			String description = productData.getDescription();
			ValidatorProperties.validateString(description);
			Integer stock = productData.getStock();
			ValidatorProperties.validatePositiveInteger(stock);
			Integer discount = productData.getDiscount();
			ValidatorProperties.validatePositiveInteger(discount);
			Float price = productData.getPrice();
			ValidatorProperties.validatePositiveFloat(price);

			Product product = new Product(reference, name, description, productData.getImage(), productData.getData(),
					price, discount, stock, category, creator);
			
			productDao.save(product);
			category.addProduct(product);

			return product;
		}

	}

	@Override
	public void updateProduct(Long id, ProductData productData)
			throws InstanceNotFoundException, InputValidationException, DuplicateInstanceException {

		Product product = productDao.find(id);

		String reference = productData.getReference();
		ValidatorProperties.validateString(reference);
		if (product.getReference() != reference) {
			try {
				productDao.findByReference(reference);
				throw new DuplicateInstanceException(productData.getReference(), Product.class.getName());
			} catch (InstanceNotFoundException e) {
				product.setReference(reference);
			}
		}

		String name = productData.getName();
		if (product.getName() != name) {
			ValidatorProperties.validateString(name);
			product.setName(name);
		}

		String description = productData.getDescription();
		if (product.getDescription() != description) {
			ValidatorProperties.validateString(description);
			product.setDescription(description);
		}

		// IMAGEN?

		// DATA?

		Float price = productData.getPrice();
		if (product.getPrice() != price) {
			ValidatorProperties.validatePositiveFloat(price);
			product.setPrice(price);
		}

		Integer discount = productData.getDiscount();
		if (product.getDiscount() != discount) {
			ValidatorProperties.validatePositiveInteger(discount);
			product.setDiscount(discount);
		}

		Integer stock = productData.getStock();
		if (product.getStock() != stock) {
			ValidatorProperties.validatePositiveInteger(stock);
			product.setStock(stock);
		}

		Long categoryId = productData.getCategoryId();
		Category oldCategory = product.getCategory();
		if (oldCategory.getId() != categoryId) {
			Category category = categoryDao.find(categoryId);
			product.setCategory(category);
			oldCategory.removeProduct(product);
			category.addProduct(product);
		}

		Long creatorId = productData.getCreatorId();
		if (product.getCreator().getId() != creatorId) {
			User creator = userDao.find(creatorId);
			product.setCreator(creator);
		}
	}

	@Override
	public Product findProductById(Long id) throws InstanceNotFoundException {
		return productDao.find(id);
	}

	@Override
	public Product findProductByReference(String reference) throws InstanceNotFoundException {
		return productDao.findByReference(reference);
	}

	@Override
	public List<Product> findProductByKeywords(String keywords) {
		return productDao.findByKeywords(keywords);
	}

	@Override
	public List<Product> findProductsByName(String name) {
		return productDao.findByName(name);
	}

	@Override
	public List<Product> findProductsByStockMin(Integer stock) {
		return productDao.findByStockMin(stock);
	}

	@Override
	public List<Product> findProductsByCategoryId(Long categoryId) throws InstanceNotFoundException {

		categoryDao.find(categoryId);
		return productDao.findByCategoryId(categoryId);
	}

	@Override
	public List<Product> findProductsByCreatorId(Long creatorId) throws InstanceNotFoundException {
		userDao.find(creatorId);
		return productDao.findByCreatorId(creatorId);
	}

	@Override
	public List<Product> findAllProducts() {
		return productDao.findAll();
	}

}
