package es.udc.tfg.fapptura.service.categoryservice;

import java.util.List;

import es.udc.tfg.fapptura.model.category.Category;
import es.udc.tfg.fapptura.util.exceptions.InputValidationException;
import es.udc.tfg.fapptura.util.exceptions.InstanceNotFoundException;

public interface CategoryService {
	
	public Category createCategory(CategoryData categoryData, Long creatorId) throws InputValidationException, InstanceNotFoundException;
	
	public void updateCategory(Long categoryId, CategoryData categoryData) throws InstanceNotFoundException, InputValidationException;
	
	public Category findCategoryById(Long categoryId) throws InstanceNotFoundException;
	
	public List<Category> findCategoryByName(String name) throws InputValidationException;
	
	public List<Category> findAllCategories();

}
