package es.udc.tfg.fapptura.model.category;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg.fapptura.model.genericDao.GenericDaoImpl;

@Repository
@Transactional
public class CategoryDaoImpl extends GenericDaoImpl<Category, Long> implements CategoryDao{

	@Override
	@SuppressWarnings("unchecked")
	public List<Category> findByName(String name) {
		return (List<Category>) this.em.createQuery("SELECT c FROM Category c WHERE c.name like :name")
				.setParameter("name", "%" +name + "%").getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Category> findByCreatorId(Long creatorId) {
		return (List<Category>) this.em.createQuery("SELECT c FROM Category c WHERE c.creator.id like :creatorId")
				.setParameter("creatorId", creatorId).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Category> findAll() {
		return (List<Category>) this.em.createQuery("SELECT c FROM Category c ORDER BY c.id")
				.getResultList();
	}

}
