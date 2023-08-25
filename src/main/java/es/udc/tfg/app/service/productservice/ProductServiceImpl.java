package es.udc.tfg.fapptura.service.productservice;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import es.udc.tfg.fapptura.model.product.Product;
import es.udc.tfg.fapptura.util.exceptions.InputValidationException;
import es.udc.tfg.fapptura.util.exceptions.InstanceNotFoundException;

@Transactional
@Service
public class ProductServiceImpl implements ProductService{

	@Override
	public Product createProduct(ProductData productData, Long creatorId)
			throws InstanceNotFoundException, InputValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProduct(Long productId, ProductData productData)
			throws InstanceNotFoundException, InputValidationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product findProductById(Long productId) throws InstanceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findProductsByReference(String reference) {
		// TODO Auto-generated method stub
		return null;
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
