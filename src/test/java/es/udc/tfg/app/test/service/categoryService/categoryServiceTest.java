package es.udc.tfg.app.test.service.categoryService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.udc.tfg.app.model.category.Category;
import es.udc.tfg.app.service.categoryservice.CategoryData;
import es.udc.tfg.app.service.categoryservice.CategoryService;
import es.udc.tfg.app.service.userservice.RegisterData;
import es.udc.tfg.app.service.userservice.UserService;
import es.udc.tfg.app.util.exceptions.DuplicateInstanceException;
import es.udc.tfg.app.util.exceptions.InputValidationException;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class categoryServiceTest {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CategoryService categoryService;

	private final String VALID_EMAIL = "email3@udc.es";
	private final String VALID_DNI = "12345677A";
	private final String VALID_PASSWORD = "password";
	private final String VALID_FIRST_NAME = "firstName1";
	private final String VALID_LAST_NAME = "lastName1";
	private final String VALID_BIRTH_DATE = "01/01/1990";
	private final String VALID_LANGUAGE = "ESP";
	private final String VALID_USER_TYPE = "EMPLOYEE";
	private final String VALID_USER_IMAGE = "asfasdgasdg";
	
	private Long getValidUserId() throws InputValidationException, DuplicateInstanceException {
		return userService.registerUser(new RegisterData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI, VALID_EMAIL, VALID_PASSWORD,
				VALID_BIRTH_DATE, VALID_LANGUAGE, VALID_USER_TYPE, VALID_USER_IMAGE)).getId();
	}
	
	private final String VALID_NAME = "category 1";
	private final String VALID_NAME_2 = "category 2";
	private final String VALID_DESC = "category number 1";
	private final String VALID_DESC_2 = "category number 2";
	
	private CategoryData getValidCategoryData(Long creatorId) throws InputValidationException, DuplicateInstanceException {
		return new CategoryData(VALID_NAME, VALID_DESC, creatorId);
	}
	private CategoryData getValidCategoryData2(Long creatorId) throws InputValidationException, DuplicateInstanceException {
		return new CategoryData(VALID_NAME_2, VALID_DESC_2, creatorId);
	}
	
	private CategoryData getValidCategoryDataNoUser() {
		return new CategoryData(VALID_NAME_2, VALID_DESC_2, null);
	}

	@Test
	public void testCreateAndFindByIdValidCategory() throws InputValidationException, InstanceNotFoundException, DuplicateInstanceException {
		
		Category category = categoryService.createCategory(getValidCategoryData(getValidUserId()));
		Category categoryFound = categoryService.findCategoryById(category.getId());
		
		assertEquals(category.getId(), categoryFound.getId());
		assertEquals(category.getName(), categoryFound.getName());
		assertEquals(category.getDescription(), categoryFound.getDescription());
		assertEquals(category.getCreator().getId(), categoryFound.getCreator().getId());
		
	}
	
	@Test(expected = InputValidationException.class)
	public void testCreateCategoryInvalidName() throws InputValidationException, InstanceNotFoundException, DuplicateInstanceException {
		
		categoryService.createCategory(new CategoryData("", VALID_DESC, getValidUserId()));
		
	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testCreateCategoryNoNExistCreator() throws InputValidationException, InstanceNotFoundException, DuplicateInstanceException {
		
		categoryService.createCategory(new CategoryData(VALID_NAME, VALID_DESC, (long) 1));
		
	}
	
	
	@Test
	public void testUpdateCategory() throws InputValidationException, InstanceNotFoundException, DuplicateInstanceException {
		
		Category category = categoryService.createCategory(getValidCategoryData(getValidUserId()));
		categoryService.updateCategory(category.getId(), getValidCategoryDataNoUser());
		Category categoryFound = categoryService.findCategoryById(category.getId());
		
		assertEquals(category.getId(), categoryFound.getId());
		assertEquals(VALID_NAME_2, categoryFound.getName());
		assertEquals(VALID_DESC_2, categoryFound.getDescription());
		assertEquals(category.getCreator().getId(), categoryFound.getCreator().getId());
		
	}
	
	@Test(expected = InputValidationException.class)
	public void testUpdateCategoryInvalidName() throws InputValidationException, InstanceNotFoundException, DuplicateInstanceException {
		
		Category category = categoryService.createCategory(getValidCategoryData(getValidUserId()));
		categoryService.updateCategory(category.getId(), new CategoryData("", VALID_DESC, null));
		
	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testUpdateNonExistCategory() throws InputValidationException, InstanceNotFoundException {
		
		categoryService.updateCategory((long)0, new CategoryData(VALID_NAME, VALID_DESC, null));
		
	}
	
	@Test
	public void testFindCategoryByName() throws InputValidationException, InstanceNotFoundException, DuplicateInstanceException {
		
		Long creatorId = getValidUserId();
		Category category1 = categoryService.createCategory(getValidCategoryData(creatorId));
		Category category2 = categoryService.createCategory(getValidCategoryData2(creatorId));
		
		List<Category> categoryList = categoryService.findCategoryByName("Cat");
		assertEquals(categoryList.size(), 2);
		assertEquals(categoryList.get(0).getId(), category1.getId());
		assertEquals(categoryList.get(1).getId(), category2.getId());
		
		categoryList = categoryService.findCategoryByName("1");
		assertEquals(categoryList.size(), 1);
		assertEquals(categoryList.get(0).getId(), category1.getId());
		
		categoryList = categoryService.findCategoryByName("2");
		assertEquals(categoryList.size(), 1);
		assertEquals(categoryList.get(0).getId(), category2.getId());
		
		categoryList = categoryService.findCategoryByName("");
		assertEquals(categoryList.size(), 2);
		
		
	}
	
	@Test
	public void testFindAllCategory() throws InputValidationException, InstanceNotFoundException, DuplicateInstanceException {
		
		Long creatorId = getValidUserId();
		Category category1 = categoryService.createCategory(getValidCategoryData(creatorId));
		
		List<Category> categoryList = categoryService.findAllCategories();
		assertEquals(categoryList.size(), 1);
		assertEquals(categoryList.get(0).getId(), category1.getId());
		
		Category category2 = categoryService.createCategory(getValidCategoryData2(creatorId));
		
		categoryList = categoryService.findAllCategories();
		assertEquals(categoryList.size(), 2);
		assertEquals(categoryList.get(0).getId(), category1.getId());
		assertEquals(categoryList.get(1).getId(), category2.getId());
		
		
		
		categoryList = categoryService.findCategoryByName("2");
		assertEquals(categoryList.size(), 1);
		assertEquals(categoryList.get(0).getId(), category2.getId());
		
		categoryList = categoryService.findCategoryByName("");
		assertEquals(categoryList.size(), 2);
		
		
	}
	
	
}
