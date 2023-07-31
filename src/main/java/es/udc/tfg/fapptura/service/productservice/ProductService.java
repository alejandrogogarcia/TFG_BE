package es.udc.tfg.fapptura.service.productservice;

import java.util.List;

import es.udc.tfg.fapptura.model.product.Product;
import es.udc.tfg.fapptura.util.exceptions.InputValidationException;
import es.udc.tfg.fapptura.util.exceptions.InstanceNotFoundException;

public interface ProductService {

	public Product createProduct(ProductData productData, Long creatorId)
			throws InstanceNotFoundException, InputValidationException;

	public void updateProduct(Long productId, ProductData productData)
			throws InstanceNotFoundException, InputValidationException;
	
	public Product findProductById(Long productId) throws InstanceNotFoundException;
	
	public List<Product> findProductsByReference(String reference);
	
	public List<Product> findProductsByName(String name);
	
	public List<Product> findProductsByStockMin(Integer stock);
	
	public List<Product> findProductsByCategoryId(Long categoryId);
	
	public List<Product> findProductsByMainProductId(Long mainProductId);
	
	public List<Product> findProductsByCreatorId(Long creatorId);
	
	public List<Product> findAllProducts();

}
