package es.udc.tfg.app.test.service.noteService;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.udc.tfg.app.model.note.Note;
import es.udc.tfg.app.model.product.Product;
import es.udc.tfg.app.model.noteline.Noteline;
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
	private final String VALID_EMAIL_2 = "email2@udc.es";
	private final String VALID_DNI = "12345677A";
	private final String VALID_DNI_2 = "12345677B";
	private final String VALID_PASSWORD = "password";
	private final String VALID_FIRST_NAME = "firstName1";
	private final String VALID_LAST_NAME = "lastName1";
	private final String VALID_BIRTH_DATE = "01/01/1990";
	private final String VALID_LANGUAGE = "ESP";
	private final String VALID_USER_TYPE = "EMPLOYEE";
	private final String VALID_USER_IMAGE = "asfasdgasdg";
	private final String VALID_USER_TAX = "VAT";

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

	private final String VALID_NAME = "category 1";
	private final String VALID_DESC = "category number 1";

	private final String VALID_COMMENT = "Comment 1";
	private final String VALID_COMMENT_2 = "Comment 2";

	private Long getValidUserId() throws InputValidationException, DuplicateInstanceException {
		return userService.registerUser(new RegisterData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI, VALID_EMAIL,
				VALID_PASSWORD, VALID_BIRTH_DATE, VALID_LANGUAGE, VALID_USER_TYPE, VALID_USER_IMAGE)).getId();
	}

	private Long getValidUserId2() throws InputValidationException, DuplicateInstanceException {
		return userService.registerUser(new RegisterData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI_2, VALID_EMAIL_2,
				VALID_PASSWORD, VALID_BIRTH_DATE, VALID_LANGUAGE, VALID_USER_TYPE, VALID_USER_IMAGE)).getId();
	}

	private Long getValidCategoryId(Long creatorId)
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {
		return categoryService.createCategory(new CategoryData(VALID_NAME, VALID_DESC, creatorId)).getId();

	}

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
				getValidCategoryId(creatorId), creatorId);

		return productService.createProduct(productData);
	}

	private List<NotelineData> getValidNotelineDataList1(Long creatorId)
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		List<NotelineData> list = new ArrayList<NotelineData>();
		list.add(new NotelineData(1, 0, null, getValidProduct1(creatorId).getReference()));
		list.add(new NotelineData(3, 10, "Comentario para producto", getValidProduct2(creatorId).getReference()));
		return list;
	}

	private List<NotelineData> getInvalidNotelineDataList1(Long creatorId)
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		List<NotelineData> list = new ArrayList<NotelineData>();
		list.add(new NotelineData(0, 0, null, getValidProduct1(creatorId).getReference()));
		return list;
	}

	private List<NotelineData> getInvalidNotelineDataList2(Long creatorId)
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		List<NotelineData> list = new ArrayList<NotelineData>();
		list.add(new NotelineData(-1, 101, null, getValidProduct1(creatorId).getReference()));
		return list;
	}

	private Long getValidClientId(Long creatorId)
			throws InputValidationException, InstanceNotFoundException, DuplicateInstanceException {

		ClientData clientData = new ClientData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI, VALID_ADDRESS, VALID_CITY,
				VALID_POSTCODE, VALID_PROVINCE, VALID_EMAIL, VALID_PHONENUMBER, VALID_USER_TAX);
		return clientService.createClient(clientData, creatorId).getId();

	}

	private Long getValidClientId2(Long creatorId)
			throws InputValidationException, InstanceNotFoundException, DuplicateInstanceException {

		ClientData clientData = new ClientData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI_2, VALID_ADDRESS,
				VALID_CITY, VALID_POSTCODE, VALID_PROVINCE, VALID_EMAIL_2, VALID_PHONENUMBER, VALID_USER_TAX);
		return clientService.createClient(clientData, creatorId).getId();

	}

	private Note getValidNote1(Long creatorId, Long clientId)
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		return noteService.createNote(creatorId, clientId, VALID_COMMENT, getValidNotelineDataList1(creatorId));
	}

	@Test
	public void testcreateAdnFindNoteAndNotelineById()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		Long clientId = getValidClientId(creatorId);
		Note note = getValidNote1(creatorId, clientId);
		Note noteSearch = noteService.findNoteById(note.getId());

		Noteline noteline = note.getNotelines().get(0);
		Noteline notelineSearch = noteService.findNotelineById(note.getId(), noteline.getNotelineId());

		assertEquals(note.getId(), noteSearch.getId());
		assertEquals(note.getNotelines().size(), noteSearch.getNotelines().size());

		assertEquals(noteline.getNote().getId(), notelineSearch.getNote().getId());
		assertEquals(noteline.getNotelineId(), notelineSearch.getNotelineId());

	}

	@Test(expected = InputValidationException.class)
	public void testCreate0amount()
			throws InstanceNotFoundException, InputValidationException, DuplicateInstanceException {
		Long creatorId = getValidUserId();
		Long clientId = getValidClientId(creatorId);
		noteService.createNote(creatorId, clientId, VALID_COMMENT, getInvalidNotelineDataList1(creatorId));

	}

	@Test(expected = InputValidationException.class)
	public void testCreateGreaterDiscount()
			throws InstanceNotFoundException, InputValidationException, DuplicateInstanceException {
		Long creatorId = getValidUserId();
		Long clientId = getValidClientId(creatorId);
		noteService.createNote(creatorId, clientId, VALID_COMMENT, getInvalidNotelineDataList2(creatorId));

	}

	@Test(expected = InstanceNotFoundException.class)
	public void testcreateAdnFindByIdNonExistenNote()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		noteService.findNoteById((long) 0);

	}

	@Test(expected = InstanceNotFoundException.class)
	public void testcreateAdnFindByIdNonExistenNoteline()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		Long clientId = getValidClientId(creatorId);
		Long noteId = getValidNote1(creatorId, clientId).getId();

		noteService.findNotelineById(noteId, (long) 5);

	}

	@Test
	public void testModifyNote()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		Long clientId = getValidClientId(creatorId);
		Note note = getValidNote1(creatorId, clientId);

		assertEquals(note.getClient().getId(), clientId);
		assertEquals(note.getComment(), VALID_COMMENT);

		Long clientId2 = getValidClientId2(creatorId);
		noteService.modifyNote(note.getId(), clientId2, VALID_COMMENT_2);
		Note noteModified = noteService.findNoteById(note.getId());

		assertEquals(noteModified.getClient().getId(), clientId2);
		assertEquals(noteModified.getComment(), VALID_COMMENT_2);

		noteService.modifyNote(note.getId(), clientId, null);
		noteModified = noteService.findNoteById(note.getId());

		assertEquals(noteModified.getClient().getId(), clientId);
		assertEquals(noteModified.getComment(), VALID_COMMENT_2);

	}

	@Test(expected = InstanceNotFoundException.class)
	public void testModifyNonExistenNote() throws InstanceNotFoundException, InputValidationException {
		noteService.modifyNote((long) 0, null, null);

	}

	@Test(expected = InstanceNotFoundException.class)
	public void testModifyNoteNonExistenClient()
			throws InstanceNotFoundException, InputValidationException, DuplicateInstanceException {
		Long creatorId = getValidUserId();
		Long clientId = getValidClientId(creatorId);
		Long noteId = getValidNote1(creatorId, clientId).getId();
		noteService.modifyNote(noteId, clientId + 1, null);

	}

	@Test
	public void testModifyNoteline()
			throws InstanceNotFoundException, InputValidationException, DuplicateInstanceException {
		Long creatorId = getValidUserId();
		Long clientId = getValidClientId(creatorId);
		Note note = getValidNote1(creatorId, clientId);
		NotelineData notelineData = new NotelineData(50, 24, VALID_COMMENT_2,
				note.getNotelines().get(0).getProduct().getReference());
		noteService.modifyNoteLine(note.getId(), note.getNotelines().get(0).getNotelineId(), notelineData);
		Noteline noteline = noteService.findNotelineById(note.getId(), note.getNotelines().get(0).getNotelineId());

		assertEquals(noteline.getAmount(), 50);
		assertEquals(noteline.getDiscount(), 24);
		assertEquals(noteline.getComment(), VALID_COMMENT_2);

	}

	@Test(expected = InputValidationException.class)
	public void testModifyNoteline0amount()
			throws InstanceNotFoundException, InputValidationException, DuplicateInstanceException {
		Long creatorId = getValidUserId();
		Long clientId = getValidClientId(creatorId);
		Note note = getValidNote1(creatorId, clientId);
		NotelineData notelineData = new NotelineData(0, VALID_PRODUCT_DISCOUNT_2, VALID_COMMENT,
				note.getNotelines().get(0).getProduct().getReference());
		noteService.modifyNoteLine(note.getId(), note.getNotelines().get(0).getNotelineId(), notelineData);

	}

	@Test(expected = InputValidationException.class)
	public void testModifyNotelineGreaterDiscount()
			throws InstanceNotFoundException, InputValidationException, DuplicateInstanceException {
		Long creatorId = getValidUserId();
		Long clientId = getValidClientId(creatorId);
		Note note = getValidNote1(creatorId, clientId);
		NotelineData notelineData = new NotelineData(2, 101, VALID_COMMENT,
				note.getNotelines().get(0).getProduct().getReference());
		noteService.modifyNoteLine(note.getId(), note.getNotelines().get(0).getNotelineId(), notelineData);

	}

	@Test(expected = InstanceNotFoundException.class)
	public void testRemoveNote()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		Long clientId = getValidClientId(creatorId);
		Long noteId = getValidNote1(creatorId, clientId).getId();

		noteService.findNoteById(noteId);
		noteService.removeNote(noteId);
		noteService.findNoteById(noteId);
	}

	@Test(expected = InstanceNotFoundException.class)
	public void testRemoveNoteline()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		Long clientId = getValidClientId(creatorId);
		Note note = getValidNote1(creatorId, clientId);
		assertEquals(note.getNotelines().size(), 2);
		Long notelineId = note.getNotelines().get(0).getNotelineId();

		noteService.removeNoteLine(note.getId(), notelineId);

		Note noteSearch = noteService.findNoteById(note.getId());
		assertEquals(noteSearch.getNotelines().size(), 1);

		noteService.findNotelineById(note.getId(), notelineId);

	}

	@Test
	public void testFindNotelinesByNoteId()
			throws InstanceNotFoundException, InputValidationException, DuplicateInstanceException {
		Long creatorId = getValidUserId();
		Long clientId = getValidClientId(creatorId);
		Note note = getValidNote1(creatorId, clientId);
		List<Noteline> notelines = noteService.findNotelinesByNoteId(note.getId());

		assertEquals(note.getNotelines().size(), notelines.size());
		assertEquals(note.getNotelines().get(0), notelines.get(0));
		assertEquals(note.getNotelines().get(1), notelines.get(1));

	}

	@Test
	public void testFindNotelinesByProdcutId()
			throws InstanceNotFoundException, InputValidationException, DuplicateInstanceException {
		Long creatorId = getValidUserId();
		Long clientId = getValidClientId(creatorId);
		Product product1 = getValidProduct1(creatorId);
		Product product2 = getValidProduct2(creatorId);

		List<NotelineData> notelineDataList1 = new ArrayList<NotelineData>();
		notelineDataList1.add(new NotelineData(1, 0, null, product1.getReference()));
		notelineDataList1.add(new NotelineData(3, 10, null, product2.getReference()));
		Note note1 = noteService.createNote(creatorId, clientId, VALID_COMMENT, notelineDataList1);

		List<NotelineData> notelineDataList2 = new ArrayList<NotelineData>();
		notelineDataList2.add(new NotelineData(10, 15, null, product2.getReference()));
		noteService.createNote(creatorId, clientId, VALID_COMMENT, notelineDataList2);

		List<Noteline> notelines = noteService.findNotelinesByProductId(product1.getId());
		assertEquals(notelines.size(), 1);
		assertEquals(notelines.get(0).getProduct().getId(), product1.getId());
		assertEquals(notelines.get(0).getNote().getId(), note1.getId());

		notelines = noteService.findNotelinesByProductId(product2.getId());
		assertEquals(notelines.size(), 2);
		assertEquals(notelines.get(0).getProduct().getId(), product2.getId());
		assertEquals(notelines.get(1).getProduct().getId(), product2.getId());

	}

	@Test
	public void testFindNotesByClientId()
			throws InstanceNotFoundException, InputValidationException, DuplicateInstanceException {
		Long creatorId = getValidUserId();
		Long clientId1 = getValidClientId(creatorId);
		Long clientId2 = getValidClientId2(creatorId);
		Product product1 = getValidProduct1(creatorId);
		Product product2 = getValidProduct2(creatorId);

		List<NotelineData> notelineDataList1 = new ArrayList<NotelineData>();
		notelineDataList1.add(new NotelineData(1, 0, null, product1.getReference()));
		notelineDataList1.add(new NotelineData(3, 10, null, product2.getReference()));

		List<NotelineData> notelineDataList2 = new ArrayList<NotelineData>();
		notelineDataList2.add(new NotelineData(10, 15, null, product2.getReference()));

		noteService.createNote(creatorId, clientId1, VALID_COMMENT, notelineDataList1);
		noteService.createNote(creatorId, clientId1, VALID_COMMENT, notelineDataList2);
		noteService.createNote(creatorId, clientId2, VALID_COMMENT, notelineDataList1);

		List<Note> notes = noteService.findNotesByClientId(clientId2);
		assertEquals(notes.size(), 1);
		assertEquals(notes.get(0).getClient().getId(), clientId2);

		notes = noteService.findNotesByClientId(clientId1);
		assertEquals(notes.size(), 2);
		assertEquals(notes.get(0).getClient().getId(), clientId1);
		assertEquals(notes.get(0).getClient().getId(), clientId1);

	}

	@Test
	public void testFindNotesByInvoiceId()
			throws InstanceNotFoundException, InputValidationException, DuplicateInstanceException {

		// A la espera de las prueba de invoice

	}

	@Test
	public void testFindNotesByCreatorId()
			throws InstanceNotFoundException, InputValidationException, DuplicateInstanceException {
		Long creatorId1 = getValidUserId();
		Long creatorId2 = getValidUserId2();
		Long clientId = getValidClientId(creatorId1);
		Product product1 = getValidProduct1(creatorId1);
		Product product2 = getValidProduct2(creatorId1);

		List<NotelineData> notelineDataList1 = new ArrayList<NotelineData>();
		notelineDataList1.add(new NotelineData(1, 0, null, product1.getReference()));
		notelineDataList1.add(new NotelineData(3, 10, null, product2.getReference()));

		List<NotelineData> notelineDataList2 = new ArrayList<NotelineData>();
		notelineDataList2.add(new NotelineData(10, 15, null, product2.getReference()));

		List<Note> notes = noteService.findNotesByCreatorId(creatorId1);
		assertTrue(notes.isEmpty());
		notes = noteService.findNotesByCreatorId(creatorId2);
		assertTrue(notes.isEmpty());

		noteService.createNote(creatorId1, clientId, VALID_COMMENT, notelineDataList1);
		noteService.createNote(creatorId1, clientId, VALID_COMMENT, notelineDataList2);
		noteService.createNote(creatorId2, clientId, VALID_COMMENT, notelineDataList1);

		notes = noteService.findNotesByCreatorId(creatorId2);
		assertEquals(notes.size(), 1);
		assertEquals(notes.get(0).getCreator().getId(), creatorId2);

		notes = noteService.findNotesByCreatorId(creatorId1);
		assertEquals(notes.size(), 2);
		assertEquals(notes.get(0).getCreator().getId(), creatorId1);
		assertEquals(notes.get(0).getCreator().getId(), creatorId1);

	}

	@Test
	public void testFindAllNotes()
			throws InstanceNotFoundException, InputValidationException, DuplicateInstanceException {
		Long creatorId = getValidUserId();
		Long clientId = getValidClientId(creatorId);
		Product product1 = getValidProduct1(creatorId);
		Product product2 = getValidProduct2(creatorId);

		List<NotelineData> notelineDataList1 = new ArrayList<NotelineData>();
		notelineDataList1.add(new NotelineData(1, 0, null, product1.getReference()));
		notelineDataList1.add(new NotelineData(3, 10, null, product2.getReference()));

		List<NotelineData> notelineDataList2 = new ArrayList<NotelineData>();
		notelineDataList2.add(new NotelineData(10, 15, null, product2.getReference()));

		List<Note> notes = noteService.findAllNotes();
		assertTrue(notes.isEmpty());

		noteService.createNote(creatorId, clientId, VALID_COMMENT, notelineDataList1);
		notes = noteService.findAllNotes();
		assertEquals(notes.size(), 1);

		noteService.createNote(creatorId, clientId, VALID_COMMENT, notelineDataList2);
		notes = noteService.findAllNotes();
		assertEquals(notes.size(), 2);

		noteService.createNote(creatorId, clientId, VALID_COMMENT, notelineDataList1);
		notes = noteService.findAllNotes();
		assertEquals(notes.size(), 3);

	}
}