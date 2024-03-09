package es.udc.tfg.app.test.service.productService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.udc.tfg.app.model.product.Product;
import es.udc.tfg.app.service.categoryservice.CategoryData;
import es.udc.tfg.app.service.categoryservice.CategoryService;
import es.udc.tfg.app.service.productservice.ProductData;
import es.udc.tfg.app.service.productservice.ProductService;
import es.udc.tfg.app.service.userservice.RegisterData;
import es.udc.tfg.app.service.userservice.UserService;
import es.udc.tfg.app.util.exceptions.DuplicateInstanceException;
import es.udc.tfg.app.util.exceptions.InputValidationException;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class productServiceTest {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	UserService userService;

	private final String VALID_EMAIL = "email3@udc.es";
	private final String VALID_DNI = "12345677A";
	private final String VALID_DNI_2 = "12345677A";
	private final String VALID_PASSWORD = "password";
	private final String VALID_FIRST_NAME = "firstName1";
	private final String VALID_LAST_NAME = "lastName1";
	private final String VALID_BIRTH_DATE = "01/01/1990";
	private final String VALID_LANGUAGE = "ESP";
	private final String VALID_USER_TYPE = "EMPLOYEE";
	private final String VALID_USER_IMAGE = "asfasdgasdg";

	private Long getValidUserId() throws InputValidationException, DuplicateInstanceException {
		System.out.println("Creado el usuario");
		return userService.registerUser(new RegisterData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI, VALID_EMAIL,
				VALID_PASSWORD, VALID_BIRTH_DATE, VALID_LANGUAGE, VALID_USER_TYPE, VALID_USER_IMAGE)).getId();
	}

	private Long getValidUserId2() throws InputValidationException, DuplicateInstanceException {
		return userService.registerUser(new RegisterData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI_2, VALID_EMAIL,
				VALID_PASSWORD, VALID_BIRTH_DATE, VALID_LANGUAGE, VALID_USER_TYPE, VALID_USER_IMAGE)).getId();
	}

	private final String VALID_NAME = "category 1";
	private final String VALID_NAME_2 = "category 2";
	private final String VALID_DESC = "category number 1";
	private final String VALID_DESC_2 = "category number 2";

	private Long getValidCategoryId(Long creatorId)
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {
		return categoryService.createCategory(new CategoryData(VALID_NAME, VALID_DESC, creatorId)).getId();

	}

	private Long getValidCategoryId2(Long creatorId)
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {
		return categoryService.createCategory(new CategoryData(VALID_NAME_2, VALID_DESC_2, creatorId)).getId();

	}

	private final String PRODUCT_REFERENCE_1 = "C12345A";
	private final String PRODUCT_REFERENCE_2 = "D13345B";
	private final String PRODUCT_NAME_1 = "Product 1";
	private final String PRODUCT_NAME_2 = "Product 2";
	private final String PRODUCT_DESC_1 = "Product number 1";
	private final String PRODUCT_DESC_2 = "Product number 2";
	private final Float PRODUCT_PRICE_1 = (float) 12.34;
	private final Float PRODUCT_PRICE_2 = (float) 22.44;
	private final Integer PRODUCT_DISCOUNT_1 = 0;
	private final Integer PRODUCT_DISCOUNT_2 = 5;
	private final Integer PRODUCT_STOCK_1 = 5;
	private final Integer PRODUCT_STOCK_2 = 20;

	private Product getValidProduct1(Long creatorId)
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		ProductData productData = new ProductData(PRODUCT_REFERENCE_1, PRODUCT_NAME_1, PRODUCT_DESC_1, "", "",
				PRODUCT_PRICE_1, PRODUCT_DISCOUNT_1, PRODUCT_STOCK_1, getValidCategoryId(creatorId), creatorId);

		return productService.createProduct(productData);
	}

	private Product getValidProduct2(Long creatorId)
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		ProductData productData = new ProductData(PRODUCT_REFERENCE_2, PRODUCT_NAME_2, PRODUCT_DESC_2, "", "",
				PRODUCT_PRICE_2, PRODUCT_DISCOUNT_2, PRODUCT_STOCK_2, getValidCategoryId2(creatorId), creatorId);

		return productService.createProduct(productData);
	}

	@Test
	public void testcreateAdnFindById()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		Product product = getValidProduct1(creatorId);
		getValidProduct2(creatorId);
		Product product2 = productService.findProductById(product.getId());
		assertEquals(product.getId(), product2.getId());

	}

	

	@Test(expected = InstanceNotFoundException.class)
	public void testFindByIdNonExistenProduct()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		productService.findProductById((long) 1);

	}

	@Test
	public void testcreateAdnFindByReference()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		Product product = getValidProduct1(creatorId);
		getValidProduct2(creatorId);
		Product product2 = productService.findProductByReference(product.getReference());
		assertEquals(product.getReference(), product2.getReference());

	}
	
	@Test(expected = DuplicateInstanceException.class)
	public void testcreateDuplicate()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		getValidProduct1(creatorId);
		getValidProduct1(creatorId);

	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testFindByReferenceNonExistenProduct()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		productService.findProductByReference("error");

	}


//	public void testCreateAndFindByIdValidCategory()
//			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {
//		Long creatorId = getValidUserId();
//		Product product = getValidProduct1(creatorId);
//		Product product2 = productService.findProductByReference(PRODUCT_REFERENCE_1);
//		assertEquals(product.getReference(), product2.getReference());
//
//	}
}
