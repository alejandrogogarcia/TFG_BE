package es.udc.tfg.app.service.productservice;

import java.util.List;

import es.udc.tfg.app.model.product.Product;
import es.udc.tfg.app.util.exceptions.DuplicateInstanceException;
import es.udc.tfg.app.util.exceptions.InputValidationException;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;

public interface ProductService {

	public Product createProduct(ProductData productData)
			throws InstanceNotFoundException, InputValidationException, DuplicateInstanceException;

	public void updateProduct(Long id, ProductData productData)
			throws InstanceNotFoundException, InputValidationException, DuplicateInstanceException;
	
	public Product findProductById(Long id) throws InstanceNotFoundException;

	public Product findProductByReference(String reference) throws InstanceNotFoundException;

	public List<Product> findProductByKeywords(String keywords);

	public List<Product> findProductsByName(String name);

	public List<Product> findProductsByStockMin(Integer stock);

	public List<Product> findProductsByCategoryId(Long categoryId) throws InstanceNotFoundException;

	public List<Product> findProductsByCreatorId(Long creatorId) throws InstanceNotFoundException;

	public List<Product> findAllProducts();

}
