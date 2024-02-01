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
	ProductDao productDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	UserDao userDao;

	@Override
	public Product createProduct(ProductData productData)
			throws InstanceNotFoundException, InputValidationException, DuplicateInstanceException {
			
			Long reference = productData.getReference();
		try {
			productDao.find(reference);
			throw new DuplicateInstanceException(productData.getReference(), Product.class.getName());

		} catch (InstanceNotFoundException e) {
			
			User creator = userDao.find(productData.getCreatorId());
			Category category = categoryDao.find(productData.getCategoryId());
			Long mainProductReference = productData.getMainProductReference();
			Product mainProduct = null;
			if (mainProductReference != null) {
				mainProduct= productDao.find(mainProductReference);
			}
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

			Product product = new Product(reference, name, description, productData.getImage(), productData.getData(), price, discount, stock, category, mainProduct, creator);
			productDao.save(product);
			
			return product;
		}

	}

	@Override
	public void updateProduct(Long productReference, ProductData productData)
			throws InstanceNotFoundException, InputValidationException {
		// TODO Auto-generated method stub

	}

	@Override
	public Product findProductByReference(Long reference) throws InstanceNotFoundException {
		return productDao.find(reference);
	}

	@Override
	public List<Product> findProductsByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findProductsByStockMin(Integer stock) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findProductsByCategoryId(Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findProductsByMainProductId(Long mainProductId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findProductsByCreatorId(Long creatorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

}
