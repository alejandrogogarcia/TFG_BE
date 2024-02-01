package es.udc.tfg.app.test.service;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.udc.tfg.app.service.userservice.RegisterData;
import es.udc.tfg.app.service.userservice.UserService;
import es.udc.tfg.app.util.exceptions.ConfirmPasswordNotMatchException;
import es.udc.tfg.app.util.exceptions.DuplicateInstanceException;
import es.udc.tfg.app.util.exceptions.InputValidationException;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
@Transactional
public class create {

	@Autowired
	UserService userService;

	private final String VALID_EMAIL = "email123@udc.es";
	private final String VALID_DNI = "12345678A";
	private final String VALID_PASSWORD = "password";
	private final String VALID_FIRST_NAME = "firstName1";
	private final String VALID_LAST_NAME = "lastName1";
	private final String VALID_BIRTH_DATE = "01/01/1990";
	private final String VALID_LANGUAGE = "ESP";
	private final String VALID_USER_TYPE = "EMPLOYEE";
	private final String VALID_USER_IMAGE = "asfasdgasdg";

	private RegisterData getValidRegisterData() {
		return new RegisterData(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DNI, VALID_EMAIL, VALID_PASSWORD,
				VALID_BIRTH_DATE, VALID_LANGUAGE, VALID_USER_TYPE, VALID_USER_IMAGE);
	}

	//@Test
	@Rollback(value = true)
	public void RegisterUser()
			throws InputValidationException, ConfirmPasswordNotMatchException, DuplicateInstanceException {

		userService.registerUser(getValidRegisterData());

	}

}
