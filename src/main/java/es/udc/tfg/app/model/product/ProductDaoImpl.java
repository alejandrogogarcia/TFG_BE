package es.udc.tfg.fapptura.model.product;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg.fapptura.model.genericDao.GenericDaoImpl;

@Repository
@Transactional
public class ProductDaoImpl extends GenericDaoImpl<Product, Long> implements ProductDao{

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> findByReference(String reference) {
		return (List<Product>) this.em.createQuery("SELECT p FROM Product p WHERE p.reference like :reference")
				.setParameter("reference", "%" + reference + "%").getResultList();
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
		return (List<Product>) this.em.createQuery("SELECT p FROM Product p WHERE p.category.id like :categoryId")
				.setParameter("categoryId", categoryId).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> findByMainProductId(Long mainProductId) {
		return (List<Product>) this.em.createQuery("SELECT p FROM Product p WHERE p.mainProduct.id like :mainProductId")
				.setParameter("mainProductId", mainProductId).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> findByCreatorId(Long creatorId) {
		return (List<Product>) this.em.createQuery("SELECT p FROM Product p WHERE p.creator.id like :creatorId")
				.setParameter("creatorId", creatorId).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> findAll() {
		return (List<Product>) this.em.createQuery("SELECT p FROM Product p ORDER BY p.id");
	}

}
