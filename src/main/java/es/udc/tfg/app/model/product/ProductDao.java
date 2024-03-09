package es.udc.tfg.app.model.product;

import java.util.List;

import es.udc.tfg.app.model.genericDao.GenericDao;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;

public interface ProductDao extends GenericDao<Product, Long> {
	
	public Product findByReference(String reference) throws InstanceNotFoundException;
	
	public List<Product> findByKeywords(String keywords);
	
	public List<Product> findByName(String name);
	
	public List<Product> findByStockMin(Integer stock);
	
	public List<Product> findByCategoryId(Long categoryId);
		
	public List<Product> findByCreatorId(Long creatorId);
	
	public List<Product> findAll();

}
