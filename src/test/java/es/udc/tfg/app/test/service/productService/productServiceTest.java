package es.udc.tfg.app.test.service.productService;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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
	private final String VALID_EMAIL_2 = "email45@udc.es";
	private final String VALID_DNI = "12345677A";
	private final String VALID_DNI_2 = "12345678A";
	private final String VALID_PASSWORD = "password";
	private final String VALID_PASSWORD_2 = "password2";
	private final String VALID_FIRST_NAME = "firstName1";
	private final String VALID_FIRST_NAME_2 = "firstName2";
	private final String VALID_LAST_NAME = "lastName1";
	private final String VALID_LAST_NAME_2 = "lastName2";
	private final String VALID_BIRTH_DATE = "01/01/1990";
	private final String VALID_BIRTH_DATE_2 = "03/12/1994";
	private final String VALID_LANGUAGE = "ESP";
	private final String VALID_LANGUAGE_2 = "GAL";
	private final String VALID_USER_TYPE = "EMPLOYEE";
	private final String VALID_USER_TYPE_2 = "ADMIN";
	private final String VALID_USER_IMAGE = "asfasdgasdg";
	private final String VALID_USER_IMAGE_2 = "ertfbdfhf";

	private Long getValidUserId() throws InputValidationException, DuplicateInstanceException {
		return userService.registerUser(new RegisterData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI, VALID_EMAIL,
				VALID_PASSWORD, VALID_BIRTH_DATE, VALID_LANGUAGE, VALID_USER_TYPE, VALID_USER_IMAGE)).getId();
	}

	private Long getValidUserId2() throws InputValidationException, DuplicateInstanceException {
		return userService
				.registerUser(new RegisterData(VALID_FIRST_NAME_2, VALID_LAST_NAME_2, VALID_DNI_2, VALID_EMAIL_2,
						VALID_PASSWORD_2, VALID_BIRTH_DATE_2, VALID_LANGUAGE_2, VALID_USER_TYPE_2, VALID_USER_IMAGE_2))
				.getId();
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

	private final String VALID_PRODUCT_REFERENCE_1 = "C12345A";
	private final String VALID_PRODUCT_REFERENCE_2 = "D53345B";
	private final String INVALID_PRODUCT_REFERENCE = "";
	private final String VALID_PRODUCT_NAME_1 = "Product 1";
	private final String VALID_PRODUCT_NAME_2 = "Product 2";
	private final String INVALID_PRODUCT_NAME = "";
	private final String VALID_PRODUCT_DESC_1 = "Product number 1";
	private final String VALID_PRODUCT_DESC_2 = "Product number 2";
	private final String INVALID_PRODUCT_DESC = "";
	private final Float VALID_PRODUCT_PRICE_1 = (float) 12.34;
	private final Float VALID_PRODUCT_PRICE_2 = (float) 22.44;
	private final Float INVALID_PRODUCT_PRICE = (float) -2.03;
	private final Integer VALID_PRODUCT_DISCOUNT_1 = 0;
	private final Integer VALID_PRODUCT_DISCOUNT_2 = 5;
	private final Integer INVALID_PRODUCT_DISCOUNT = -5;
	private final Integer VALID_PRODUCT_STOCK_1 = 5;
	private final Integer VALID_PRODUCT_STOCK_2 = 20;
	private final Integer INVALID_PRODUCT_STOCK = -20;

	private Product getValidProduct1(Long creatorId)
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		ProductData productData = new ProductData(VALID_PRODUCT_REFERENCE_1, VALID_PRODUCT_NAME_1, VALID_PRODUCT_DESC_1,
				"", "", VALID_PRODUCT_PRICE_1, VALID_PRODUCT_DISCOUNT_1, VALID_PRODUCT_STOCK_1,
				getValidCategoryId(creatorId), creatorId);

		return productService.createProduct(productData);
	}

	private Product getValidProduct2(Long creatorId)
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		ProductData productData = new ProductData(VALID_PRODUCT_REFERENCE_2, VALID_PRODUCT_NAME_2, VALID_PRODUCT_DESC_2,
				"", "", VALID_PRODUCT_PRICE_2, VALID_PRODUCT_DISCOUNT_2, VALID_PRODUCT_STOCK_2,
				getValidCategoryId2(creatorId), creatorId);

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

	@Test(expected = InputValidationException.class)
	public void testCreateProductInvalidReference()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		ProductData productData = new ProductData(INVALID_PRODUCT_REFERENCE, VALID_PRODUCT_NAME_2, VALID_PRODUCT_DESC_2,
				"", "", VALID_PRODUCT_PRICE_2, VALID_PRODUCT_DISCOUNT_2, VALID_PRODUCT_STOCK_2,
				getValidCategoryId2(creatorId), creatorId);

		productService.createProduct(productData);
	}

	@Test(expected = InputValidationException.class)
	public void testCreateProductInvalidName()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		ProductData productData = new ProductData(VALID_PRODUCT_REFERENCE_1, INVALID_PRODUCT_NAME, VALID_PRODUCT_DESC_2,
				"", "", VALID_PRODUCT_PRICE_2, VALID_PRODUCT_DISCOUNT_2, VALID_PRODUCT_STOCK_2,
				getValidCategoryId2(creatorId), creatorId);

		productService.createProduct(productData);
	}

	@Test(expected = InputValidationException.class)
	public void testCreateProductInvalidDescription()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		ProductData productData = new ProductData(VALID_PRODUCT_REFERENCE_1, VALID_PRODUCT_NAME_1, INVALID_PRODUCT_DESC,
				"", "", VALID_PRODUCT_PRICE_2, VALID_PRODUCT_DISCOUNT_2, VALID_PRODUCT_STOCK_2,
				getValidCategoryId2(creatorId), creatorId);

		productService.createProduct(productData);
	}

	@Test(expected = InputValidationException.class)
	public void testCreateProductInvalidPrice()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		ProductData productData = new ProductData(VALID_PRODUCT_REFERENCE_1, VALID_PRODUCT_NAME_1, VALID_PRODUCT_DESC_1,
				"", "", INVALID_PRODUCT_PRICE, VALID_PRODUCT_DISCOUNT_2, VALID_PRODUCT_STOCK_2,
				getValidCategoryId2(creatorId), creatorId);

		productService.createProduct(productData);
	}

	@Test(expected = InputValidationException.class)
	public void testCreateProductInvalidDiscount()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		ProductData productData = new ProductData(VALID_PRODUCT_REFERENCE_1, VALID_PRODUCT_NAME_1, VALID_PRODUCT_DESC_1,
				"", "", VALID_PRODUCT_PRICE_1, INVALID_PRODUCT_DISCOUNT, VALID_PRODUCT_STOCK_2,
				getValidCategoryId2(creatorId), creatorId);

		productService.createProduct(productData);
	}

	@Test(expected = InputValidationException.class)
	public void testCreateProductInvalidStock()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		ProductData productData = new ProductData(VALID_PRODUCT_REFERENCE_1, VALID_PRODUCT_NAME_1, VALID_PRODUCT_DESC_1,
				"", "", VALID_PRODUCT_PRICE_1, INVALID_PRODUCT_DISCOUNT, INVALID_PRODUCT_STOCK,
				getValidCategoryId2(creatorId), creatorId);

		productService.createProduct(productData);
	}

	@Test
	public void testUpdateProduct()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		Product product1 = getValidProduct1(creatorId);

		assertEquals(product1.getReference(), VALID_PRODUCT_REFERENCE_1);
		assertEquals(product1.getName(), VALID_PRODUCT_NAME_1);
		assertEquals(product1.getDescription(), VALID_PRODUCT_DESC_1);
		assertEquals(product1.getPrice(), VALID_PRODUCT_PRICE_1);
		assertEquals(product1.getDiscount(), VALID_PRODUCT_DISCOUNT_1);
		assertEquals(product1.getStock(), VALID_PRODUCT_STOCK_1);

		ProductData productData = new ProductData(VALID_PRODUCT_REFERENCE_2, VALID_PRODUCT_NAME_2, VALID_PRODUCT_DESC_2,
				"", "", VALID_PRODUCT_PRICE_2, VALID_PRODUCT_DISCOUNT_2, VALID_PRODUCT_STOCK_2,
				getValidCategoryId2(creatorId), creatorId);
		productService.updateProduct(product1.getId(), productData);

		assertEquals(product1.getReference(), VALID_PRODUCT_REFERENCE_2);
		assertEquals(product1.getName(), VALID_PRODUCT_NAME_2);
		assertEquals(product1.getDescription(), VALID_PRODUCT_DESC_2);
		assertEquals(product1.getPrice(), VALID_PRODUCT_PRICE_2);
		assertEquals(product1.getDiscount(), VALID_PRODUCT_DISCOUNT_2);
		assertEquals(product1.getStock(), VALID_PRODUCT_STOCK_2);

	}

	@Test(expected = InstanceNotFoundException.class)
	public void testUpdateNonExistenProduct()
			throws InstanceNotFoundException, InputValidationException, DuplicateInstanceException {

		productService.updateProduct((long) -2, null);

	}

	@Test(expected = InputValidationException.class)
	public void testUpdateProductInvalidReference()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		Long productId = getValidProduct1(creatorId).getId();

		ProductData productData = new ProductData(INVALID_PRODUCT_REFERENCE, VALID_PRODUCT_NAME_2, VALID_PRODUCT_DESC_2,
				"", "", VALID_PRODUCT_PRICE_2, VALID_PRODUCT_DISCOUNT_2, VALID_PRODUCT_STOCK_2,
				getValidCategoryId2(creatorId), creatorId);

		productService.updateProduct(productId, productData);
	}

	@Test(expected = InputValidationException.class)
	public void testUpdateProductInvalidName()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		Long productId = getValidProduct1(creatorId).getId();
		ProductData productData = new ProductData(VALID_PRODUCT_REFERENCE_1, INVALID_PRODUCT_NAME, VALID_PRODUCT_DESC_2,
				"", "", VALID_PRODUCT_PRICE_2, VALID_PRODUCT_DISCOUNT_2, VALID_PRODUCT_STOCK_2,
				getValidCategoryId2(creatorId), creatorId);

		productService.updateProduct(productId, productData);
	}

	@Test(expected = InputValidationException.class)
	public void testUpdateProductInvalidDescription()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		Long productId = getValidProduct1(creatorId).getId();
		ProductData productData = new ProductData(VALID_PRODUCT_REFERENCE_1, VALID_PRODUCT_NAME_1, INVALID_PRODUCT_DESC,
				"", "", VALID_PRODUCT_PRICE_2, VALID_PRODUCT_DISCOUNT_2, VALID_PRODUCT_STOCK_2,
				getValidCategoryId2(creatorId), creatorId);

		productService.updateProduct(productId, productData);
	}

	@Test(expected = InputValidationException.class)
	public void testUpdateProductInvalidPrice()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		Long productId = getValidProduct1(creatorId).getId();
		ProductData productData = new ProductData(VALID_PRODUCT_REFERENCE_1, VALID_PRODUCT_NAME_1, VALID_PRODUCT_DESC_1,
				"", "", INVALID_PRODUCT_PRICE, VALID_PRODUCT_DISCOUNT_2, VALID_PRODUCT_STOCK_2,
				getValidCategoryId2(creatorId), creatorId);

		productService.updateProduct(productId, productData);
	}

	@Test(expected = InputValidationException.class)
	public void testUpdateProductInvalidDiscount()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		Long productId = getValidProduct1(creatorId).getId();
		ProductData productData = new ProductData(VALID_PRODUCT_REFERENCE_1, VALID_PRODUCT_NAME_1, VALID_PRODUCT_DESC_1,
				"", "", VALID_PRODUCT_PRICE_1, INVALID_PRODUCT_DISCOUNT, VALID_PRODUCT_STOCK_2,
				getValidCategoryId2(creatorId), creatorId);

		productService.updateProduct(productId, productData);
	}

	@Test(expected = InputValidationException.class)
	public void testUpdateProductInvalidStock()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		Long productId = getValidProduct1(creatorId).getId();
		ProductData productData = new ProductData(VALID_PRODUCT_REFERENCE_1, VALID_PRODUCT_NAME_1, VALID_PRODUCT_DESC_1,
				"", "", VALID_PRODUCT_PRICE_1, INVALID_PRODUCT_DISCOUNT, INVALID_PRODUCT_STOCK,
				getValidCategoryId2(creatorId), creatorId);

		productService.updateProduct(productId, productData);
	}

	@Test(expected = InstanceNotFoundException.class)
	public void testFinByIdNonExistenProduct()
			throws InstanceNotFoundException, InputValidationException, DuplicateInstanceException {

		productService.findProductById((long) -2);

	}

	@Test(expected = InstanceNotFoundException.class)
	public void testFinByReferenceNonExistenProduct()
			throws InstanceNotFoundException, InputValidationException, DuplicateInstanceException {

		productService.findProductByReference("NON EXISTEN REFERENCE");

	}

	@Test
	public void testFinByKeywords()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		Product product1 = getValidProduct1(creatorId);
		Product product2 = getValidProduct2(creatorId);

		List<Product> search = productService.findProductByKeywords("Product");
		assertEquals(search.size(), 2);
		search = productService.findProductByKeywords("number 2");
		assertEquals(product2.getId(), search.get(0).getId());
		assertEquals(search.size(), 1);
		search = productService.findProductByKeywords("2");
		assertEquals(search.size(), 2);
		search = productService.findProductByKeywords("C12");
		assertEquals(search.size(), 1);
		assertEquals(product1.getId(), search.get(0).getId());
		search = productService.findProductByKeywords("number 3");
		assertTrue(search.isEmpty());
	}

	@Test
	public void testFindProductsByName()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		Product product1 = getValidProduct1(creatorId);
		Product product2 = getValidProduct2(creatorId);

		List<Product> search = productService.findProductsByName("Prod");
		assertEquals(search.size(), 2);
		search = productService.findProductsByName("duct 2");
		assertEquals(search.size(), 1);
		assertEquals(product2.getId(), search.get(0).getId());
		search = productService.findProductsByName("oduct 1");
		assertEquals(search.size(), 1);
		assertEquals(product1.getId(), search.get(0).getId());
		search = productService.findProductsByName("number");
		assertTrue(search.isEmpty());
	}

	@Test
	public void testFindByStockMin()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		Product product1 = getValidProduct1(creatorId);
		getValidProduct2(creatorId);

		List<Product> search = productService.findProductsByStockMin(20);
		assertEquals(search.size(), 2);
		search = productService.findProductsByStockMin(19);
		assertEquals(search.size(), 1);
		assertEquals(product1.getId(), search.get(0).getId());
		search = productService.findProductsByStockMin(4);
		assertTrue(search.isEmpty());
	}

	@Test
	public void testFindByCategoryId()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();

		Product product1 = getValidProduct1(creatorId);
		Long categoryId1 = product1.getCategory().getId();
		List<Product> search = productService.findProductsByCategoryId(categoryId1);
		assertEquals(search.size(), 1);
		assertEquals(product1.getId(), search.get(0).getId());

		Product product2 = getValidProduct2(creatorId);
		Long categoryId2 = product2.getCategory().getId();
		search = productService.findProductsByCategoryId(categoryId2);
		assertEquals(search.size(), 1);
		assertEquals(product2.getId(), search.get(0).getId());
	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testFindByNonExistenCategoryId()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		productService.findProductsByCategoryId((long) 1);

	}

	@Test
	public void testFindByCreatorId()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId1 = getValidUserId();
		Long creatorId2 = getValidUserId2();

		Product product1 = getValidProduct1(creatorId1);
		List<Product> search = productService.findProductsByCreatorId(creatorId1);
		assertEquals(search.size(), 1);
		assertEquals(product1.getId(), search.get(0).getId());

		Product product2 = getValidProduct2(creatorId2);
		search = productService.findProductsByCreatorId(creatorId2);
		assertEquals(search.size(), 1);
		assertEquals(product2.getId(), search.get(0).getId());

	}

	@Test(expected = InstanceNotFoundException.class)
	public void testFindByNonExistenCreatorId()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		productService.findProductsByCreatorId((long) 1);

	}

	@Test
	public void testFindByAll() throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();

		List<Product> search = productService.findAllProducts();

		Product product1 = getValidProduct1(creatorId);
		search = productService.findAllProducts();
		assertEquals(search.size(), 1);
		assertEquals(product1.getId(), search.get(0).getId());

		getValidProduct2(creatorId);
		search = productService.findAllProducts();
		assertEquals(search.size(), 2);
	}
}
