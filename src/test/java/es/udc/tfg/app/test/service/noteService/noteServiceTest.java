package es.udc.tfg.app.test.service.noteService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.udc.tfg.app.model.note.Note;
import es.udc.tfg.app.model.noteline.Noteline;
import es.udc.tfg.app.model.noteline.NotelineDao;
import es.udc.tfg.app.model.product.Product;
import es.udc.tfg.app.model.user.User;
import es.udc.tfg.app.service.categoryservice.CategoryData;
import es.udc.tfg.app.service.categoryservice.CategoryService;
import es.udc.tfg.app.service.clientservice.ClientData;
import es.udc.tfg.app.service.clientservice.ClientService;
import es.udc.tfg.app.service.noteservice.NoteService;
import es.udc.tfg.app.service.noteservice.NotelineData;
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
public class noteServiceTest {

	@Autowired
	NoteService noteService;

	@Autowired
	UserService userService;

	@Autowired
	ClientService clientService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;

	private final String VALID_EMAIL = "email3@udc.es";
	private final String VALID_DNI = "12345677A";
	private final String VALID_PASSWORD = "password";
	private final String VALID_FIRST_NAME = "firstName1";
	private final String VALID_LAST_NAME = "lastName1";
	private final String VALID_BIRTH_DATE = "01/01/1990";
	private final String VALID_LANGUAGE = "ESP";
	private final String VALID_USER_TYPE = "EMPLOYEE";
	private final String VALID_USER_IMAGE = "asfasdgasdg";

	private final String VALID_ADDRESS = "Lugar Nº3 Izq";
	private final String VALID_CITY = "Ciudad nueva";
	private final Long VALID_POSTCODE = (long) 15680;
	private final String VALID_PROVINCE = "A CORUÑA";
	private final Long VALID_PHONENUMBER = (long) 981888999;

	private final String VALID_PRODUCT_REFERENCE_1 = "C12345A";
	private final String VALID_PRODUCT_REFERENCE_2 = "D53345B";
	private final String VALID_PRODUCT_NAME_1 = "Product 1";
	private final String VALID_PRODUCT_NAME_2 = "Product 2";
	private final String VALID_PRODUCT_DESC_1 = "Product number 1";
	private final String VALID_PRODUCT_DESC_2 = "Product number 2";
	private final Float VALID_PRODUCT_PRICE_1 = (float) 12.34;
	private final Float VALID_PRODUCT_PRICE_2 = (float) 22.44;
	private final Integer VALID_PRODUCT_DISCOUNT_1 = 0;
	private final Integer VALID_PRODUCT_DISCOUNT_2 = 5;
	private final Integer VALID_PRODUCT_STOCK_1 = 5;
	private final Integer VALID_PRODUCT_STOCK_2 = 20;
	private final Integer INVALID_PRODUCT_STOCK = -20;

	private final String VALID_NAME = "category 1";
	private final String VALID_DESC = "category number 1";

	private Long getValidUserId() throws InputValidationException, DuplicateInstanceException {
		return userService.registerUser(new RegisterData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI, VALID_EMAIL,
				VALID_PASSWORD, VALID_BIRTH_DATE, VALID_LANGUAGE, VALID_USER_TYPE, VALID_USER_IMAGE)).getId();
	}

	private Long getValidCategoryId(Long creatorId)
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {
		return categoryService.createCategory(new CategoryData(VALID_NAME, VALID_DESC, creatorId)).getId();

	}

	private String getValidProductReference1(Long creatorId)
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		ProductData productData = new ProductData(VALID_PRODUCT_REFERENCE_1, VALID_PRODUCT_NAME_1, VALID_PRODUCT_DESC_1,
				"", "", VALID_PRODUCT_PRICE_1, VALID_PRODUCT_DISCOUNT_1, VALID_PRODUCT_STOCK_1,
				getValidCategoryId(creatorId), creatorId);

		return productService.createProduct(productData).getReference();
	}

	private String getValidProductReference2(Long creatorId)
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		ProductData productData = new ProductData(VALID_PRODUCT_REFERENCE_2, VALID_PRODUCT_NAME_2, VALID_PRODUCT_DESC_2,
				"", "", VALID_PRODUCT_PRICE_2, VALID_PRODUCT_DISCOUNT_2, VALID_PRODUCT_STOCK_2,
				getValidCategoryId(creatorId), creatorId);

		return productService.createProduct(productData).getReference();
	}

	private List<NotelineData> getValidNotelineDataList1(Long creatorId)
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		List<NotelineData> list = new ArrayList<NotelineData>();
		list.add(new NotelineData(1, 0, null, getValidProductReference1(creatorId)));
		list.add(new NotelineData(3, 10, "Comentario para producto", getValidProductReference2(creatorId)));

		return list;
	}

	private Long getValidClientId(Long creatorId)
			throws InputValidationException, InstanceNotFoundException, DuplicateInstanceException {

		ClientData clientData = new ClientData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI, VALID_ADDRESS, VALID_CITY,
				VALID_POSTCODE, VALID_PROVINCE, VALID_EMAIL, VALID_PHONENUMBER);
		return clientService.createClient(clientData, creatorId).getId();

	}

	private Long getValidNote1(Long creatorId, Long clientId)
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		return noteService.createNote(creatorId, clientId, "", getValidNotelineDataList1(creatorId)).getId();
	}

	@Test
	public void testcreateAdnFindById()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {
		
		Long creatorId = getValidUserId();
		Long clientId = getValidClientId(creatorId);
		Note note = noteService.createNote(creatorId, clientId, "", getValidNotelineDataList1(creatorId));

		Note noteSearch = noteService.findNoteById(note.getId());
		
		System.out.println(noteService.findNotelineById(noteSearch.getId(), (long) 2));

		//assertEquals(note.getId(), noteSearch.getId());

	}

}
