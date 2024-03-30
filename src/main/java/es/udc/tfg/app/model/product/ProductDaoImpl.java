package es.udc.tfg.app.model.product;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg.app.model.genericDao.GenericDaoImpl;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;

@Repository
@Transactional
public class ProductDaoImpl extends GenericDaoImpl<Product, Long> implements ProductDao {

	@Override
	public Product findByReference(String reference) throws InstanceNotFoundException {
		Product product = null;
		try {
			Query query = this.em.createQuery("SELECT p FROM Product p WHERE p.reference = :reference")
					.setParameter("reference", reference);
			product = (Product) query.getSingleResult();
		} catch (Exception e) {
			throw new InstanceNotFoundException(reference, Product.class.getName());
		}
		return product;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> findByKeywords(String keywords) {
		return (List<Product>) this.em.createQuery(
				"SELECT p FROM Product p WHERE p.reference like :keywords OR p.name like :keywords OR p.description like :keywords")
				.setParameter("keywords", "%" + keywords + "%").getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> findByName(String name) {
		return (List<Product>) this.em.createQuery("SELECT p FROM Product p WHERE p.name like :name")
				.setParameter("name", "%" + name + "%").getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> findByStockMin(Integer stock) {
		return (List<Product>) this.em.createQuery("SELECT p FROM Product p WHERE p.stock <= :stock")
				.setParameter("stock", stock).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> findByCategoryId(Long categoryId) {
		return (List<Product>) this.em.createQuery("SELECT p FROM Product p WHERE p.category.id = :categoryId")
				.setParameter("categoryId", categoryId).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> findByCreatorId(Long creatorId) {
		return (List<Product>) this.em.createQuery("SELECT p FROM Product p WHERE p.creator.id = :creatorId")
				.setParameter("creatorId", creatorId).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> findAll() {
		return (List<Product>) this.em.createQuery("SELECT p FROM Product p ORDER BY p.id").getResultList();
	}

}
