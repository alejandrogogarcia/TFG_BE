package es.udc.tfg.app.service.productservice;

import java.util.List;

import es.udc.tfg.app.model.product.Product;
import es.udc.tfg.app.util.exceptions.DuplicateInstanceException;
import es.udc.tfg.app.util.exceptions.InputValidationException;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;

public interface ProductService {

	public Product createProduct(ProductData productData)
			throws InstanceNotFoundException, InputValidationException, DuplicateInstanceException;

	public void updateProduct(Long reference, ProductData productData)
			throws InstanceNotFoundException, InputValidationException;

	public Product findProductByReference(Long reference) throws InstanceNotFoundException;

	public List<Product> findProductsByName(String name);

	public List<Product> findProductsByStockMin(Integer stock);

	public List<Product> findProductsByCategoryId(Long categoryId);

	public List<Product> findProductsByMainProductId(Long mainProductId);

	public List<Product> findProductsByCreatorId(Long creatorId);

	public List<Product> findAllProducts();

}
