package es.udc.tfg.app.model.category;

import java.util.List;

import es.udc.tfg.app.model.genericDao.GenericDao;

public interface CategoryDao extends GenericDao<Category, Long>{
	
	public List<Category> findByName(String name);
	
	public List<Category> findByCreatorId(Long creatorId);
	
	public List<Category> findAll();

}
