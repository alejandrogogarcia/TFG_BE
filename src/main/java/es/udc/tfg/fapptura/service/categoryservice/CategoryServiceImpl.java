package es.udc.tfg.fapptura.service.categoryservice;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import es.udc.tfg.fapptura.model.category.Category;
import es.udc.tfg.fapptura.model.category.CategoryDao;
import es.udc.tfg.fapptura.model.user.User;
import es.udc.tfg.fapptura.model.user.UserDao;
import es.udc.tfg.fapptura.util.exceptions.InputValidationException;
import es.udc.tfg.fapptura.util.exceptions.InstanceNotFoundException;
import es.udc.tfg.fapptura.util.validator.ValidatorProperties;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryDao categoryDao;

	private UserDao userDao;

	@Override
	public Category createCategory(CategoryData categoryData, Long creatorId)
			throws InputValidationException, InstanceNotFoundException {
		User creator = userDao.find(creatorId);
		ValidatorProperties.validateString(categoryData.getName());
		Category category = new Category(categoryData.getName(), categoryData.getDescription(), creator);
		categoryDao.save(category);
		return category;
	}

	@Override
	public void updateCategory(Long categoryId, CategoryData categoryData)
			throws InstanceNotFoundException, InputValidationException {

		Category category = categoryDao.find(categoryId);
		ValidatorProperties.validateString(categoryData.getName());

		category.setName(categoryData.getName());
		category.setDescription(categoryData.getDescription());
	}

	@Override
	public Category findCategoryById(Long categoryId) throws InstanceNotFoundException {
		return categoryDao.find(categoryId);
	}

	@Override
	public List<Category> findCategoryByName(String name) throws InputValidationException {
		return categoryDao.findByName(name);
	}

	@Override
	public List<Category> findAllCategories() {
		return categoryDao.findAll();
	}

}
