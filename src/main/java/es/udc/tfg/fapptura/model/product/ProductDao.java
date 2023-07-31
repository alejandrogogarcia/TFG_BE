package es.udc.tfg.fapptura.model.product;

import java.util.List;

import es.udc.tfg.fapptura.model.genericDao.GenericDao;

public interface ProductDao extends GenericDao<Product, Long> {
	
	public List<Product> findByReference(String reference);
	
	public List<Product> findByName(String name);
	
	public List<Product> findByStockMin(Integer stock);
	
	public List<Product> findByCategoryId(Long categoryId);
	
	public List<Product> findByMainProductId(Long mainProductId);
	
	public List<Product> findByCreatorId(Long creatorId);
	
	public List<Product> findAll();

}
