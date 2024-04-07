package es.udc.tfg.app.test.service.clientService;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.udc.tfg.app.model.client.Client;
import es.udc.tfg.app.service.clientservice.ClientData;
import es.udc.tfg.app.service.clientservice.ClientService;
import es.udc.tfg.app.service.userservice.RegisterData;
import es.udc.tfg.app.service.userservice.UserService;
import es.udc.tfg.app.util.exceptions.DuplicateInstanceException;
import es.udc.tfg.app.util.exceptions.InputValidationException;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class clientServiceTest {

	@Autowired
	ClientService clientService;

	@Autowired
	UserService userService;

	private final String INVALID_EMAIL = "email";
	private final String VALID_EMAIL = "email3@udc.es";
	private final String VALID_EMAIL_2 = "email45@udc.es";
	private final String INVALID_DNI = "12345";
	private final String VALID_DNI = "12345677A";
	private final String VALID_DNI_2 = "12345678A";
	private final String VALID_PASSWORD = "password";
	private final String VALID_PASSWORD_2 = "password2";
	private final String INVALID_FIRST_NAME = "";
	private final String VALID_FIRST_NAME = "Alejandro";
	private final String VALID_FIRST_NAME_2 = "Alberto";
	private final String INVALID_LAST_NAME = "";
	private final String VALID_LAST_NAME = "Gomez";
	private final String VALID_LAST_NAME_2 = "García";
	private final String VALID_BIRTH_DATE = "01/01/1990";
	private final String VALID_BIRTH_DATE_2 = "03/12/1994";
	private final String VALID_LANGUAGE = "ESP";
	private final String VALID_LANGUAGE_2 = "GAL";
	private final String VALID_USER_TYPE = "EMPLOYEE";
	private final String VALID_USER_TYPE_2 = "ADMIN";
	private final String VALID_USER_IMAGE = "asfasdgasdg";
	private final String VALID_USER_IMAGE_2 = "ertfbdfhf";

	private Long getValidUserId() throws InputValidationException, DuplicateInstanceException {
		System.out.println("Creado el usuario");
		return userService.registerUser(new RegisterData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI, VALID_EMAIL,
				VALID_PASSWORD, VALID_BIRTH_DATE, VALID_LANGUAGE, VALID_USER_TYPE, VALID_USER_IMAGE)).getId();
	}

	private Long getValidUserId2() throws InputValidationException, DuplicateInstanceException {
		return userService
				.registerUser(new RegisterData(VALID_FIRST_NAME_2, VALID_LAST_NAME_2, VALID_DNI_2, VALID_EMAIL_2,
						VALID_PASSWORD_2, VALID_BIRTH_DATE_2, VALID_LANGUAGE_2, VALID_USER_TYPE_2, VALID_USER_IMAGE_2))
				.getId();
	}

	private final String INVALID_ADDRESS = "";
	private final String VALID_ADDRESS = "Lugar Nº3 Izq";
	private final String VALID_ADDRESS_2 = "Lugar Nº2 Der";
	private final String INVALID_CITY = "";
	private final String VALID_CITY = "Ciudad nueva";
	private final String VALID_CITY_2 = "Ciudad vieja";
	private final Long INVALID_POSTCODE = (long) 15;
	private final Long VALID_POSTCODE = (long) 15680;
	private final Long VALID_POSTCODE_2 = (long) 15881;
	private final String INVALID_PROVINCE = "";
	private final String VALID_PROVINCE = "A CORUÑA";
	private final String VALID_PROVINCE_2 = "LUGO";
	private final Long INVALID_PHONENUMBER = (long) 981888;
	private final Long VALID_PHONENUMBER = (long) 981888999;
	private final Long VALID_PHONENUMBER_2 = (long) 981666999;

	private ClientData getValidClientData() {
		return new ClientData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI, VALID_ADDRESS, VALID_CITY, VALID_POSTCODE,
				VALID_PROVINCE, VALID_EMAIL, VALID_PHONENUMBER);
	}

	private ClientData getValidClientData2() {
		return new ClientData(VALID_FIRST_NAME_2, VALID_LAST_NAME_2, VALID_DNI_2, VALID_ADDRESS_2, VALID_CITY_2,
				VALID_POSTCODE_2, VALID_PROVINCE_2, VALID_EMAIL_2, VALID_PHONENUMBER_2);
	}

	private Client getValidClient(Long creatorId, ClientData clientData)
			throws InputValidationException, InstanceNotFoundException, DuplicateInstanceException {

		return clientService.createClient(clientData, creatorId);
	}

	@Test
	public void testcreateAdnFindById()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		Client client1 = getValidClient(creatorId, getValidClientData());
		Client client2 = getValidClient(creatorId, getValidClientData2());
		Client search = clientService.findClientById(client1.getId());
		assertEquals(client1.getId(), search.getId());
		search = clientService.findClientById(client2.getId());
		assertEquals(client2.getId(), search.getId());

	}

	@Test(expected = InstanceNotFoundException.class)
	public void testFindByIdNonExistenClient()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		clientService.findClientById((long) 1);

	}

	@Test(expected = InstanceNotFoundException.class)
	public void testCreateNonExistenCreatorId()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		clientService.createClient(null, (long) 1);

	}

	@Test(expected = DuplicateInstanceException.class)
	public void testCreateDuplicatedDNI()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		getValidClient(creatorId, getValidClientData());
		getValidClient(creatorId, getValidClientData());

	}

	@Test(expected = InputValidationException.class)
	public void testCreateInvalidFirstName()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		ClientData clientData = new ClientData(INVALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI, VALID_ADDRESS,
				VALID_CITY, VALID_POSTCODE, VALID_PROVINCE, VALID_EMAIL, VALID_PHONENUMBER);
		clientService.createClient(clientData, creatorId);
	}

	@Test(expected = InputValidationException.class)
	public void testCreateInvalidLastName()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		ClientData clientData = new ClientData(VALID_FIRST_NAME, INVALID_LAST_NAME, VALID_DNI, VALID_ADDRESS,
				VALID_CITY, VALID_POSTCODE, VALID_PROVINCE, VALID_EMAIL, VALID_PHONENUMBER);
		clientService.createClient(clientData, creatorId);
	}

	@Test(expected = InputValidationException.class)
	public void testCreateInvalidDni()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		ClientData clientData = new ClientData(VALID_FIRST_NAME, VALID_LAST_NAME, INVALID_DNI, VALID_ADDRESS,
				VALID_CITY, VALID_POSTCODE, VALID_PROVINCE, VALID_EMAIL, VALID_PHONENUMBER);
		clientService.createClient(clientData, creatorId);
	}

	@Test(expected = InputValidationException.class)
	public void testCreateInvalidAddress()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		ClientData clientData = new ClientData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI, null, VALID_CITY,
				VALID_POSTCODE, VALID_PROVINCE, VALID_EMAIL, VALID_PHONENUMBER);
		clientService.createClient(clientData, creatorId);
	}

	@Test(expected = InputValidationException.class)
	public void testCreateInvalidCity()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		ClientData clientData = new ClientData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI, VALID_ADDRESS, null,
				VALID_POSTCODE, VALID_PROVINCE, VALID_EMAIL, VALID_PHONENUMBER);
		clientService.createClient(clientData, creatorId);
	}

	@Test(expected = InputValidationException.class)
	public void testCreateInvalidPostCode()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		ClientData clientData = new ClientData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI, VALID_ADDRESS, VALID_CITY,
				INVALID_POSTCODE, VALID_PROVINCE, VALID_EMAIL, VALID_PHONENUMBER);
		clientService.createClient(clientData, creatorId);
	}

	@Test(expected = InputValidationException.class)
	public void testCreateInvalidProvince()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		ClientData clientData = new ClientData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI, VALID_ADDRESS, VALID_CITY,
				VALID_POSTCODE, INVALID_PROVINCE, VALID_EMAIL, VALID_PHONENUMBER);
		clientService.createClient(clientData, creatorId);
	}

	@Test(expected = InputValidationException.class)
	public void testCreateInvalidEmail()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		ClientData clientData = new ClientData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI, VALID_ADDRESS, VALID_CITY,
				VALID_POSTCODE, VALID_PROVINCE, INVALID_EMAIL, VALID_PHONENUMBER);
		clientService.createClient(clientData, creatorId);
	}

	@Test(expected = InputValidationException.class)
	public void testCreateInvalidPhone()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		ClientData clientData = new ClientData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI, VALID_ADDRESS, VALID_CITY,
				VALID_POSTCODE, VALID_PROVINCE, VALID_EMAIL, INVALID_PHONENUMBER);
		clientService.createClient(clientData, creatorId);
	}

	@Test
	public void testcreateUpdateClient()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		ClientData clientDataInicial = getValidClientData();
		Client client = getValidClient(creatorId, clientDataInicial);

		assertEquals(client.getFirstName(), clientDataInicial.getFirstName());
		assertEquals(client.getLastName(), clientDataInicial.getLastName());
		assertEquals(client.getDni(), clientDataInicial.getDni());
		assertEquals(client.getAddress(), clientDataInicial.getAddress());
		assertEquals(client.getCity(), clientDataInicial.getCity());
		assertEquals(client.getPostCode(), clientDataInicial.getPostCode());
		assertEquals(client.getProvince(), clientDataInicial.getProvince());
		assertEquals(client.getEmail(), clientDataInicial.getEmail());
		assertEquals(client.getPhoneNumber(), clientDataInicial.getPhoneNumber());

		ClientData clientDataModificado = getValidClientData2();
		clientService.updateClient(client.getId(), clientDataModificado);
		client = clientService.findClientById(client.getId());

		assertEquals(client.getFirstName(), clientDataModificado.getFirstName());
		assertEquals(client.getLastName(), clientDataModificado.getLastName());
		assertEquals(client.getDni(), clientDataModificado.getDni());
		assertEquals(client.getAddress(), clientDataModificado.getAddress());
		assertEquals(client.getCity(), clientDataModificado.getCity());
		assertEquals(client.getPostCode(), clientDataModificado.getPostCode());
		assertEquals(client.getProvince(), clientDataModificado.getProvince());
		assertEquals(client.getEmail(), clientDataModificado.getEmail());
		assertEquals(client.getPhoneNumber(), clientDataModificado.getPhoneNumber());
	}

	@Test(expected = InstanceNotFoundException.class)
	public void testUpdateNonExistenClient()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		clientService.updateClient((long) 1, null);
	}

	@Test(expected = InputValidationException.class)
	public void testUpdateInvalidFirstName()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long clientId = getValidClient(getValidUserId(), getValidClientData()).getId();
		ClientData clientData = new ClientData(INVALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI, VALID_ADDRESS,
				VALID_CITY, VALID_POSTCODE, VALID_PROVINCE, VALID_EMAIL, VALID_PHONENUMBER);
		clientService.updateClient(clientId, clientData);
	}

	@Test(expected = InputValidationException.class)
	public void testUpdateInvalidLastName()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long clientId = getValidClient(getValidUserId(), getValidClientData()).getId();
		ClientData clientData = new ClientData(VALID_FIRST_NAME, INVALID_LAST_NAME, VALID_DNI, VALID_ADDRESS,
				VALID_CITY, VALID_POSTCODE, VALID_PROVINCE, VALID_EMAIL, VALID_PHONENUMBER);
		clientService.updateClient(clientId, clientData);
	}

	@Test(expected = InputValidationException.class)
	public void testUpdateInvalidDni()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long clientId = getValidClient(getValidUserId(), getValidClientData()).getId();
		ClientData clientData = new ClientData(VALID_FIRST_NAME, VALID_LAST_NAME, INVALID_DNI, VALID_ADDRESS,
				VALID_CITY, VALID_POSTCODE, VALID_PROVINCE, VALID_EMAIL, VALID_PHONENUMBER);
		clientService.updateClient(clientId, clientData);
	}

	@Test(expected = InputValidationException.class)
	public void testUpdateInvalidAddress()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long clientId = getValidClient(getValidUserId(), getValidClientData()).getId();
		ClientData clientData = new ClientData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI, INVALID_ADDRESS,
				VALID_CITY, VALID_POSTCODE, VALID_PROVINCE, VALID_EMAIL, VALID_PHONENUMBER);
		clientService.updateClient(clientId, clientData);
	}

	@Test(expected = InputValidationException.class)
	public void testUpdateInvalidCity()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long clientId = getValidClient(getValidUserId(), getValidClientData()).getId();
		ClientData clientData = new ClientData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI, VALID_ADDRESS,
				INVALID_CITY, VALID_POSTCODE, VALID_PROVINCE, VALID_EMAIL, VALID_PHONENUMBER);
		clientService.updateClient(clientId, clientData);
	}

	@Test(expected = InputValidationException.class)
	public void testUpdateInvalidPostCode()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long clientId = getValidClient(getValidUserId(), getValidClientData()).getId();
		ClientData clientData = new ClientData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI, VALID_ADDRESS, VALID_CITY,
				INVALID_POSTCODE, VALID_PROVINCE, VALID_EMAIL, VALID_PHONENUMBER);
		clientService.updateClient(clientId, clientData);
	}

	@Test(expected = InputValidationException.class)
	public void testUpdateInvalidProvince()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long clientId = getValidClient(getValidUserId(), getValidClientData()).getId();
		ClientData clientData = new ClientData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI, VALID_ADDRESS, VALID_CITY,
				VALID_POSTCODE, INVALID_PROVINCE, VALID_EMAIL, VALID_PHONENUMBER);
		clientService.updateClient(clientId, clientData);
	}

	@Test(expected = InputValidationException.class)
	public void testUpdateInvalidEmail()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long clientId = getValidClient(getValidUserId(), getValidClientData()).getId();
		ClientData clientData = new ClientData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI, VALID_ADDRESS, VALID_CITY,
				VALID_POSTCODE, VALID_PROVINCE, INVALID_EMAIL, VALID_PHONENUMBER);
		clientService.updateClient(clientId, clientData);
	}

	@Test(expected = InputValidationException.class)
	public void testUpdateInvalidPhone()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long clientId = getValidClient(getValidUserId(), getValidClientData()).getId();
		ClientData clientData = new ClientData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI, VALID_ADDRESS, VALID_CITY,
				VALID_POSTCODE, VALID_PROVINCE, VALID_EMAIL, INVALID_PHONENUMBER);
		clientService.updateClient(clientId, clientData);
	}

	@Test(expected = DuplicateInstanceException.class)
	public void testUpdateDuplicatedDni()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		Long clientId = getValidClient(creatorId, getValidClientData()).getId();
		ClientData clientData = getValidClientData2();
		getValidClient(creatorId, clientData);
		clientService.updateClient(clientId, clientData);
	}

	@Test
	public void testFindClientByFirstName()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		List<Client> search = clientService.findClientByFirstName("prueba");
		assertTrue(search.isEmpty());
		Long creatorId = getValidUserId();
		ClientData clientData1 = getValidClientData();
		Client client1 = getValidClient(creatorId, clientData1);
		ClientData clientData2 = getValidClientData2();
		Client client2 = getValidClient(creatorId, clientData2);
		search = clientService.findClientByFirstName("Al");
		assertEquals(search.size(), 2);
		search = clientService.findClientByFirstName(clientData1.getFirstName());
		assertEquals(client1.getId(), search.get(0).getId());
		search = clientService.findClientByFirstName(clientData2.getFirstName());
		assertEquals(client2.getId(), search.get(0).getId());
	}

	@Test
	public void testFindClientByLastName()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		List<Client> search = clientService.findClientByLastName("prueba");
		assertTrue(search.isEmpty());
		Long creatorId = getValidUserId();
		ClientData clientData1 = getValidClientData();
		Client client1 = getValidClient(creatorId, clientData1);
		ClientData clientData2 = getValidClientData2();
		Client client2 = getValidClient(creatorId, clientData2);
		search = clientService.findClientByLastName("G");
		assertEquals(search.size(), 2);
		search = clientService.findClientByLastName(clientData1.getLastName());
		assertEquals(client1.getId(), search.get(0).getId());
		search = clientService.findClientByLastName(clientData2.getLastName());
		assertEquals(client2.getId(), search.get(0).getId());
	}

	@Test
	public void testFindClientByDni()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId = getValidUserId();
		ClientData clientData1 = getValidClientData();
		Client client1 = getValidClient(creatorId, clientData1);
		ClientData clientData2 = getValidClientData2();
		Client client2 = getValidClient(creatorId, clientData2);
		Client search = clientService.findClientByDni(clientData1.getDni());
		assertEquals(client1.getId(), search.getId());
		search = clientService.findClientByDni(clientData2.getDni());
		assertEquals(client2.getId(), search.getId());
	}

	@Test(expected = InstanceNotFoundException.class)
	public void testFindClientByNonExistenDni()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		clientService.findClientByDni(VALID_DNI);

	}

	@Test
	public void testFindClientByCity()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		List<Client> search = clientService.findClientByCity("prueba");
		assertTrue(search.isEmpty());
		Long creatorId = getValidUserId();
		ClientData clientData1 = getValidClientData();
		Client client1 = getValidClient(creatorId, clientData1);
		ClientData clientData2 = getValidClientData2();
		Client client2 = getValidClient(creatorId, clientData2);
		search = clientService.findClientByCity("Ciudad");
		assertEquals(search.size(), 2);
		search = clientService.findClientByCity(clientData1.getCity());
		assertEquals(client1.getId(), search.get(0).getId());
		search = clientService.findClientByCity(clientData2.getCity());
		assertEquals(client2.getId(), search.get(0).getId());
	}

	@Test
	public void testFindClientByCreatorId()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Long creatorId1 = getValidUserId();
		Long creatorId2 = getValidUserId2();
		List<Client> search = clientService.findClientByCreatorId(creatorId1);
		assertTrue(search.isEmpty());
		Client client1 = getValidClient(creatorId1, getValidClientData());
		Client client2 = getValidClient(creatorId2, getValidClientData2());
		search = clientService.findClientByCreatorId(creatorId1);
		assertEquals(client1.getId(), search.get(0).getId());
		search = clientService.findClientByCreatorId(creatorId2);
		assertEquals(client2.getId(), search.get(0).getId());
	}

	@Test
	public void testFindAllClients()
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		List<Client> search = clientService.findAll();
		assertTrue(search.isEmpty());
		getValidClient(getValidUserId(), getValidClientData());
		search = clientService.findAll();
		assertEquals(1, search.size());
		getValidClient(getValidUserId2(), getValidClientData2());
		search = clientService.findAll();
		assertEquals(2, search.size());
	}
}
