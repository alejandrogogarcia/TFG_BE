package es.udc.tfg.app.service.categoryservice;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.tfg.app.model.category.Category;
import es.udc.tfg.app.model.category.CategoryDao;
import es.udc.tfg.app.model.user.User;
import es.udc.tfg.app.model.user.UserDao;
import es.udc.tfg.app.util.exceptions.InputValidationException;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.tfg.app.util.validator.ValidatorProperties;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private UserDao userDao;

	@Override
	public Category createCategory(CategoryData categoryData)
			throws InputValidationException, InstanceNotFoundException {
		User creator = userDao.find(categoryData.getCreatorId());
		ValidatorProperties.validateString(categoryData.getName());
		Category category = new Category(categoryData.getName(), categoryData.getDescription(), creator);
		categoryDao.save(category);
		creator.addCategory(category);
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
	public List<Category> findCategoryByName(String name) {
		return categoryDao.findByName(name);
	}

	@Override
	public List<Category> findAllCategories() {
		return categoryDao.findAll();
	}

}
