package es.udc.tfg.app.service.categoryservice;

import java.util.List;

import es.udc.tfg.app.model.category.Category;
import es.udc.tfg.app.util.exceptions.InputValidationException;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;

public interface CategoryService {
	
	public Category createCategory(CategoryData categoryData) throws InputValidationException, InstanceNotFoundException;
	
	public void updateCategory(Long categoryId, CategoryData categoryData) throws InstanceNotFoundException, InputValidationException;
	
	public Category findCategoryById(Long categoryId) throws InstanceNotFoundException;
	
	public List<Category> findCategoryByName(String name);
	
	public List<Category> findAllCategories();

}
