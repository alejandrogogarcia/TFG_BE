package es.udc.tfg.app.model.product;

import java.util.List;

import es.udc.tfg.app.model.genericDao.GenericDao;

public interface ProductDao extends GenericDao<Product, Long> {
	
	//public Product findByReference(Long reference);
	
	public List<Product> findByName(String name);
	
	public List<Product> findByStockMin(Integer stock);
	
	public List<Product> findByCategoryId(Long categoryId);
	
	public List<Product> findByMainProductId(Long mainProductId);
	
	public List<Product> findByCreatorId(Long creatorId);
	
	public List<Product> findAll();

}
