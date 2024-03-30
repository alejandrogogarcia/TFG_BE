package es.udc.tfg.app.test.service.clientService;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

	private final String VALID_ADDRESS = "Lugar Nº3 Izq";
	private final String VALID_ADDRESS_2 = "Lugar Nº2 Der";
	private final String VALID_CITY = "Ciudad nueva";
	private final String VALID_CITY_2 = "Ciudad vieja";
	private final Long VALID_POSTCODE = (long) 15680;
	private final Long VALID_POSTCODE_2 = (long) 15881;
	private final String VALID_PROVINCE = "A CORUÑA";
	private final String VALID_PROVINCE_2 = "LUGO";
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

}
